package dev.supasintatiyanupanwong.apps.android.bpi.prices.ui

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class DecimalFormatTextWatcher(
    private val delegate: TextView,
    private val format: DecimalFormat,
    private val doAfterTextChanged: ((Editable?) -> Unit)? = null
) : TextWatcher {

    init {
        delegate.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(text: Editable?) {
        delegate.removeTextChangedListener(this)

        try {
            delegate.text = format.format(format.parse(text.toString()))
            doAfterTextChanged?.invoke(text)
            if (delegate is EditText) {
                delegate.setSelection(delegate.text.length)
            }
        } catch (_: Exception) {
        }

        delegate.addTextChangedListener(this)
    }
}
