@file:OptIn(ExperimentalMaterial3Api::class)package network.chaintech.cmpfilepickerdemo.ui.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_camera_black
import cmpfilepickerdemo.composeapp.generated.resources.ic_image_dailog
import cmpfilepickerdemo.composeapp.generated.resources.ic_txt_list
import cmpfilepickerdemo.composeapp.generated.resources.ic_video_camera
import network.chaintech.cmpfilepickerdemo.ui.typo.PS
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectImageBottomSheet(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGallerySingleClick: () -> Unit,
    onGalleryMultipleClick: () -> Unit
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
            containerColor = Color.Transparent, // Dark background like in image
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
            //windowInsets = WindowInsets(0)
        ) {
            SelectImageContent(
                onCameraClick = {
                    showBottomSheet = false
                    onCameraClick()
                },
                onGallerySingleClick = {
                    showBottomSheet = false
                    onGallerySingleClick()
                },
                onGalleryMultipleClick = {
                    showBottomSheet = false
                    onGalleryMultipleClick()
                }
            )
        }
    }
}

@Composable
private fun SelectImageContent(
    onCameraClick: () -> Unit,
    onGallerySingleClick: () -> Unit,
    onGalleryMultipleClick: () -> Unit
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
            .background(Color.White)// Account for safe area
    ) {
        // Title
        Text(
            text = "Select Image",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = PS(),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        // Options
        SelectImageOption(
            icon = Res.drawable.ic_camera_black,
            title = "Camera",
            onClick = onCameraClick,
            isFirst = true
        )

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(horizontal = 24.dp)
                .background(Color.White.copy(alpha = 0.1f))
        )

        SelectImageOption(
            icon = Res.drawable.ic_image_dailog,
            title = "Gallery (Single Photo)",
            onClick = onGallerySingleClick
        )

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(horizontal = 24.dp)
                .background(Color.White.copy(alpha = 0.1f))
        )

        SelectImageOption(
            icon = Res.drawable.ic_image_dailog,
            title = "Gallery (Multiple Photos)",
            onClick = onGalleryMultipleClick,
            isLast = true
        )
    }
}

@Composable
private fun SelectImageOption(
    icon: DrawableResource,
    title: String,
    onClick: () -> Unit,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = null,
                colorFilter = colorFilter,
//                colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f)),
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontFamily = PS(),
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectVideoBottomSheet(
    onDismiss: () -> Unit,
    onGallerySingleVideoClick: () -> Unit,
    onGalleryMultipleVideoClick: () -> Unit
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
            SelectVideoContent(
                onGallerySingleVideoClick = {
                    showBottomSheet = false
                    onGallerySingleVideoClick()
                },
                onGalleryMultipleVideoClick = {
                    showBottomSheet = false
                    onGalleryMultipleVideoClick()
                }
            )
        }
    }
}

@Composable
private fun SelectVideoContent(
    onGallerySingleVideoClick: () -> Unit,
    onGalleryMultipleVideoClick: () -> Unit
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
            text = "Select Video",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        // Options
        SelectImageOption(
            icon = Res.drawable.ic_video_camera, // You'll need to add this drawable resource
            title = "Gallery (Single Video)",
            onClick = onGallerySingleVideoClick,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f)),
            isFirst = true
        )

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(horizontal = 24.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        SelectImageOption(
            icon = Res.drawable.ic_video_camera, // You'll need to add this drawable resource
            title = "Gallery (Multiple Videos)",
            onClick = onGalleryMultipleVideoClick,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f)),
            isLast = true
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDocumentBottomSheet(
    onDismiss: () -> Unit,
    onGallerySingleFileClick: () -> Unit,
    onGalleryMultipleFileClick: () -> Unit
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
            SelectDocumentContent(
                onGallerySingleFileClick = {
                    showBottomSheet = false
                    onGallerySingleFileClick()
                },
                onGalleryMultipleFileClick = {
                    showBottomSheet = false
                    onGalleryMultipleFileClick()
                }
            )
        }
    }
}

@Composable
private fun SelectDocumentContent(
    onGallerySingleFileClick: () -> Unit,
    onGalleryMultipleFileClick: () -> Unit
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
            text = "Select Document",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        // Options
        SelectImageOption(
            icon = Res.drawable.ic_txt_list, // You'll need to add this drawable resource
            title = "Gallery (Single File)",
            onClick = onGallerySingleFileClick,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f)),

            isFirst = true
        )

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(horizontal = 24.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        SelectImageOption(
            icon = Res.drawable.ic_txt_list, // You'll need to add this drawable resource
            title = "Gallery (Multiple Files)",
            onClick = onGalleryMultipleFileClick,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.9f)),
            isLast = true
        )
    }
}