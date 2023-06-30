package dev.supasintatiyanupanwong.apps.android.bpi

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.view.doOnPreDraw
import androidx.core.view.updatePadding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dev.supasintatiyanupanwong.apps.android.bpi.base.ui.BaseActivity
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.ui.CurrencyCodePickerDialog
import dev.supasintatiyanupanwong.apps.android.bpi.databinding.ActivityMainBinding
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.PricesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO PricesActivity
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: PricesViewModel by viewModel()

    private var entries: List<Entry>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            bottomBar {
                doOnPreDraw {
                    scroll.updatePadding(bottom = it.height)
                }
            }

            chart {
                isScaleXEnabled = false
                isScaleYEnabled = false
                isDoubleTapToZoomEnabled = false

                description {
                    isEnabled = false
                }

                legend {
                    isEnabled = false
                }

                xAxis {
                    isEnabled = true
                    textColor = attrColorOf(android.R.attr.textColorPrimary)

                    setDrawLabels(false)
                    setDrawGridLines(false)

                    position = XAxis.XAxisPosition.BOTTOM
                }

                axisLeft {
                    isEnabled = true
                    textColor = attrColorOf(android.R.attr.textColorPrimary)
                }

                axisRight {
                    isEnabled = false
                }
            }

            currencySelectButton {
                setOnClickListener { CurrencyCodePickerDialog.show(it.context) }
            }
        }

        viewModel.currentPriceOfSelectedCurrency.observe(this) {
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

        viewModel.priceRecordsOfSelectedCurrency.observe(this) {
            entries = it.orEmpty()
                .map { record ->
                    Entry(
                        // Cast toInt() first to take only LSB to ensure plotting precision
                        record.timeMillis.toInt().toFloat(),
                        record.price.value.toFloat()
                    )
                }

            binding {
                chart {
                    data = LineData(
                        LineDataSet(entries, null)
                            .apply {
                                setDrawValues(false)
                                setDrawCircles(false)

                                mode = LineDataSet.Mode.CUBIC_BEZIER
                                color = Color.parseColor("#F7931A")
                            }
                    )
                    invalidate()
                }
            }
        }
    }

}


@ColorInt
fun Context.attrColorOf(@AttrRes attrId: Int): Int {
    val arr = obtainStyledAttributes(null, intArrayOf(attrId))
    val color = arr.getColor(0, Color.TRANSPARENT)
    arr.recycle()
    return color
}

// Mimicking DSL
inline operator fun <T> T.invoke(crossinline action: T.() -> Unit): T {
    action()
    return this
}
