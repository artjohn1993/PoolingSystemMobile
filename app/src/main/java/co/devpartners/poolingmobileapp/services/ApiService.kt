package co.devpartners.poolingmobileapp.services

import android.graphics.ColorSpace
import co.devpartners.poolingmobileapp.model.LoginUser
import co.devpartners.poolingmobileapp.model.Model
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.*
import java.util.*

/**
 * Created by DEVPARTNERS SOFTWARE on 17/04/2018.
 */
interface ApiService{

    @Headers("content-type: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("Auth/login")
    fun login(@Field("username") username : String = "testuser",
              @Field("password")password : String = "12345") : Observable<Model.Result>

    @GET("Skill")
    fun getSkills() : Observable<List<Model.Skills>>
}
