package ktxdev.b0mb3r.services

import android.content.SharedPreferences
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import ktxdev.b0mb3r.BaseService
import ktxdev.b0mb3r.Utils
import org.json.JSONObject
import java.lang.reflect.Parameter

class IziUA(pref: SharedPreferences) : BaseService(pref) {
    override fun onRun(countryCode: String, phoneNumber: String) {
        super.onRun(countryCode, phoneNumber)

        val (request, response, result) = "https://izi.ua/api/auth/register"
            .httpPost(listOf(
                "phone" to "+${countryCode}${phoneNumber}",
                "name" to "${Utils.getRandomRussianName()}",
                "is_terms_accepted" to true
            ))
            .responseString()

        val (request, response, result) = "https://izi.ua/api/auth/sms-login"
            .httpPost(listOf(
                "phone" to "+${countryCode}${phoneNumber}"
            ))
            .responseString()
    }
}