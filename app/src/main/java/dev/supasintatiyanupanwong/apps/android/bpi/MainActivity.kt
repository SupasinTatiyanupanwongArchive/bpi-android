package dev.supasintatiyanupanwong.apps.android.bpi

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.domain.usecases.ObserveSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.ui.CurrencyCodePickerDialog
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.widgets.ConversionView
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val fetchCurrentPriceUseCase: FetchCurrentPriceUseCase by inject()
    private val observeCurrentPriceUseCase: ObserveCurrentPriceUseCase by inject()

    private val observeSelectedCurrencyCodeUseCase: ObserveSelectedCurrencyCodeUseCase by inject()

    private val formatPriceUseCase: FormatPriceUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        observeCurrentPriceUseCase()
            .combine(observeSelectedCurrencyCodeUseCase()) { record, currencyCode ->
                record?.prices?.find { it.currency.currencyCode == currencyCode }
            }
            .flowWithLifecycle(lifecycle)
            .onEach { price ->
                findViewById<TextView>(R.id.conversion_type).text =
                    "BTC/${price?.currency?.currencyCode}"
                findViewById<TextView>(R.id.price).text =
                    price?.let { "${it.currency.symbol} ${formatPriceUseCase(it.value)}" }

                findViewById<ConversionView>(R.id.conversion_view).sourcePrice = price
            }
            .launchIn(lifecycleScope)

        findViewById<SwipeRefreshLayout>(R.id.refresh_layout).setOnRefreshListener { invalidate() }

        findViewById<View>(R.id.currency_select).setOnClickListener { CurrencyCodePickerDialog.show(this) }

        invalidate()
    }

    private fun invalidate() {
        lifecycleScope.launch {
            fetchCurrentPriceUseCase()
            findViewById<SwipeRefreshLayout>(R.id.refresh_layout).isRefreshing = false
        }
    }

}
