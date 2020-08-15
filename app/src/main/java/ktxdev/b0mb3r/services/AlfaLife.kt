package ktxdev.b0mb3r.services

import ktxdev.b0mb3r.BaseService
import org.json.JSONObject

class AlfaLife : BaseService() {
    override fun onRun(countryCode: String, phoneNumber: String) {
        super.onRun(countryCode, phoneNumber)

        post("https://alfalife.cc/auth.php",
            JSONObject()
                .put("phone", phoneNumber)
        )
    }
}