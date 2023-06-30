package dev.supasintatiyanupanwong.apps.android.bpi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.ui.CurrencyCodePickerDialog
import dev.supasintatiyanupanwong.apps.android.bpi.databinding.ActivityMainBinding
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FetchCurrentPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ObserveCurrentPriceOfSelectedCurrencyUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val fetchCurrentPriceUseCase: FetchCurrentPriceUseCase by inject()
    private val observeCurrentPriceOfSelectedCurrencyUseCase: ObserveCurrentPriceOfSelectedCurrencyUseCase by inject()

    private val formatPriceUseCase: FormatPriceUseCase by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(ActivityMainBinding::inflate)

        binding {
            setContentView(root)

            refreshLayout {
                setOnRefreshListener { this@MainActivity.invalidate() }
            }

            currencySelectButton {
                setOnClickListener { CurrencyCodePickerDialog.show(it.context) }
            }
        }

        observeCurrentPriceOfSelectedCurrencyUseCase()
            .flowWithLifecycle(lifecycle)
            .onEach {
                val price = it?.price

                binding {
                    conversionType {
                        text = "BTC/${price?.currency?.currencyCode}"
                    }

                    rate {
                        text = price?.formatToString() ?: "N/A"
                    }

                    conversionView {
                        sourcePrice = price
                    }
                }
            }
            .launchIn(lifecycleScope)

        invalidate()
    }

    private fun PriceInfo.formatToString(): String {
        return "${currency.symbol} ${formatPriceUseCase(value)}"
    }

    private fun invalidate() {
        lifecycleScope.launch {
            fetchCurrentPriceUseCase()

            binding {
                refreshLayout {
                    isRefreshing = false
                }
            }
        }
    }

}


inline operator fun <T> T.invoke(crossinline action: T.() -> Unit): T {
    action()
    return this
}
