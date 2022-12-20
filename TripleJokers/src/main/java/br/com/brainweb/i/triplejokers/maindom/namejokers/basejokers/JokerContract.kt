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

    const val MAIM_LINKS = "warmwinter.solutions"
    const val SAVETWO = "warmwinter.solutions/jokers.php"
    const val LITL_LINK = "jokers.php"
}