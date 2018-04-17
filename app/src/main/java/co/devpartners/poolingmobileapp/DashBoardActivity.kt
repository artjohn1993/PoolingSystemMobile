package co.devpartners.poolingmobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.devpartners.poolingmobileapp.database.DataBaseHandler
import co.devpartners.poolingmobileapp.model.Token

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        var db = DataBaseHandler(this)
        var data = db.readData()

        println("##############################")
        println(data.get(0).tokenValue)
    }
}
