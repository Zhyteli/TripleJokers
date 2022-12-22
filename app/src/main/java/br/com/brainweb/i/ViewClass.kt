package br.com.brainweb.i

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import br.com.Cart
import br.com.brainweb.i.StertJokers.Companion.PUT
import br.com.brainweb.i.triplejokers.i.NewJokers
import br.com.brainweb.i.triplejokers.maindom.domjokers.Jokers
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewClass : AppCompatActivity() {

    private val newBild = NewJokers()

    private lateinit var impl: JokersImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataMaid()
    }

    fun getCart(application: Application): String? {
        impl = JokersImpl(application)
        return impl.getterJoker()?.cart
    }

    fun chBoolData(application: Application): Boolean {
        impl = JokersImpl(application)
        return impl.getterJoker() != null
    }

    fun webSev(ul: String, application: Application) {
        impl = JokersImpl(application)
        impl.setterSQLData(Jokers(cart = ul))
    }

    private fun dataMaid() {
        AppLinkData.fetchDeferredAppLinkData(this.application) { appLink ->
            val dataFas = appLink?.targetUri.toString()
            if (appLink?.targetUri.toString() == "null") {
                val p1 = object : AppsFlyerConversionListener {
                    override fun onConversionDataSuccess(dataApps: MutableMap<String, Any>?) {
                        lifecycleScope.launch {
                            apOneMain(this@ViewClass, dataApps, dataFas)
                        }
                    }
                    override fun onConversionDataFail(p0: String?) {
                        lifecycleScope.launch {
                            fasTwoFalseDeep(this@ViewClass, dataFas, null)
                        }
                    }

                    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                    }

                    override fun onAttributionFailure(p0: String?) {
                    }
                }
                AppsFlyerLib.getInstance()
                    .init("vs8uqiWZjAX7r5CukUnByE", p1, this@ViewClass)
                AppsFlyerLib.getInstance().start(this)
            } else {
                lifecycleScope.launch {
                    fasTwoFalseDeep(this@ViewClass, dataFas, null)
                }
            }
        }
    }

    private suspend fun fasTwoFalseDeep(
        afi: Activity,
        dataFas: String,
        aps: MutableMap<String, Any>?
    ) {
        val id = withContext(Dispatchers.Default) {
            AdvertisingIdClient.getAdvertisingIdInfo(
                afi.application
            ).id.toString()
        }
        OneSignal.setExternalUserId(id)
        Cart.polf(this,dataFas,aps)
        val lop = newBild(
            fData = dataFas,
            gadid = id,
            res = afi.application.resources,
            activity = afi,
            aData = aps
        )
        val i = Intent(this, WemMyClient::class.java)
        i.putExtra(PUT, lop)
        startActivity(i)
        finish()
    }

    private suspend fun apOneMain(
        activity: Activity,
        asp: MutableMap<String, Any>?,
        tpo: String
    ) {
        val slans = bongol(activity)
        OneSignal.setExternalUserId(slans)
        Cart.polf(this,tpo,asp)
        val value = newBild(
            res = activity.application.resources,
            aData = asp,
            fData = tpo,
            gadid = slans,
            activity = activity
        )
        val i = Intent(this, WemMyClient::class.java)
        i.putExtra(PUT, value)
        startActivity(i)
        finish()
    }

    private suspend fun bongol(activity: Activity): String {
        val yoi = withContext(Dispatchers.Default) {
            AdvertisingIdClient.getAdvertisingIdInfo(
                activity.application
            ).id.toString()
        }
        return yoi
    }
    override fun onBackPressed() {
    }
}