package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : MyAbstract(), MyInterface2,MyInterface1{

    lateinit var textView : TextView

    override fun myData(data: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myInterface1=this
        val myInterface=this

        myInterface1.showMessage("Interface 1")
        myInterface.showMessage("Interface 2")

        textView = findViewById(R.id.textView)

        textView.setOnClickListener {

            val intent = Intent(this,MainActivity1::class.java)
            startActivity(intent)
        }

    }

    /**
     *
     * @param message
     */
    override fun showMessage(message: String) {
        Log.e("Key",message)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}