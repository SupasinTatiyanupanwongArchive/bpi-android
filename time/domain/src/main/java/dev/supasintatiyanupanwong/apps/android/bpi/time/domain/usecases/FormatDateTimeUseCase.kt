package dev.supasintatiyanupanwong.apps.android.bpi.time.domain.usecases

import java.text.SimpleDateFormat
import java.util.Locale

class FormatDateTimeUseCase {

    private val format = SimpleDateFormat("EEE, MMM dd, yyyy HH:mm", Locale.getDefault())

    operator fun invoke(timeMillis: Long) = format.format(timeMillis)!!

}
