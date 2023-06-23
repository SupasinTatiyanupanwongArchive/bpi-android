package dev.supasintatiyanupanwong.apps.android.bpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.CurrentPriceRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val currentPriceRepository: CurrentPriceRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            currentPriceRepository.fetchCurrentPrice()

            findViewById<TextView>(R.id.text)
                .text = currentPriceRepository.getCurrentPrice().toString()
        }
    }

}
