package network.chaintech.cmpfilepickerdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_back
import cmpfilepickerdemo.composeapp.generated.resources.ic_delete
import cmpfilepickerdemo.composeapp.generated.resources.ic_images
import cmpfilepickerdemo.composeapp.generated.resources.ic_video_camera
import multiplatform.network.cmpfilepicker.models.SharedDocument
import multiplatform.network.cmpfilepicker.models.SharedVideo
import network.chaintech.cmpfilepickerdemo.utils.formatFileSize
import network.chaintech.cmpfilepickerdemo.utils.getDocumentTypeColor
import network.chaintech.cmpfilepickerdemo.utils.getDocumentTypeIcon
import network.chaintech.cmpfilepickerdemo.utils.getFileExtension
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

// Main Enhanced Media Display Screen matching the iOS-style design
@Composable
fun EnhancedMediaDisplayScreen1(
    imageList: List<ImageBitmap>,
    videoList: List<SharedVideo>,
    documentList: List<SharedDocument>,
    singleImage: ImageBitmap?,
    onVideoClick: (SharedVideo) -> Unit,
    onDocumentClick: (SharedDocument) -> Unit,
    onImageClick: (ImageBitmap) -> Unit,
    onBackToSelection: () -> Unit,
    onRemoveImage: ((Int) -> Unit)? = null,
    onRemoveVideo: ((SharedVideo) -> Unit)? = null,
    onRemoveDocument: ((SharedDocument) -> Unit)? = null,
    paddingValue: PaddingValues = PaddingValues()
) {
    // Calculate total items
    val totalImages = imageList.size + if (singleImage != null) 1 else 0
    val totalItems = totalImages + videoList.size + documentList.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7)) // iOS-style background
            .padding(paddingValue)
    ) {
        // Header matching the design
        CleanHeader(
            totalItems = totalItems,
            onBackClick = onBackToSelection
        )

        // Instruction text
        Text(
            text = "Tap on thumbnail to view the file",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8E8E93), // iOS secondary text color
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            textAlign = TextAlign.Center
        )

        // File List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(white),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            singleImage?.let { bitmap ->
                item {
                    FileItemCard(
                        thumbnail = {
                            ImageThumbnail(
                                bitmap = bitmap,
                                onClick = {
                                    onImageClick(bitmap)
                                }
                            )
                        },
                        title = "Image title",
                        subtitle = "Jpeg / 9.5 MB",
                        onRemove = { onRemoveImage?.invoke(0) },
                        onClick = {
                            onImageClick(bitmap)
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }

            // Image List
            items(imageList.withIndex().toList()) { (index, bitmap) ->
                FileItemCard(
                    thumbnail = {
                        ImageThumbnail(
                            bitmap = bitmap,
                            onClick = {
                                onImageClick(bitmap)
                            }
                        )
                    },
                    title = "Image title",
                    subtitle = "Jpeg / 9.5 MB",
                    onRemove = { onRemoveImage?.invoke(if (singleImage != null) index + 1 else index) },
                    onClick = {
                        onImageClick(bitmap)
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }

            // Video List
            items(videoList) { video ->
                FileItemCard(
                    thumbnail = {
                        VideoThumbnail(
                            video = video,
                            onClick = { onVideoClick(video) }
                        )
                    },
                    title = video.name.ifEmpty { "Video title" },
                    subtitle = "${video.mimeType.uppercase()} / ${formatFileSize(video.data?.size ?: 0)}",
                    onRemove = { onRemoveVideo?.invoke(video) },
                    onClick = {
                        onVideoClick(video)
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }

            // Document List
            items(documentList) { document ->
                FileItemCard(
                    thumbnail = {
                        DocumentThumbnail(
                            document = document,
                            onClick = { onDocumentClick(document) }
                        )
                    },
                    title = document.name.ifEmpty { "Document title" },
                    subtitle = "${
                        getFileExtension(
                            document.name,
                            document.mimeType
                        ).uppercase()
                    } / ${formatFileSize(document.size.toInt())}",
                    onRemove = { onRemoveDocument?.invoke(document) },
                    onClick = {
                        onDocumentClick(document)
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }

            // Empty state
            if (totalItems == 0) {
                item {
                    EmptyMediaState(onBackToSelection = onBackToSelection)
                }
            }
        }
    }
}

// Clean header matching iOS design
@Composable
fun CleanHeader(
    totalItems: Int,
    onBackClick: () -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(44.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back), // Replace with back arrow
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Title
            Text(
                text = "Selected Assets ($totalItems)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333)
            )
        }
    }
}

// File item card matching the design
@Composable
fun FileItemCard(
    thumbnail: @Composable () -> Unit,
    title: String,
    subtitle: String,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail
            Box(
                modifier = Modifier.size(60.dp)
            ) {
                thumbnail()
            }

            Spacer(modifier = Modifier.width(12.dp))

            // File info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8E8E93),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(Res.drawable.ic_delete), // Replace with trash/delete icon
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier.size(32.dp).clickable {
                    onRemove()
                }
            )
        }
    }
}

// Image thumbnail component
@Composable
fun ImageThumbnail(
    bitmap: ImageBitmap,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Image(
            bitmap = bitmap,
            contentDescription = "Image thumbnail",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Image overlay icon
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp)
                .size(16.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(3.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_images),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                tint = Color.White
            )
        }
    }
}

// Video thumbnail component
@Composable
fun VideoThumbnail(
    video: SharedVideo,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1C1C1E)) // Dark background for video
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Video icon
        Icon(
            painter = painterResource(Res.drawable.ic_video_camera),
            contentDescription = "Video thumbnail",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}

// Document thumbnail component
@OptIn(ExperimentalResourceApi::class)
@Composable
fun DocumentThumbnail(
    document: SharedDocument,
    onClick: () -> Unit
) {
    val documentColor = getDocumentTypeColor(document.mimeType)

    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(documentColor.copy(alpha = 0.15f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Document icon
        Icon(
            painter = painterResource(getDocumentTypeIcon(document.mimeType)),
            contentDescription = "Document thumbnail",
            modifier = Modifier.size(28.dp),
            tint = documentColor
        )

    }
}