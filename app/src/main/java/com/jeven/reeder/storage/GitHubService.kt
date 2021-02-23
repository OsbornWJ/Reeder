package com.jeven.reeder.storage

import com.jeven.reeder.data.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * 创建人：wenjie
 * 邮箱： Osbornjie@163.com
 * 功能：
 */
open interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo>>
}