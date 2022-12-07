package com.gedehari.pubpix.network

import com.gedehari.pubpix.Config
import com.gedehari.pubpix.model.error.ErrorResponse
import com.gedehari.pubpix.model.login.LoginRequestJson
import com.gedehari.pubpix.model.login.LoginResponseJson
import com.gedehari.pubpix.model.login.RefreshRequestJson
import com.gedehari.pubpix.model.post.Post
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ApiService {
    @POST("auth/signIn")
    suspend fun signIn(
        @Body
        request: LoginRequestJson
    ): NetworkResponse<LoginResponseJson, ErrorResponse>

    @POST("auth/refresh")
    suspend fun refresh(
        @Body
        request: RefreshRequestJson
    ): NetworkResponse<LoginResponseJson, ErrorResponse>

    @GET("post")
    suspend fun getPosts(
        @Query("from")
        from: Int = 0,

        @Query("limit")
        limit: Int = 0 // server default is 10
    ): NetworkResponse<List<Post>, ErrorResponse>

    companion object {
        // For parsing JSON body
        private val moshi = Moshi.Builder()
            .add(ErrorResponseJsonAdapter())
            .add(UserJsonAdapter())
            .add(PostJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

        // HTTP client
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()

        // REST client
        private val retrofit = Retrofit.Builder()
            .client(okHttpClient)
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