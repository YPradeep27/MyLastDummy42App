package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity2() : MyAbstract(){

    lateinit var textView : TextView

    companion object {
        val name : String = "Kotlin"
    }

    val numberName : String = "One"

    override fun myData(data: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView = findViewById(R.id.textView2)

        textView.setOnClickListener {

            val intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}