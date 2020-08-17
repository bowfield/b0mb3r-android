package ktxdev.b0mb3r.utils

import com.github.kittinunf.fuel.core.Response


val Response.text : String
    get() = String(data)
