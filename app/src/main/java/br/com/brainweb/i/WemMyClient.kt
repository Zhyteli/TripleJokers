package br.com.brainweb.i

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.brainweb.i.databinding.ActivityWemMyClientBinding
import kotlinx.coroutines.launch

class WemMyClient : AppCompatActivity() {

    private var isCallbackEnabled = true

    private val binding by lazy {
        ActivityWemMyClientBinding.inflate(layoutInflater)
    }
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    private lateinit var long: String
    private lateinit var viewJoker: ViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewJoker = ViewClass()
        sharedPreferences = getSharedPreferences("kol", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        var temp: ValueCallback<Array<Uri>>? = null

        var choo = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            temp?.onReceiveValue(it.toTypedArray())
        }

        CookieManager.getInstance().setAcceptThirdPartyCookies(binding.olen, true)
        CookieManager.getInstance().setAcceptCookie(true)

        binding.olen.loadUrl(intent.getStringExtra("link_one")!!)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(isCallbackEnabled) {
                override fun handleOnBackPressed() {
                    if (binding.olen.canGoBack()) binding.olen.goBack()
                }
            })

        binding.olen.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView,
                fpc: ValueCallback<Array<Uri>>,
                params: FileChooserParams?
            ): Boolean {
                if (isCallbackEnabled) {
                    temp = fpc
                    choo.launch("image/*")
                }
                return true
            }
        }

        binding.olen.settings.javaScriptEnabled = true
        binding.olen.settings.domStorageEnabled = true

        binding.olen.webViewClient = object : WebViewClient() {
            override fun onPageFinished(i: WebView?, lot: String) {
                super.onPageFinished(i, lot)
                CookieManager.getInstance().flush()
                if (lot == "https://wilddoggy.online/") {
//                    startActivity(Intent(this@MainActivity, MainCreditsActivity::class.java))
                    finish()
                } else {
                    binding.kinl.visibility = View.GONE
                    binding.olen.visibility = View.VISIBLE
                    CookieManager.getInstance().flush()
                    when (sharedPreferences.getString("del", "one")) {
                        "one" -> {
                            editor.putString("del", "save")
                            editor.apply()
                        }
                        "save" -> {
                            lifecycleScope.launch {
                                viewJoker.webSev(lot,application)
                                Log.d("olympusViewModel_save_2", lot.toString())
                            }
                            editor.putString("del", "false")
                            editor.apply()
                        }
                        "false" -> {
                        }

                        else -> {
                            editor.putString("del", "save")
                            editor.apply()
                        }
                    }
                }
            }
        }

        binding.olen.settings.userAgentString =
            binding.olen.settings.userAgentString.replace("wv", "")
    }
}