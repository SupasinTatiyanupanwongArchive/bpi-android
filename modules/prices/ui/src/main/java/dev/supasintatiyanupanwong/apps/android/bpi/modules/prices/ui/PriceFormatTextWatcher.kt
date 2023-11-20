package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.ui

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.FormatPriceUseCase
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases.ParsePriceUseCase

class PriceFormatTextWatcher(
    private val delegate: TextView,
    private val formatPriceUseCase: FormatPriceUseCase,
    private val parsePriceUseCase: ParsePriceUseCase,
    private val doAfterTextChanged: ((Editable?) -> Unit)? = null
) : TextWatcher {

    init {
        delegate.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(text: Editable?) {
        text ?: return

        delegate.removeTextChangedListener(this)

        try {
            delegate.text = formatPriceUseCase(parsePriceUseCase(text.toString()))
            doAfterTextChanged?.invoke(text)
            if (delegate is EditText) {
                delegate.setSelection(delegate.text.length)
            }
        } catch (_: Exception) {
        }

        delegate.addTextChangedListener(this)
    }

}
