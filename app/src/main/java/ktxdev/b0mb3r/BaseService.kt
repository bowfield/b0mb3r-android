package ktxdev.b0mb3r

import android.content.Context
import android.content.SharedPreferences
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


open class BaseService(_pref: SharedPreferences) {
    var pref: SharedPreferences? = _pref

    var client = OkHttpClient()
    fun post(url: String) : Boolean {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", Utils.getRandomUserAgent())
            .addHeader("X-Requested-With", "XMLHttpRequest")
            //.post(formBody)

            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        return response.isSuccessful
    }

    fun get(url: String, json: JSONObject) : Boolean {
        var formBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", Utils.getRandomUserAgent())
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .post(formBody)
            .build()

        val response: Response = client.newCall(request).execute()

        return response.isSuccessful
    }

    fun getRandomProxy() : JSONObject {
        var json = JSONArray(pref!!.getString("proxys", "{}"))

        return json.get(Random().nextInt(json.length()-1)) as JSONObject
    }

    open fun onRun(countryCode: String, phoneNumber: String) {
        println("(:DEBUG) Вызов ${this.javaClass.canonicalName}")
    }

    fun formatPhone(phone : String, mask : String, mask_symbol : Char = '*') : String{
        var formattedPhone : String = "";
        val splitted = mask.split(mask_symbol); // грязный хак для подсчета количества символов в маске, лучше переделать
        if (phone.length == splitted.size - 1){
            var phoneTemp = phone;
            for (symbol in mask){
                if(symbol == mask_symbol){
                    formattedPhone += phoneTemp[0];
                    phoneTemp = phoneTemp.slice(1.rangeTo(phoneTemp.length - 1));
                } else {
                    formattedPhone += symbol;
                }
            }
        } else {
            formattedPhone = phone;
        }
        return formattedPhone;
    }
}