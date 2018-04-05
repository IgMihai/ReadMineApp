package com.assist.redmineapp.data

import com.assist.redmineapp.Models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by assist on 30.03.2018.
 */
class RestClient {
    private var retrofit: Retrofit? = null


    companion object {
        public val instance: RestClient by lazy { RestClient() }

    }

    val api: API

    init {
        val clientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.networkInterceptors().add(httpLoggingInterceptor)

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(User.instance.getDomainName())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .build()
        }
        api = retrofit!!.create(API::class.java)
    }

}