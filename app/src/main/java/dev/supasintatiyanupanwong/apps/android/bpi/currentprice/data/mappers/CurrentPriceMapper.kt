package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.mappers

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models.CurrentPriceJson
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.models.PriceJson
import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models.PriceInfo
import java.util.Currency

class CurrentPriceMapper {

    fun transform(json: CurrentPriceJson?): List<PriceInfo> {
        json ?: return emptyList()

        val bpi = json.bpi ?: return emptyList()

        return mutableListOf<PriceInfo>().apply {
            transformPriceJsonToPriceDomain(bpi.usd)?.also { this += it }
            transformPriceJsonToPriceDomain(bpi.gbp)?.also { this += it }
            transformPriceJsonToPriceDomain(bpi.eur)?.also { this += it }
        }
    }

    private fun transformPriceJsonToPriceDomain(json: PriceJson?): PriceInfo? {
        json ?: return null

        val code = json.code ?: return null
        val rate = json.rate ?: return null

        return try {
            PriceInfo(Currency.getInstance(code), rate)
        } catch (ex: Exception) {
            null // Fail-safe for invalid code from API
        }
    }

}
