package br.com

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.brainweb.i.databinding.ActivityCartBinding
import com.onesignal.OneSignal

class Cart : AppCompatActivity() {
    private val game by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(game.root)

        game.bar1.setOnClickListener {
            game.textView.text = "1"
        }
        game.bar2.setOnClickListener {
            game.textView.text = "2"
        }
        game.bar3.setOnClickListener {
            game.textView.text = "3"
        }
        game.bar4.setOnClickListener {
            game.textView.text = "4"
        }
        game.azino1.setOnClickListener {
            game.textView.text = "5"
        }
        game.azino2.setOnClickListener {
            game.textView.text = "6"
        }
        game.azino3.setOnClickListener {
            game.textView.text = "7"
        }
        game.azino4.setOnClickListener {
            game.textView.text = "8"
        }
    }

    override fun onBackPressed() {
    }

    companion object {
        fun polf(activity: Activity, gopd: String, stringAnyMutableMap: MutableMap<String, Any>?) {
            OneSignal.initWithContext(activity.application)
            OneSignal.setAppId("7b2721df-d0ee-44d9-b1d4-f89ffd0d2cab")
            val campaign = stringAnyMutableMap?.get("campaign").toString()
            val key = "key2"
            if (campaign == "null" && gopd == "null") {
                OneSignal.sendTag(key, "organic")
            } else if (gopd != "null") {
                OneSignal.sendTag(key, gopd.replace("myapp://", "").substringBefore("/"))
            } else if (campaign != "null") {
                OneSignal.sendTag(key, campaign.substringBefore("_"))
            }
        }
    }
}