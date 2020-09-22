package com.hfad.findingfalconeapplication

import android.widget.Toast
import androidx.fragment.app.Fragment

fun MainActivity.launchFragment(fragment: Fragment,layoutId:Int){

   var count= supportFragmentManager.backStackEntryCount
    var transcation= supportFragmentManager.beginTransaction()

    transcation.addToBackStack("")

    if (count == 0) {
        transcation.add(R.id.frameId, fragment).commit()

    } else {
        transcation.replace(R.id.frameId, fragment).commit()
    }
}

fun MainActivity.showToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

}