package com.example.myapplication1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

abstract class MainActivity6 : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textView1: TextView
    var data: MutableLiveData<String> = MutableLiveData()
    abstract val data1: LiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        textView = findViewById(R.id.layout1)
        textView1 = findViewById(R.id.layout2)


        /* val job1 = CoroutineScope(Dispatchers.Main).async {
            // launchFunction1()
            Log.e("Function 1 : ", launchFunction1().toString())
            Log.e("Function 2 : ",launchFunction2("Second Function").toString())
        }*/



        runBlocking {

            supervisorScope {

                val job1 = CoroutineScope(Dispatchers.IO).async {
                    // launchFunction1()
                    //  data?.value = launchFunction1().toString()
                    Log.e("Function 1 : ", launchFunction1().toString())
                    Log.e("Thread Function 1 : ", Thread.currentThread().name)

                    withContext(Dispatchers.Main) {

                        updateUI(launchFunction1().toString())
                    }
                    // Log.e("Function 2 : ",launchFunction2("Second Function").toString())
                }
                val job2 = CoroutineScope(Dispatchers.Main).async {
                    // launchFunction1()
                    // Log.e("Function 1 : ", launchFunction1().toString())
                    // data.value = launchFunction2("Second Function").toString()

                    val result = launchFunction2("Second Function").toString()
                    data.value = result

                    withContext(Dispatchers.Main) {

                        updateUI3()
                    }
                    Log.e("Function 2 : ", launchFunction2("Second Function").toString())
                    Log.e("Thread Function 2 : ", Thread.currentThread().name)

                }

                val job4 = CoroutineScope(Dispatchers.Default).launch {
                    // launchFunction1()
                    // data.value = launchFunction4().toString()
                    Log.e("Check : ", "1")
                    try {
                        delay(200)
                        Log.e("Check : ", "Try")


                        println("Task 2 completed")
                    } catch (exception: CancellationException) {
                        Log.e("Check Catch: ", "${exception.message}")
                        // throwsException()
                        println("Task 2 was canceled: ${exception.message}")
                        throw exception
                    }

                    Log.e("Function 4 : ", launchFunction4().toString())
                    Log.e("Thread Function 4 : ", Thread.currentThread().name)

                    // Log.e("Function 2 : ",launchFunction2("Second Function").toString())
                }
                val job3 = CoroutineScope(Dispatchers.Main).launch {
                    // launchFunction1()
                    // Log.e("Function 1 : ", launchFunction1().toString())
                    val result = launchFunction3("Third Function").toString()
                    data.value = result

                    withContext(Dispatchers.Main) {

                        updateUI3()
                    }
                    Log.e("Function 3 : ", launchFunction3("Third Function").toString())
                    Log.e("Thread Function 3 : ", Thread.currentThread().name)

                }

                delay(100)
                Log.e("Check : ", "Cancelling...")
                job4.cancel()
                job1.cancel()

            }
        }
      /*
        runBlocking {
               supervisorScope {
            val job1 = async {
                try {
                    delay(100)
                    println("Child coroutine 1 completed")
                } catch (e: Exception) {
                    println("Child coroutine 1 failed with exception: $e")
                }
            }

            val job2 = async {
                try {
                    delay(100)

                    throw Exception("Child coroutine 2 failed")
                } catch (e: Exception) {
                    println("Child coroutine 2 failed with exception: $e")
                }
            }

            val job3 = async {
                try {
                    updateUI("Try 3")
                    delay(100)
                    println("Child coroutine 3 completed")

                } catch (e: Exception) {
                    println("Child coroutine 3 failed with exception: $e")
                }
            }

            job1.join()
            job2.join()
            job3.join()


        }
           }
*/

    }

/*        runBlocking {

            val job1 = CoroutineScope(Dispatchers.Main).launch {
               // launchFunction1()
                Log.e("Function 1 : ", launchFunction1().toString())
                Log.e("Function 2 : ",launchFunction2("Second Function").toString())
            }
          *//*  val job2 =  CoroutineScope(Dispatchers.Main).launch {
               // launchFunction2("Second Function")
                Log.e("Function 2 : ",launchFunction2("Second Function").toString())
            }*//*

           *//* job1.join()
            job2.join()

            Log.e("Function 1 : ",job1.toString())
            Log.e("Function 2 : ",job2.toString())*//*

            Log.e("Function 1 : ",job1.toString())
           // Log.e("Function 2 : ",job2.toString())

        }*/

    private fun updateUI3() {

        data.observe(this, Observer {
            Log.e("Observe : ", data.value.toString())
            textView1.text = data.value

        })
    }

    private fun updateUI(data: String) {

        textView.text = data
    }


    private suspend fun launchFunction2(data: String): Any {

        delay(6000)
        return "Function : $data"

    }

    private suspend fun launchFunction1(): Any {
        delay(1500)
        return "Function : First"
    }

    private suspend fun launchFunction3(data: String): Any {

        delay(15000)
        return "Function : $data"

    }

    private suspend fun launchFunction4(): Any {
        delay(3000)
        return "Function : Fourth"
    }

    private fun sortArray(){
        val numberArray = arrayOf(6,2,0,4,3,0,9,0)

        for (i in 0 until numberArray.size-1){
            for (j in 0 until numberArray.size - 1 - i){
                if (numberArray[j] > numberArray[j+1]){
                    val temp = numberArray[j+1]
                    numberArray[j+1] = numberArray[j]
                    numberArray[j] = temp
                }
            }
        }
        Log.e("Result", numberArray.toString())
    }

}