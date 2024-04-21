package dev.thiaago.jetpackbrazilfields.ui.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormatSymbols
import java.util.Locale

class MoneyVisualTransformation(private val currencySymbol: String? = "") : VisualTransformation {
    private val symbols = DecimalFormatSymbols.getInstance(Locale("pt", "br"))

    override fun filter(text: AnnotatedString): TransformedText {
        val thousandsSeparator = symbols.groupingSeparator
        val decimalSeparator = symbols.decimalSeparator
        val zero = symbols.zeroDigit

        val inputText = text.text

        val intPart = inputText
            .dropLast(2)
            .reversed()
            .chunked(3)
            .joinToString(thousandsSeparator.toString())
            .reversed()
            .ifEmpty {
                zero.toString()
            }

        val fractionPart = inputText.takeLast(2).let {
            if (it.length != 2) {
                List(2 - it.length) {
                    zero
                }.joinToString("") + it
            } else {
                it
            }
        }

        var formattedNumber = intPart + decimalSeparator + fractionPart
        if(currencySymbol?.isNotEmpty() == true) {
            formattedNumber = "$currencySymbol $formattedNumber"
        }

        val newText = AnnotatedString(text = formattedNumber)

        val offsetMapping = MoneyOffsetMapping(
            contentLength = text.length,
            formattedLength = newText.length
        )

        return TransformedText(newText, offsetMapping)
    }
}

private class MoneyOffsetMapping(private val contentLength: Int, private val formattedLength: Int) :
    OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = formattedLength
    override fun transformedToOriginal(offset: Int): Int = contentLength

}