package ktxdev.b0mb3r.service

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestAsCurlInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import ktxdev.b0mb3r.controller.DEBUG
import ktxdev.b0mb3r.phone.Phone
import ktxdev.b0mb3r.utils.getRandomUserAgent


public val services by lazy {
     ServiceFactory.build()
}

fun MutableList<Service>.service(
        url: String = "",
        method: Method = Method.POST,
        headers: MutableMap<String, String> = mutableMapOf(
             "User-Agent" to getRandomUserAgent(),
             "X-Requested-With" to "XMLHttpRequest"
        ),
        params: MutableMap<String, Any?> = mutableMapOf(),
        formData: MutableMap<String, Pair<String, String>> = mutableMapOf(),
        body: Any? = null,
        contentType: String? = null,
        fn: Request.(Phone) -> Unit = {}){
     add(Service(Request(url, method, headers, params, formData, body, contentType), fn))
}
