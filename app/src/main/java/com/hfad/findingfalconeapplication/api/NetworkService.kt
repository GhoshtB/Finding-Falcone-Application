package com.hfad.findingfalconeapplication.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkService {

    lateinit var retrofit: Retrofit
    val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private val TIMEOUT_CONNECT = 30   //In seconds
    private val TIMEOUT_READ = 30   //In seconds
    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"

    val chains = Interceptor { chain ->
        val origin = chain.request()

        origin.newBuilder()
            .method(origin.method, origin.body)
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)

        chain.proceed(origin)
    }

    val logging: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            }.level = HttpLoggingInterceptor.Level.BODY

            return loggingInterceptor
        }

    init {
        okHttpClient.connectTimeout(TIMEOUT_CONNECT.toLong(),TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIMEOUT_READ.toLong(),TimeUnit.SECONDS)
        okHttpClient.addInterceptor(chains)
        okHttpClient.addInterceptor(logging)
    }

    fun <T> callApiServices(baseUrl: String, classes: Class<T>): T {
        retrofit = Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(classes)
    }

}