package com.example.myapplication1

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class MyAbstract : AppCompatActivity() {


    abstract fun myData(data : String)

    fun myResponse(response : String){
        Log.e("Key","Response .....")
    }

}