package ktxdev.b0mb3r

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ktxdev.b0mb3r.controller.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences("main", Context.MODE_PRIVATE)

        // textViewServices.text = textViewServices.text.toString() + services.size.toString()

        buttonRun.setOnClickListener {
            var dialog = Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar)

            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.argb(200, 255, 255, 255)))
            dialog.setContentView(R.layout.progress)

            dialog.show()

            var phone = editTextPhone.text.toString()
            //var country = editTextCountry.text.toString()
            var cycles = editTextCount.text.toString().toInt()

            var num = 0
            thread {
                Bomber(phone) {
                    subscribe {
                        when(it) {
                            is BomberFinished -> {
                                runOnUiThread {
                                    dialog.dismiss()
                                }
                            }
                            is SmsSent -> {
                                num++
                                runOnUiThread {
                                    dialog.textView2.text = "${num}/${this.getServices().size}"
                                }
                            }
                        }
                    }
                    start(cycles) // количество смс
                }
            }
        }
    }
}