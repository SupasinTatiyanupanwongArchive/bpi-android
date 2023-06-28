package dev.supasintatiyanupanwong.apps.android.bpi.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.prices.data.PricesRepository

class FetchCurrentPriceUseCase(private val pricesRepository: PricesRepository) {

    suspend operator fun invoke() = pricesRepository.fetchCurrentPrice()

}
