package ktxdev.b0mb3r.services

import ktxdev.b0mb3r.BaseService
import org.json.JSONObject

class Alpari : BaseService() {
    override fun onRun(countryCode: String, phoneNumber: String) {
        super.onRun(countryCode, phoneNumber)

        post("http://94.154.218.82:7201/api/account/register/sendConfirmCode",
            JSONObject()
                .put("phone", phoneNumber)
        )
    }
}