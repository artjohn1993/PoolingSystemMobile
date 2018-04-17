package co.devpartners.poolingmobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.devpartners.poolingmobileapp.Api.PoolingAPi
import co.devpartners.poolingmobileapp.model.LoginUser
import co.devpartners.poolingmobileapp.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var disposable : Disposable? = null

    private val apiServer by lazy {
        PoolingAPi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        loginButton.setOnClickListener {

            login()
        }
    }

    fun login(){

        disposable = apiServer.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    println("==============================================")
                    println(result.toString())
                },
                        {error ->
                            println("----------------------------------------------------")
                            println(error)

                        })

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()

    }
}
