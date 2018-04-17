package co.devpartners.poolingmobileapp.Api

import co.devpartners.poolingmobileapp.services.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by DEVPARTNERS SOFTWARE on 17/04/2018.
 */
class PoolingAPi {
    companion object {
        fun createOkhttp(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
                    .build()




            return httpClient
        }

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("http://13.75.89.123/pooling/api/")
                    .client(createOkhttp())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}