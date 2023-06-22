package dev.supasintatiyanupanwong.apps.android.bpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.ClientProvider
import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.CoindeskService
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.apis.CurrentPriceApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val json = CoindeskService(ClientProvider()).create(CurrentPriceApi::class.java)
                .getCurrentPrice()

            findViewById<TextView>(R.id.text).text = json.toString()
        }
    }

}
