package br.com.brainweb.maindom.namejokers

import android.app.Application
import android.content.ContentValues
import android.provider.BaseColumns._ID
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.brainweb.maindom.domjokers.Jokers
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.CART
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.TABLE_MANE
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
        db.insert(TABLE_MANE, null, content)
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

        val cartDb = cursor.getString(cursor.getColumnIndexOrThrow(CART))
        val idDb = cursor.getString(cursor.getColumnIndexOrThrow(_ID))
        return Jokers(cart = cartDb)
    }
}