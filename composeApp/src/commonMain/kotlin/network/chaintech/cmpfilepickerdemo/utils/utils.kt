package network.chaintech.cmpfilepickerdemo.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_pdf_list
import cmpfilepickerdemo.composeapp.generated.resources.ic_txt_list
import cmpfilepickerdemo.composeapp.generated.resources.ic_word_list
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.math.roundToInt

// Helper functions
@Composable
fun getDocumentTypeColor(mimeType: String?): Color {
    return when {
        mimeType?.contains("pdf") == true -> Color(0xFFE53E3E) // Red for PDF
        mimeType?.contains("word") == true || mimeType?.contains("document") == true -> Color(
            0xFF2B6CB0
        ) // Blue for Word
        mimeType?.contains("sheet") == true || mimeType?.contains("excel") == true -> Color(
            0xFF38A169
        ) // Green for Excel
        mimeType?.contains("presentation") == true || mimeType?.contains("powerpoint") == true -> Color(
            0xFFD69E2E
        ) // Orange for PowerPoint
        mimeType?.contains("text") == true -> Color(0xFF805AD5) // Purple for text files
        else -> MaterialTheme.colorScheme.primary
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getDocumentTypeIcon(mimeType: String?): DrawableResource {
    return when {
        mimeType?.contains("pdf") == true -> Res.drawable.ic_pdf_list
        mimeType?.contains("word") == true || mimeType?.contains("document") == true -> Res.drawable.ic_word_list
        mimeType?.contains("sheet") == true || mimeType?.contains("excel") == true -> Res.drawable.ic_txt_list
        mimeType?.contains("presentation") == true || mimeType?.contains("powerpoint") == true -> Res.drawable.ic_txt_list
        mimeType?.contains("text") == true -> Res.drawable.ic_txt_list
        else -> Res.drawable.ic_txt_list
    }
}

fun getFileExtension(fileName: String, mimeType: String?): String {
    val extension = fileName.substringAfterLast(".", "")
    if (extension.isNotEmpty()) return extension

    return when (mimeType) {
        "application/pdf" -> "PDF"
        "application/msword" -> "DOC"
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> "DOCX"
        "application/vnd.ms-excel" -> "XLS"
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> "XLSX"
        "text/plain" -> "TXT"
        else -> "FILE"
    }
}

fun formatFileSize(bytes: Int): String {
    return when {
        bytes < 1024 -> "$bytes B"
        bytes < 1024 * 1024 -> {
            val kb = bytes / 1024.0
            "${(kb * 10).roundToInt() / 10.0} KB"
        }

        bytes < 1024 * 1024 * 1024 -> {
            val mb = bytes / (1024.0 * 1024.0)
            "${(mb * 10).roundToInt() / 10.0} MB"
        }

        else -> {
            val gb = bytes / (1024.0 * 1024.0 * 1024.0)
            "${(gb * 10).roundToInt() / 10.0} GB"
        }
    }
}