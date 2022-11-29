package com.gedehari.pubpix.network

import com.gedehari.pubpix.Config
import com.gedehari.pubpix.model.Post
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("post")
    suspend fun getPosts(
        @Query("from")
        from: Int = 0,

        @Query("limit")
        limit: Int = 0 // server default is 10
    ): NetworkResponse<List<Post>, ErrorResponse>

    companion object {
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Config.BASE_URL)
            .build()

        private var instance: ApiService? = null

        fun getClient(): ApiService {
            return instance ?: synchronized(this) {
                val newInstance = retrofit.create(ApiService::class.java)
                instance = newInstance
                return newInstance
            }
        }
    }
}