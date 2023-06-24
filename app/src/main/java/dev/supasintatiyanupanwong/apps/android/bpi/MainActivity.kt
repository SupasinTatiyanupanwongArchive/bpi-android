package dev.supasintatiyanupanwong.apps.android.bpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val pricesRepository: PricesRepository by inject()

    private val format = DecimalFormat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        pricesRepository.observeCurrentPrice()
            .flowWithLifecycle(lifecycle)
            .onEach { record ->
                val firstPrice = record?.prices?.getOrNull(0)
                findViewById<TextView>(R.id.conversion_type).text =
                    "BTC/${firstPrice?.currency?.currencyCode}"
                findViewById<TextView>(R.id.price).text =
                    firstPrice?.let { "${it.currency.symbol} ${format.format(it.value)}" }
            }
            .launchIn(lifecycleScope)

        findViewById<SwipeRefreshLayout>(R.id.refresh_layout).setOnRefreshListener { invalidate() }

        invalidate()
    }

    private fun invalidate() {
        lifecycleScope.launch {
            pricesRepository.fetchCurrentPrice()
            findViewById<SwipeRefreshLayout>(R.id.refresh_layout).isRefreshing = false
        }
    }

}
