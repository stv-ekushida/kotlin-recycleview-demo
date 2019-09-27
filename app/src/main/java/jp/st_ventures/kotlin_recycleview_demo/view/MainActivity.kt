package jp.st_ventures.kotlin_recycleview_demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import jp.st_ventures.kotlin_recycleview_demo.R
import jp.st_ventures.kotlin_recycleview_demo.model.HomeFeed
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    /// JSONデータ取得
    fun fetchJson() {

        val url = "http://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object:Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val bodyJson = toPrettyFormat(body.toString())
                print(bodyJson)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed:: class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }
        })
    }


    fun toPrettyFormat(jsonString: String): String {
        val parser = JsonParser()
        val json = parser.parse(jsonString).asJsonObject
        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(json)
    }
}

