package br.com.brainweb.i

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.Cart
import br.com.brainweb.i.StertJokers.Companion.PUT
import br.com.brainweb.i.databinding.ActivityWemMyClientBinding
import kotlinx.coroutines.launch

class WemMyClient : AppCompatActivity() {

    private var isCallbackEnabled = true

    private val binding by lazy {
        ActivityWemMyClientBinding.inflate(layoutInflater)
    }
    lateinit var gop: SharedPreferences
    lateinit var rop: SharedPreferences.Editor
    private lateinit var viewJoker: ViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewJoker = ViewClass()
        gop = getSharedPreferences("kol", MODE_PRIVATE)
        rop = gop.edit()
        var kol: ValueCallback<Array<Uri>>? = null

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(isCallbackEnabled) {
                override fun handleOnBackPressed() {
                    if (binding.olen.canGoBack()) binding.olen.goBack()
                }
            })

        var strinp = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            kol?.onReceiveValue(it.toTypedArray())
        }

        CookieManager.getInstance().setAcceptThirdPartyCookies(binding.olen, true)
        CookieManager.getInstance().setAcceptCookie(true)

        binding.olen.loadUrl(intent.getStringExtra(PUT).toString())

        image(kol, strinp)

        binding.olen.settings.javaScriptEnabled = true
        binding.olen.settings.userAgentString =
            binding.olen.settings.userAgentString.replace("wv", "")
        binding.olen.settings.domStorageEnabled = true

        binding.olen.webViewClient = object : WebViewClient() {
            override fun onPageFinished(i: WebView?, lot: String) {
                super.onPageFinished(i, lot)
                CookieManager.getInstance().flush()
                workUrt(lot)
            }
        }
    }

    private fun workUrt(lot: String) {
        if (lot == "https://warmwinter.solutions/") {
            startActivity(Intent(this@WemMyClient, Cart::class.java))
            finish()
        } else {
            binding.kinl.visibility = View.GONE
            binding.olen.visibility = View.VISIBLE
            CookieManager.getInstance().flush()
            funSet(lot)
        }
    }

    private fun funSet(lot: String) {
        when (gop.getString("del", "one")) {
            "one" -> {
                rop.putString("del", "save")
                rop.apply()
            }
            "save" -> {
                lifecycleScope.launch {
                    viewJoker.webSev(lot, application)
                }
                rop.putString("del", "false")
                rop.apply()
            }
            "false" -> {
            }

            else -> {
                rop.putString("del", "save")
                rop.apply()
            }
        }
    }

    private fun image(
        kol: ValueCallback<Array<Uri>>?,
        strinp: ActivityResultLauncher<String>
    ) {
        var kol1 = kol
        binding.olen.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView,
                fpc: ValueCallback<Array<Uri>>,
                params: FileChooserParams?
            ): Boolean {
                if (isCallbackEnabled) {
                    kol1 = fpc
                    strinp.launch("image/*")
                }
                return true
            }
        }
    }
}