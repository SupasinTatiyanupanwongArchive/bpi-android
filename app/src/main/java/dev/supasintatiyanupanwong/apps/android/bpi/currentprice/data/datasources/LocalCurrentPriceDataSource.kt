package dev.supasintatiyanupanwong.apps.android.bpi.currentprice.data.datasources

import dev.supasintatiyanupanwong.apps.android.bpi.currentprice.domain.models.PriceInfo

class LocalCurrentPriceDataSource {

    private var tmp: Pair<Long, List<PriceInfo>>? = null

    fun save(data: Pair<Long, List<PriceInfo>>?) {
        if (data == null) return

        tmp = data
    }

    fun peek(): Pair<Long, List<PriceInfo>>? {
        return tmp
    }

}
