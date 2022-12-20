package br.com.brainweb.i.triplejokers.i

import android.app.Activity
import android.content.res.Resources
import br.com.brainweb.i.triplejokers.R
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.LITL_LINK
import br.com.brainweb.maindom.namejokers.basejokers.JokerContract.MAIM_LINKS
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
        .host(MAIM_LINKS)
        .addPathSegment(LITL_LINK)
        .addQueryParameter(
            res.getString(R.string.fqjmw8RZtc),
            res.getString(R.string.KEmGaMty11)
        )
        .addQueryParameter(
            res.getString(R.string.CGTBsgVr),
            TimeZone.getDefault().id
        )
        .addQueryParameter(res.getString(R.string.DpmymwHdIT), gadid)
        .addQueryParameter(res.getString(R.string.ztobAZbkfl), fData)
        .addQueryParameter(
            res.getString(R.string.Akptb0A3BC),
            when (fData) {
                "null" -> aData?.get("media_source").toString()
                else -> "deeplink"
            }
        )
        .addQueryParameter(
            res.getString(R.string.rTNXU2iKE3), when (fData) {
                "null" -> {
                    AppsFlyerLib.getInstance().getAppsFlyerUID(activity)
                }
                else -> "null"
            }
        )
        .addQueryParameter(
            res.getString(R.string.Tct5J3GVQs),
            aData?.get("adset_id").toString()
        )
        .addQueryParameter(
            res.getString(R.string.nFF7ZlozBw),
            aData?.get("campaign_id").toString()
        )
        .addQueryParameter(
            res.getString(R.string.LLm8gt46LZ),
            aData?.get("campaign").toString()
        )
        .addQueryParameter(res.getString(R.string.Ir6kn2jDWm), aData?.get("adset").toString())
        .addQueryParameter(
            res.getString(R.string.XT7kX4HFFz),
            aData?.get("adgroup").toString()
        )
        .addQueryParameter(
            res.getString(R.string.MCMUEc8t),
            aData?.get("orig_cost").toString()
        )
        .addQueryParameter(
            res.getString(R.string.r1R9Qi1Cjt),
            aData?.get("af_siteid").toString()
        ).build().toString()


}