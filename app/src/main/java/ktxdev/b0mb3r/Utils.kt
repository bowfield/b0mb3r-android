package ktxdev.b0mb3r

import java.util.*

object Utils {
    fun getRandomUserAgent() : String {
        var r = Random()

        var applewebkitv = r.nextInt(600)
        var mozillav = r.nextInt(9)
        var chromesubv = r.nextInt(3880)

        return "Chrome: Mozilla/${mozillav}.0 (X11; Linux x86_64) AppleWebKit/${applewebkitv}.0 (KHTML, like Gecko) Chrome/77.0.${chromesubv}.90 Safari/537.36."
    }

    fun getRandomRussianName() : String {
        return ""
    }

    fun getRandomUserName() : String {
        return ""
    }

    fun getRandomEmail() : String {
        return getRandomUserName() + "@gmail.com"
    }
}