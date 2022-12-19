package br.com.brainweb.maindom.namejokers.basejokers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.CREATE_COMMAND
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.DROP_COMMAND

class JokerDBHelper(
    context: Context?
) : SQLiteOpenHelper(
    context, "link.db", null, 1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_COMMAND)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_COMMAND)
        onCreate(p0)
    }
}