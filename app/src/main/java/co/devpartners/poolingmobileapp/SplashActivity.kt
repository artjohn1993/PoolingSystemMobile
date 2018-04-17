package co.devpartners.poolingmobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import co.devpartners.poolingmobileapp.database.DataBaseHandler
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()

        Handler().postDelayed({

            var db = DataBaseHandler(this)
            var data = db.readData()
            if(data.size <= 0){
                startActivity<MainActivity>()
                finish()
            }
            else{
                startActivity<DashBoardActivity>()
                finish()
            }

        },2000)
    }
}
