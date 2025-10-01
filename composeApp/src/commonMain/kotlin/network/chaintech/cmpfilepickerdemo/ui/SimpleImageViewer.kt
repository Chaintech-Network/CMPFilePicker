package network.chaintech.cmpfilepickerdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun SimpleImageViewer(
    modifier: Modifier = Modifier,
    bitmap: ImageBitmap,
    onBack: () -> Unit,
    imageTitle: String = "Image"
) {
    Column(
        modifier = modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onBack() },
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Viewing image: $imageTitle (${bitmap.width} x ${bitmap.height})  ${bitmap.config.value}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Simple image display
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}