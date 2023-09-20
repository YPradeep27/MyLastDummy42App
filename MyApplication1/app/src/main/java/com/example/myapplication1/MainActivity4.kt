package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity4() : MyAbstract(){

    lateinit var textView : TextView

    val numberArray = arrayOf(5,2,0,4,3,0,0,1,8)
    lateinit var temp : Array<Int>
    var boolean : Boolean = true

    override fun myData(data: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        textView = findViewById(R.id.textView4)

        Log.e("Data",numberArray.joinToString())

        val rows = 4
        val columns = 6
       // printRectanglePattern(rows, columns)


/*        for (i in 0 until numberArray.size - 1) {
            for (j in 0 until numberArray.size - 1 - i) {
                if (numberArray[j] < numberArray[j + 1]) {
                    // Swap numbers[j] and numbers[j+1]
                    val temp = numberArray[j]
                    numberArray[j] = numberArray[j + 1]
                    numberArray[j + 1] = temp
                }
                Log.e("Data1",numberArray.joinToString())
            }
            Log.e("Data2",numberArray.joinToString())
        }

        Log.e("Data3",numberArray.joinToString())*/
/*

        // triangle pattern

        for(i in 0..4){
            for (j in 0..i){
                print("* ")
            }
            println()
        }

        // rectangle pattern

        for(i in 0..4){
            for (j in 0..6){
                print("* ")
            }
            println()
        }

        // inverted pyramid pattern

        for (i in 4 downTo 1) {
            for (j in 1..4 - i) {
                print("  ")
            }
            for (k in 1 until i * 2) {
                print("* ")
            }
            println()
        }
*/
/*

        // pyramid pattern

        for (i in 1..4) {
            for (j in 1..4 - i) {
                print("  ")
            }
            for (k in 1 until i * 2) {
                print("* ")
            }
            println()
        }
*/



     /*   for (i in numberArray){
            if (i )
        }*/

        // Method 1
       /* numberArray.sortDescending()
        numberArray.sort()
        Log.e("Data",numberArray.joinToString())*/

        // Method 2




        textView.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun printRectanglePattern(rows: Int, columns: Int) {
        for (i in 1..rows) {
            for (j in 1..columns-i) {
                print("* ")
            }
            println()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity1::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}