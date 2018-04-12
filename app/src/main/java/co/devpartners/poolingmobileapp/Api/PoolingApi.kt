package co.devpartners.poolingmobileapp.Api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by DEVPARTNERS SOFTWARE on 12/04/2018.
 */
class PoolingApi {
    companion object Singleton {
        val shared by lazy{
            val api = PoolingApi()
            api.retrofit
        }}

    val retrofit = Retrofit.Builder()
            .baseUrl("http://13.75.89.123/pooling/api")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

}