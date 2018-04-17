package co.devpartners.poolingmobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.devpartners.poolingmobileapp.Api.PoolingAPi
import co.devpartners.poolingmobileapp.database.DataBaseHandler
import co.devpartners.poolingmobileapp.model.LoginUser
import co.devpartners.poolingmobileapp.model.Token
import co.devpartners.poolingmobileapp.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import java.util.*




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
            var user = LoginUser(usernameEdit.text.toString(), passwordEdit.text.toString())
            login(user)
        }
    }

    fun login(user : LoginUser){

        disposable = apiServer.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    println("==============================================")
                    println(result.tokenString)

                    var token = Token(result.tokenString)
                    var db = DataBaseHandler(this)
                    var result = db.insertData(token)
                    if(result.equals(true)){
                        Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                        startActivity<DashBoardActivity>()
                        finish()
                    }
                    else{
                        Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show()
                    }


                },
                        {error ->
                            println("----------------------------------------------------")
                            Toast.makeText(this,"Invalid input",Toast.LENGTH_LONG).show()

                        })

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()

    }
}
