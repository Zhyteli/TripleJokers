package br.com.brainweb.maindom.namejokers.basejokers

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object JokerContract : BaseColumns {
    const val TABLE_MANE = "link"
    const val CART = "cart"

    const val TYPE_TEXT = "TEXT"
    const val TYPE_INTEGER = "INTEGER"

    const val CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS $TABLE_MANE" +
            "($_ID $TYPE_INTEGER PRIMARY KEY, $CART " +
            "$TYPE_TEXT)"

    const val DROP_COMMAND = "DROP TABLE IF EXISTS $TABLE_MANE"

    const val ONE = "warmwinter.solutions"
    const val SAVETWO = "warmwinter.solutions/jokers.php"
    const val APPS = "sy5MBTcoDjSgCzqkWdjbYK"
    const val TWO = "jokers.php"
    const val SERS = "9852d012-2c93-4b4b-83c2-06425ec326d3"
}