package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository

class ObserveCurrentPriceUseCase(private val pricesRepository: PricesRepository) {

    operator fun invoke() = pricesRepository.observeCurrentPrice()

}
