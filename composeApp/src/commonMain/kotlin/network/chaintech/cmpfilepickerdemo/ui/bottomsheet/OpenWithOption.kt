package network.chaintech.cmpfilepickerdemo.ui.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.chaintech.cmpfilepickerdemo.ui.typo.FontSfPro
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class OpenWithOption(
    val icon: DrawableResource,
    val title: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenWithBottomSheet(
    onDismiss: () -> Unit,
    options: List<OpenWithOption>
) {
    var showBottomSheet by remember { mutableStateOf(true) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                onDismiss()
            },
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            containerColor = Color.Transparent,
            contentColor = Color.White,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 8.dp)
                        .width(50.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(2.5.dp))
                        .background(Color.White)
                )
            },
        ) {
            OpenWithContent(
                options = options,
                onOptionClick = { option ->
                    showBottomSheet = false
                    option.onClick()
                }
            )
        }
    }
}

@Composable
private fun OpenWithContent(
    options: List<OpenWithOption>,
    onOptionClick: (OpenWithOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .padding(bottom = 34.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .background(Color.White)
    ) {
        // Title
        Text(
            text = "Open with",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontFamily = FontSfPro(),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        // Options Grid (2 columns)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEachIndexed { index, option ->
                OpenWithOptionItem(
                    option = option,
                    onClick = { onOptionClick(option) },
                    modifier = Modifier.weight(1f)
                )
                
                // Add spacing between items (except for last item)
                if (index < options.size - 1) {
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
    }
}

@Composable
private fun OpenWithOptionItem(
    option: OpenWithOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon
        Image(
            painter = painterResource(option.icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f))
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = option.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontFamily = FontSfPro(),
            textAlign = TextAlign.Center
        )
    }
}

// Predefined factory functions for common use cases
object OpenWithBottomSheetFactory {
    
    @Composable
    fun ForPhoto(
        onDismiss: () -> Unit,
        onPhotoViewerClick: () -> Unit,
        onMoreAppsClick: () -> Unit,
        photoViewerIcon: DrawableResource,
        moreAppsIcon: DrawableResource
    ) {
        OpenWithBottomSheet(
            onDismiss = onDismiss,
            options = listOf(
                OpenWithOption(
                    icon = photoViewerIcon,
                    title = "Photo Viewer",
                    onClick = onPhotoViewerClick
                ),
                OpenWithOption(
                    icon = moreAppsIcon,
                    title = "More Apps",
                    onClick = onMoreAppsClick
                )
            )
        )
    }

    @Composable
    fun ForVideo(
        onDismiss: () -> Unit,
        onVideoPlayerClick: () -> Unit,
        onMoreAppsClick: () -> Unit,
        videoPlayerIcon: DrawableResource,
        moreAppsIcon: DrawableResource
    ) {
        OpenWithBottomSheet(
            onDismiss = onDismiss,
            options = listOf(
                OpenWithOption(
                    icon = videoPlayerIcon,
                    title = "Video Player",
                    onClick = onVideoPlayerClick
                ),
                OpenWithOption(
                    icon = moreAppsIcon,
                    title = "More Apps",
                    onClick = onMoreAppsClick
                )
            )
        )
    }

    @Composable
    fun ForDocument(
        onDismiss: () -> Unit,
        onDocViewerClick: () -> Unit,
        onMoreAppsClick: () -> Unit,
        docViewerIcon: DrawableResource,
        moreAppsIcon: DrawableResource
    ) {
        OpenWithBottomSheet(
            onDismiss = onDismiss,
            options = listOf(
                OpenWithOption(
                    icon = docViewerIcon,
                    title = "Doc Viewer",
                    onClick = onDocViewerClick
                ),
                OpenWithOption(
                    icon = moreAppsIcon,
                    title = "More Apps",
                    onClick = onMoreAppsClick
                )
            )
        )
    }
}