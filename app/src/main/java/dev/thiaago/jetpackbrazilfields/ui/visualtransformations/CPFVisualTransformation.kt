package dev.thiaago.jetpackbrazilfields.ui.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CPFVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text.mapIndexed { index, char ->
            when(index) {
                3, 6 -> ".$char"
                9 -> "-$char"
                else -> char
            }
        }.joinToString(separator = "")

        val annotatedString = AnnotatedString(formattedText)

        return TransformedText(text = annotatedString, offsetMapping = CPFOffsetMapping())
    }
}

class CPFOffsetMapping: OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = when {
        offset >= 10 -> offset + 3
        offset >= 7 -> offset + 2
        offset >= 4 -> offset + 1
        else -> offset
    }

    override fun transformedToOriginal(offset: Int): Int = when {
        offset >= 10 -> offset - 3
        offset >= 7 -> offset - 2
        offset >= 4 -> offset - 1
        else -> offset
    }
}