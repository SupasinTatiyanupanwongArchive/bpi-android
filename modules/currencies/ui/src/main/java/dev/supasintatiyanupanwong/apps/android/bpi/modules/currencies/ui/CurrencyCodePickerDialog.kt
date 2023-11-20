package dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.ui

import android.content.Context
import android.content.DialogInterface
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.GetCurrenciesUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.GetSelectedCurrencyCodeUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.currencies.domain.usecases.SetSelectedCurrencyCodeUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object CurrencyCodePickerDialog : KoinComponent {

    private val getCurrenciesUseCase: GetCurrenciesUseCase by inject()

    private val getSelectedCurrencyCodeUseCase: GetSelectedCurrencyCodeUseCase by inject()
    private val setSelectedCurrencyCodeUseCase: SetSelectedCurrencyCodeUseCase by inject()

    private var isShowing = false // Prevent multiple instances of dialog in case of spamming

    fun show(context: Context) {
        if (isShowing) return
        isShowing = true

        val currencies = getCurrenciesUseCase().orEmpty()

        val items = currencies.map { it.currencyCode }.toTypedArray()
        val initCheckedItem = currencies
            .indexOfFirst { it.currencyCode == getSelectedCurrencyCodeUseCase() }
        var currCheckedItem = initCheckedItem

        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.currency_code_picker_dialog_title)
            .setSingleChoiceItems(items, initCheckedItem) { dialog, checkedItem ->
                currCheckedItem = checkedItem
                dialog.getPositiveButton()?.isEnabled = checkedItem != -1
            }
            .setPositiveButton(
                if (items.isEmpty()) null else context.getString(android.R.string.ok),
                null
            )
            .setNegativeButton(android.R.string.cancel, null)
            .create()

        dialog.setOnShowListener { it.getPositiveButton()?.isEnabled = initCheckedItem != -1 }
        dialog.setOnDismissListener {
            if (currCheckedItem != initCheckedItem) {
                setSelectedCurrencyCodeUseCase(items.getOrNull(currCheckedItem))
            }
            isShowing = false
        }
        dialog.show()
    }

}


fun DialogInterface.getPositiveButton(): Button? {
    return (this as? AlertDialog)?.getButton(DialogInterface.BUTTON_POSITIVE)
}
