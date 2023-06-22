package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models.PriceInfo

class LocalCurrentPriceDataSource {

    private var tmp: List<PriceInfo>? = null

    fun save(data: List<PriceInfo>) {
        tmp = data
    }

    fun peek(): List<PriceInfo>? {
        return tmp
    }

}
