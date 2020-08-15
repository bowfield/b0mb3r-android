package ktxdev.b0mb3r

import okhttp3.*
import org.json.JSONObject


open class BaseService() {
    var client = OkHttpClient()
    fun post(url: String, json: JSONObject) : Boolean {
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

    open fun onRun(countryCode: String, phoneNumber: String) {
        println("(:DEBUG) Вызов ${this.javaClass.canonicalName}")
    }
}