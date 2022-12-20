package br.com.brainweb.i

import android.app.Application
import android.content.ContentValues
import android.provider.BaseColumns._ID
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.CART
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.TABLE_MANE
import br.com.brainweb.maindom.domjokers.Jokers
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.SAVETWO
import br.com.brainweb.maindom.namejokers.basejokers.JokerDBHelper

class JokersImpl(
    application: Application
) {
    private val helper = JokerDBHelper(application)
    private val db = helper.writableDatabase
    private lateinit var mutableLiveData: MutableLiveData<Jokers>

    fun setterSQLData(carts: Jokers) {
        val content = ContentValues()
        content.put(CART, carts.cart)
        if (getterLive().value == null) {
            db.insert(TABLE_MANE, null, content)
        } else if (getterLive().value!!.cart.contains(SAVETWO)) {
            db.insert(TABLE_MANE, null, content)
        }
    }

    fun getterLive(): MutableLiveData<Jokers> {
        mutableLiveData = MutableLiveData()
        val cursor = db.query(
            TABLE_MANE,
            null,
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()){
            val cartDb = cursor.getString(cursor.getColumnIndexOrThrow(CART))
            mutableLiveData.value = Jokers(cart = cartDb)
            return mutableLiveData
        }
        return mutableLiveData
    }

    fun getterJoker(): Jokers?{
        val cursor = db.query(
            TABLE_MANE,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val jk = Jokers()
        while (cursor.moveToNext()) {
            val cartDb = cursor.getString(cursor.getColumnIndexOrThrow(CART))
            val ig = cursor.getString(cursor.getColumnIndexOrThrow(_ID))
            return if (ig == "0"){
                Log.d("olympusViewModel", "1 ${jk.toString()}")
                jk.cart = cartDb
                jk
            }else{
                Log.d("olympusViewModel", "1 ${jk.toString()}")
                jk.cart = cartDb
                jk
            }
        }
        return if (jk.cart == "null" || jk.cart == ""){
            null
        }else{
            jk
        }
    }
}