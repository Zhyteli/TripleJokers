package br.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.brainweb.i.databinding.ActivityCartBinding

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
}