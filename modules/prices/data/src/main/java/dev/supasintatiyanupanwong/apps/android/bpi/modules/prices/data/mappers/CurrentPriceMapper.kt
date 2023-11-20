package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.mappers

import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.models.CurrentPriceJson
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.models.PriceJson
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.models.PriceInfo
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.models.PricesRecord
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.ParsePriceUseCase
import java.util.Currency

class CurrentPriceMapper(private val parsePriceUseCase: ParsePriceUseCase) {

    fun transform(json: CurrentPriceJson?): PricesRecord? {
        json ?: return null

        val timeMillis = json.time?.updatedAt ?: return null
        val disclaimer = json.disclaimer
        val bpi = json.bpi ?: return null

        val prices = mutableListOf<PriceInfo>().apply {
            transformPriceJsonToPriceDomain(bpi.usd)?.also { this += it }
            transformPriceJsonToPriceDomain(bpi.gbp)?.also { this += it }
            transformPriceJsonToPriceDomain(bpi.eur)?.also { this += it }
        }
        if (prices.isEmpty()) return null

        return PricesRecord(
            timeMillis = timeMillis,
            disclaimer = disclaimer,
            prices = prices
        )
    }

    private fun transformPriceJsonToPriceDomain(json: PriceJson?): PriceInfo? {
        json ?: return null

        val code = json.code ?: return null
        val rate = json.rate ?: return null

        return try {
            PriceInfo(Currency.getInstance(code), parsePriceUseCase(rate))
        } catch (ex: Exception) {
            null // Fail-safe for invalid code from API
        }
    }

}
