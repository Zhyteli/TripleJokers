package br.com.brainweb.i

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.brainweb.i.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var long: String

    private lateinit var viewJoker: ViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewJoker = ViewClass()

        if (viewJoker.chBoolData(application)) {
            long = viewJoker.getCart(application).toString()
            val inter = Intent(this, WemMyClient::class.java)
            val observable = Observable.just(viewJoker.getCart(application).toString())
            observable.map { it }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.d("TTT", it.toString())
                    inter.putExtra(PUT, it)
                    startActivity(inter)
                    finish()
                }
        } else {
            startActivity(Intent(this, ViewClass::class.java))
            finish()
        }

    }

    companion object {
        fun tagOneSignal(data2: String, campaign: String): String {
            return if (campaign == "null" && data2 == "null") {
                "organic"
            } else if (data2 != "null") {
                data2.replace("myapp://", "").substringBefore("/")
            } else if (campaign != "null") {
                campaign.substringBefore("_")
            } else {
                "null"
            }
        }

        const val PUT = "link_one"
    }
}