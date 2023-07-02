package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.core.view.doOnPreDraw
import androidx.core.view.isInvisible
import androidx.core.view.updatePadding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dev.supasintatiyanupanwong.apps.android.bpi.base.ui.BaseActivity
import dev.supasintatiyanupanwong.apps.android.bpi.currencies.ui.CurrencyCodePickerDialog
import dev.supasintatiyanupanwong.apps.android.bpi.platform.android.attrColorOf
import dev.supasintatiyanupanwong.apps.android.bpi.platform.jvm.invoke
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.databinding.PricesActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PricesActivity : BaseActivity<PricesActivityBinding>() {

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

        viewModel.loadingHint.observe(this) {
            val isReady = !entries.isNullOrEmpty()

            // Using isInvisible instead of isVisible or isGone to prevent layout re-measurement.
            binding {
                progress {
                    isInvisible = !it || isReady
                }

                error {
                    isInvisible = it || isReady
                }

                content {
                    isInvisible = !isReady
                }
            }
        }

        viewModel.currentPriceOfSelectedCurrency.observe(this) {
            val price = it?.price

            binding {
                conversionType {
                    @SuppressLint("SetTextI18n") // Untranslatable text
                    text = "BTC/${price?.currency?.currencyCode}"
                }

                rate {
                    text = viewModel.formatPriceAsString(price)
                }

                updatedAt {
                    text = viewModel.formatDateTimeAsString(it?.timeMillis)
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
                        // Cast toInt() first to account only LSB to ensure plotting precision
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

                                color = Color.parseColor("#F7931A")
                            }
                    )
                    invalidate()
                }
            }
        }
    }

}
