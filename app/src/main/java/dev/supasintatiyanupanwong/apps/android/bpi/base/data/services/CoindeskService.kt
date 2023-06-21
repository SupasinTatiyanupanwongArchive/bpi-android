package dev.supasintatiyanupanwong.apps.android.bpi.base.data.services

class CoindeskService(provider: ClientProvider) {

    private val client = provider.provide("https://api.coindesk.com/v1/bpi/")

    fun <T> create(service: Class<T>): T = client.create(service)

}
