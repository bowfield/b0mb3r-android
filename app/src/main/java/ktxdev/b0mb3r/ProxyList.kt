package ktxdev.b0mb3r

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

object ProxyList {
    open fun get(count: Int) : JSONObject {
        var client = OkHttpClient()
        val request = Request.Builder()
            .url("https://devunicon.000webhostapp.com/proxy.php?ssl_only=1&count=${count}")
            .header("User-Agent", Utils.getRandomUserAgent())
            .get()
            .build()

        val response: Response = client.newCall(request).execute()

        return JSONObject(response.body().string())
    }
}