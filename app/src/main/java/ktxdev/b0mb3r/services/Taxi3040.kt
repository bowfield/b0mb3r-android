package ktxdev.b0mb3r.services

import ktxdev.b0mb3r.BaseService
import org.json.JSONObject

class Taxi3040 : BaseService() {
    override fun onRun(countryCode: String, phoneNumber: String) {
        super.onRun(countryCode, phoneNumber)

        post("https://3040.com.ua/taxi-ordering",
            JSONObject()
                .put("callback-phone", phoneNumber)
        )
    }
}