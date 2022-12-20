package br.com.brainweb.i

import android.app.Activity
import android.content.res.Resources
import br.com.brainweb.maindom.R
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.ONE
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.TWO
import com.appsflyer.AppsFlyerLib
import okhttp3.HttpUrl
import java.util.*

class NewJokers {
    operator fun invoke(
        res: Resources,
        aData: MutableMap<String, Any>?,
        fData: String,
        gadid: String,
        activity: Activity
    ): String  = HttpUrl.Builder()
        .scheme("https")
        .host(ONE)
        .addPathSegment(TWO)
        .addQueryParameter(
            res.getString(R.string.iArJi9I3a),
            res.getString(R.string.CiNZOkXBI1)
        )
        .addQueryParameter(
            res.getString(R.string.uvExeT7Zet),
            TimeZone.getDefault().id
        )
        .addQueryParameter(res.getString(R.string.iokp), gadid)
        .addQueryParameter(res.getString(R.string.OSDePUnfBN), fData)
        .addQueryParameter(
            res.getString(R.string.IxER3w4yxK),
            when (fData) {
                "null" -> aData?.get("media_source").toString()
                else -> "deeplink"
            }
        )
        .addQueryParameter(
            res.getString(R.string.L2kuCOI5tf), when (fData) {
                "null" -> {
                    AppsFlyerLib.getInstance().getAppsFlyerUID(activity)
                }
                else -> "null"
            }
        )
        .addQueryParameter(
            res.getString(R.string.irdeYvYa3O),
            aData?.get("adset_id").toString()
        )
        .addQueryParameter(
            res.getString(R.string.o3FhZvRnbH),
            aData?.get("campaign_id").toString()
        )
        .addQueryParameter(
            res.getString(R.string.Jmgjh9zKJj),
            aData?.get("campaign").toString()
        )
        .addQueryParameter(res.getString(R.string.tFPu3fhq7g), aData?.get("adset").toString())
        .addQueryParameter(
            res.getString(R.string.IcHNjmAFrL),
            aData?.get("adgroup").toString()
        )
        .addQueryParameter(
            res.getString(R.string.nJXu2okDjn),
            aData?.get("orig_cost").toString()
        )
        .addQueryParameter(
            res.getString(R.string.AAxInyHS4F),
            aData?.get("af_siteid").toString()
        ).build().toString()


}