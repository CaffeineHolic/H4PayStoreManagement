package com.h4pay.store.networking.tools

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException

class checkUpdate(): AsyncTask<Void, Int, JSONObject>(){
    private val TAG = "checkUpdate"
    override fun doInBackground(vararg params: Void?): JSONObject? {
        var version = 0.0
        try {
            // 서버연결
            val url = URL(
                    "https://yoon-lab.xyz/h4pay/lastest_version.json"
            )
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            conn.requestMethod = "GET"
            conn.doInput = true
            conn.connect()
            /* 서버 -> 안드로이드 파라메터값 전달 */
            val iss = conn.inputStream
            val inn = BufferedReader(InputStreamReader(iss))
            var line = inn.readLines()
            var page = String()
            for (element in line) {
                page += element
                Log.e("RECV DATA*", page)
            }
            Log.e(TAG, "1차")
            val jsonObject = JSONObject(page)
            Log.e(TAG, "2차")
            return jsonObject
        } catch (e: UnknownHostException){
            e.printStackTrace()
            return null
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
    override fun onPostExecute(result: JSONObject?) {
        super.onPostExecute(result)
        return
    }
}