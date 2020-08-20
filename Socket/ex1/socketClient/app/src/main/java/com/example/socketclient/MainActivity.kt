package com.example.socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var thread = NetworkThread()
            thread.start()
        }
    }

    inner class NetworkThread : Thread() {
        override fun run() {
            try {
                // Server에 접속
                var socket = Socket("192.168.0.167", 55555)
                Log.d("SOCKET", "Server에 접속")

                // Data 수신 (Server -> Client)
                var input = socket.getInputStream()
                var dis = DataInputStream(input)

                // Data 송신 (Client -> Server)
                var output = socket.getOutputStream()
                var dos = DataOutputStream(output)

                // Client 수신 내용
                var data1 = dis.readInt()
                var data2 = dis.readDouble()
                var data3 = dis.readUTF()
                Log.d("SOCKET", "수신 성공")

                // Client 송신 내용
                dos.writeInt(200)
                dos.writeDouble(1122.1)
                dos.writeUTF("CLIENT")
                Log.d("SOCKET", "송신 성공")

                runOnUiThread {
                    text.text = "data1 : ${data1} \n"
                    text.append("data2 : ${data2} \n")
                    text.append("data3 : ${data3} \n")
                }

                // Socket 해제
                socket.close()



            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }
}