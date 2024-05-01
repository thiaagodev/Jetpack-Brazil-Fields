package dev.thiaago.jetpackbrazilfields.ui.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CNPJVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // 68.094.776/0001-51
        val formattedText = text.text.mapIndexed { index, char ->
            when(index) {
                2, 5 -> ".$char"
                8 -> "/$char"
                12 -> "-$char"
                else -> char
            }
        }.joinToString(separator = "")

        val annotatedString = AnnotatedString(formattedText)

        return TransformedText(text = annotatedString, offsetMapping = CNPJOffsetMapping())
    }
}

class CNPJOffsetMapping: OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = when {
        offset >= 13 -> offset + 4
        offset >= 9 -> offset + 3
        offset >= 6 -> offset + 2
        offset >= 3 -> offset + 1
        else -> offset
    }

    override fun transformedToOriginal(offset: Int): Int = when {
        offset >= 13 -> offset - 4
        offset >= 9 -> offset - 3
        offset >= 6 -> offset - 2
        offset >= 3 -> offset +- 1
        else -> offset
    }
}