package ktxdev.b0mb3r

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress.*
import ktxdev.b0mb3r.services.AistTaxi
import ktxdev.b0mb3r.services.AlfaLife
import ktxdev.b0mb3r.services.IziUA
import ktxdev.b0mb3r.services.Taxi3040
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var services = listOf<BaseService>(
        Taxi3040(),
        AistTaxi(),
        AlfaLife(),
        IziUA()
    )

    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences("main", Context.MODE_PRIVATE)

        textViewServices.text = textViewServices.text.toString() + services.size.toString()

        buttonRun.setOnClickListener {
            var dialog = Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar)

            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.argb(200, 255, 255, 255)))
            dialog.setContentView(R.layout.progress)

            dialog.show()

            var phone = editTextPhone.text.toString()
            var country = editTextCountry.text.toString()
            var cycles = editTextCount.text.toString().toInt()

            thread {
                var json = ProxyList.get(cycles)
                pref!!.edit().putString("proxys", json.toString()).apply()

                var i = 0
                while(i < cycles) {
                    println("(:DEBUG) Проход #${i}")
                    var num = 0
                    services.forEach {
                        num++
                        it.onRun(country, phone)

                        runOnUiThread { dialog.textView2.text = "${num}/${services.size}" }
                        Thread.sleep(1000)
                    }

                    i++
                }

                runOnUiThread {
                    dialog.dismiss()
                }
            }
        }
    }
}