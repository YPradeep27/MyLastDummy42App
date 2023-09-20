package com.example.myapplication1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.IllegalArgumentException
import java.util.*

open class MainActivity5 : AppCompatActivity() {

    var c : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val sum = { a : Int,b : Int,c : Int -> a+b+c }

    open fun saveInt(a: Int, b:Int) : Int{
        c = a+b
        return c
    }

    open fun addition(a:Int ,b :Int , c :Int , operation : (Int , Int , Int) -> Int) : Int{
        return operation(a,b,c)
    }

    open fun <T : Number> additionGeneral(a:T,b:T) : T {

        return (a.toDouble() + b.toDouble()) as T

    }

}

class StaticMainActivity5 : AppCompatActivity(){

    companion object {
        fun multiplyInt(a: Int, b:Int):Int {
            val d = a*a*b
            return d
        }
    }

    fun subtractInt(a:Int , b: Int) : Int {
        val d = a-b
        return d
    }
}

class ChildMainActivity5 : MainActivity5(){

    private val staticMainActivity5 : StaticMainActivity5 = StaticMainActivity5()
    private val arrayNumber = arrayOf(5,1,3,0,0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        sortNumbers()

        val d = this.saveInt(3,4)
        Log.e("This", d.toString())

        val e = StaticMainActivity5.multiplyInt(2,3)
        val f = staticMainActivity5.subtractInt(3,2)
        Log.e("Static", e.toString())
        Log.e("Without Static", f.toString())

        val resultHOF = addition(5,21,12,sum)
        val resultGFInt = additionGeneral(1,1)
        val resultGFDouble = additionGeneral(3.1,1.9)
        val resultGFFloat = additionGeneral(3.2f,1.1f)
        Log.e("HOF", resultHOF.toString())
        Log.e("resultGFInt", resultGFInt.toString())
        Log.e("resultGFDouble", resultGFDouble.toString())
        Log.e("resultGFFloat", resultGFFloat.toString())
    }

    private fun sortNumbers() {

        for (i in 0 until arrayNumber.size-1){
            for (j in 0 until arrayNumber.size-1-i){
                if (arrayNumber[j] > arrayNumber[j+1]){
                    val temp = arrayNumber[j+1]
                    arrayNumber[j+1] = arrayNumber[j]
                    arrayNumber[j] = temp
                }
            }
        }
        Log.e("Sort Check",arrayNumber.joinToString())


    }

    override fun saveInt(a: Int, b:Int): Int{

        val d = super.saveInt(a+1,b+2)
        val g = super.sum(1,2,3)
        Log.e("Super d", d.toString())
        Log.e("Super g", g.toString())
        c = a+b
        return c
    }


}