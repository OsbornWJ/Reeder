package com.jeven.reeder

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.jeven.reeder.data.Repo
import com.jeven.reeder.storage.GitHubService
import com.jeven.reeder.util.StatusBarHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarHelper.setStatusBarLightMode(this)
        // 4.4及以上版本开启
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        val navView: LinearLayout = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        navView.setupWithNavController(navController)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call: Call<List<Repo>> = retrofit.create(GitHubService::class.java).listRepos("octocat")
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.d("response", "onResponse: ${response.body()!![0].name}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.d("response", "onResponse: ${t.message}")
            }

        })
    }
}