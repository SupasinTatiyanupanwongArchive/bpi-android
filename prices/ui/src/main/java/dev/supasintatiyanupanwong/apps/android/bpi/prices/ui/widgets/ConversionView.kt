package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import dev.supasintatiyanupanwong.apps.android.bpi.platform.android.attach
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases.ParsePriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.PriceFormatTextWatcher
import dev.supasintatiyanupanwong.apps.android.bpi.prices.ui.databinding.PricesConversionViewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.math.RoundingMode
import kotlin.properties.Delegates

class ConversionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), KoinComponent {

    private val binding = attach(PricesConversionViewBinding::inflate)

    private val formatPriceUseCase: FormatPriceUseCase by inject()
    private val parsePriceUseCase: ParsePriceUseCase by inject()

    var sourcePrice by Delegates.observable<PriceInfo?>(null) { _, _, _ ->
        invalidateSourcePriceView()
        invalidateDestinationPriceView()
    }

    init {
        orientation = VERTICAL

        binding.src.addTextChangedListener(
            PriceFormatTextWatcher(binding.src, formatPriceUseCase, parsePriceUseCase) {
                invalidateDestinationPriceView()
            }
        )
    }

    private fun invalidateSourcePriceView() {
        binding.srcLayout.suffixText = sourcePrice?.currency?.currencyCode
    }

    private fun invalidateDestinationPriceView() {
        val srcString = binding.src.text?.toString()

        if (srcString.isNullOrBlank()) {
            binding.src.setText("0")
            return
        }

        val src = parsePriceUseCase(srcString)
        val price = sourcePrice?.value
        binding.dst.setText(
            if (price == null) {
                "NaN"
            } else {
                formatPriceUseCase(src.divide(price, 2, RoundingMode.HALF_EVEN))
            }
        )
    }

}
