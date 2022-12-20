package br.com.brainweb.i

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import br.com.brainweb.i.MainActivity.Companion.tagOneSignal
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
        getConversionData(this)
    }
    fun getCart(application: Application): String? {
        impl = JokersImpl(application)
        return impl.getterJoker()?.cart
    }
    fun chBoolData(application: Application): Boolean {
        impl = JokersImpl(application)
        return impl.getterJoker() != null
    }

    fun webSev(ul:String,application: Application) {
        impl = JokersImpl(application)
        impl.setterSQLData(Jokers(cart = ul))
    }

    private fun getConversionData(activity: Activity) {
        AppLinkData.fetchDeferredAppLinkData(activity.application) { appLink ->
            val fbData = appLink?.targetUri.toString()

            if (fbData == "null") {
                val conversionDataListener = object : AppsFlyerConversionListener {
                    override fun onConversionDataSuccess(apfData: MutableMap<String, Any>?) {
                        Log.d("mfail1", apfData.toString())

                        lifecycleScope.launch {
                            apOneMain(activity, apfData, fbData)
                        }
                    }

                    override fun onConversionDataFail(p0: String?) {
                        Log.d("mfail2", p0.toString())
                        lifecycleScope.launch {
                            fasTwoFalseDeep(activity, fbData, null)
                        }
                    }

                    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                    }

                    override fun onAttributionFailure(p0: String?) {
                    }
                }
                AppsFlyerLib.getInstance().init("vs8uqiWZjAX7r5CukUnByE", conversionDataListener, activity)
                AppsFlyerLib.getInstance().start(activity)
            }else{
                Log.d("mfail3", "deep")
                lifecycleScope.launch {
                    fasTwoFalseDeep(activity, fbData, null)
                }
            }
        }
    }

    private suspend fun fasTwoFalseDeep(activity: Activity, fbData: String, aps: MutableMap<String, Any>?) {
        val gadid = withContext(Dispatchers.Default) {
            AdvertisingIdClient.getAdvertisingIdInfo(
                activity.application
            ).id.toString()
        }
        OneSignal.setExternalUserId(gadid)
        val data = newBild(
            res = activity.application.resources,
            aData = null,
            fData = fbData,
            gadid = gadid,
            activity = activity
        )
        OneSignal.initWithContext(activity.application)
        OneSignal.setAppId("7b2721df-d0ee-44d9-b1d4-f89ffd0d2cab")

        val campaign = aps?.get("campaign").toString()
        val key = "key2"
        OneSignal.sendTag(key, tagOneSignal(fbData, campaign))
        Log.d("mfail3-2", data.toString())
        val i = Intent(this, WemMyClient::class.java)
        i.putExtra("link_one", data)
        startActivity(i)
        finish()
    }

    private suspend fun apOneMain(
        activity: Activity,
        apfData: MutableMap<String, Any>?,
        fbData: String
    ) {
        val gadid = withContext(Dispatchers.Default) {
            AdvertisingIdClient.getAdvertisingIdInfo(
                activity.application
            ).id.toString()
        }
        OneSignal.setExternalUserId(gadid)
        val data = newBild(
            res = activity.application.resources,
            aData = apfData,
            fData = fbData,
            gadid = gadid,
            activity = activity
        )
        OneSignal.initWithContext(activity.application)
        OneSignal.setAppId("7b2721df-d0ee-44d9-b1d4-f89ffd0d2cab")

        val campaign = apfData?.get("campaign").toString()
        val key = "key2"
        OneSignal.sendTag(key, tagOneSignal(fbData, campaign))
        Log.d("mfail1", data.toString())
        val i = Intent(this, WemMyClient::class.java)
        i.putExtra("link_one", data)
        startActivity(i)
        finish()
    }
}