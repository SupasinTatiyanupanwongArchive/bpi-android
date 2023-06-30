package dev.supasintatiyanupanwong.apps.android.bpi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.ui.CurrencyCodePickerDialog
import dev.supasintatiyanupanwong.apps.android.bpi.databinding.ActivityMainBinding
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.PricesViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PricesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(ActivityMainBinding::inflate)

        binding {
            setContentView(root)

            currencySelectButton {
                setOnClickListener { CurrencyCodePickerDialog.show(it.context) }
            }
        }

        viewModel.currentPriceOfSelectedCurrency()
            .flowWithLifecycle(lifecycle)
            .onEach {
                val price = it?.price

                binding {
                    conversionType {
                        text = "BTC/${price?.currency?.currencyCode}"
                    }

                    rate {
                        text = viewModel.formatPriceAsString(price)
                    }

                    conversionView {
                        sourcePrice = price
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

}


inline operator fun <T> T.invoke(crossinline action: T.() -> Unit): T {
    action()
    return this
}
