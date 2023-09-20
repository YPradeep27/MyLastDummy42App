package com.example.myapplication1

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject

class MainActivity1() : MyAbstract(){

    lateinit var textView : TextView
    lateinit var textView2 : TextView
    lateinit var editText : EditText
    lateinit var mSocket : Socket
    lateinit var btnGet : Button
    lateinit var mainActivity2 : MainActivity2


    override fun myData(data: String) {

    }
    init {

        MainActivity2.name
        mainActivity2.numberName

        try {
            mSocket = IO.socket("http://chat.socket.io")
        }catch (e : Exception){
            Log.e("Exception :",e.toString())
        }
        mSocket.connect()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        textView = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        editText = findViewById(R.id.editText)
        btnGet = findViewById(R.id.btnGet)


      //  mSocket.on("new message", onNewMessage);



        btnGet.setOnClickListener {

            Log.e("Get f","True")
            mSocket.on("sendMessage", onNewMessage)
           // mSocket.emit("message")
/*            mSocket.on("message"){args ->

                Log.e("Get", args.toString())
                val data = args[0] as String
                try {
                   // val message = data.getString("message")
                       runOnUiThread {
                           textView2.text = data +" Added"
                       }
                    // Handle the received message
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }*/
        }


        textView.setOnClickListener {
/*
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)*/

            attemptSend()

        }



    }

    private val onNewMessage : Emitter.Listener = Emitter.Listener { args ->

        Log.e("GetMessage1","True")

        val data = args[0] as JSONObject
        try {
             val message = data.getString("message")
            runOnUiThread {
                textView2.text = message +" Added"
            }
            // Handle the received message
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

/*    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
                    addMessage(username, message);
                }
            });
        }
    };*/

    private fun attemptSend() {
        val message = editText.text.toString().trim()
        val data = JSONObject()
        if (TextUtils.isEmpty(message)){
            return
        }
        editText.run {
            Log.e("Edit Text ","True")
            data.put("message",message)
            if (TextUtils.isEmpty(message)){
                return
            }
            mSocket.emit("sendMessage", data);
            Log.e("Edit Text ","Emitted : ${data}")
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mSocket.connected()){
            mSocket.disconnect()
        }
    }

}