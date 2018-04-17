package co.devpartners.poolingmobileapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import co.devpartners.poolingmobileapp.model.Token

/**
 * Created by DEVPARTNERS SOFTWARE on 17/04/2018.
 */
val DATABASE_NAME = "UserToken"
val TABLE_NAME = "UserInfo"
val COL_ID = "id"
val COL_TOKEN = "token"

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){

    val context = context
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_TOKEN + " VARCHAR(5000))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(token: Token) : Boolean{
        var success : Boolean
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TOKEN,token.tokenValue)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == (-1).toLong())
        {
            success = false
        }
        else
        {
            success = true

        }
        return success
    }
    fun update(token : String){
        val db = this.readableDatabase
        var query = "Select *  from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var cv = ContentValues()
                cv.put(COL_TOKEN,token)

                db.update(TABLE_NAME,cv, COL_ID + "=? ",
                        arrayOf(result.getString(result.getColumnIndex(COL_ID))))
            }while (result.moveToNext())
        }
    }
    fun readData() : MutableList<Token>{
        var list : MutableList<Token> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var token  = Token()
                token.tokenValue = result.getString(result.getColumnIndex(COL_TOKEN))
                list.add(token
                )
            }while (result.moveToNext())
        }
        db.close()
        return list
    }
}

