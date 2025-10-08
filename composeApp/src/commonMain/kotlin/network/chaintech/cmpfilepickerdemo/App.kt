package network.chaintech.cmpfilepickerdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_document
import cmpfilepickerdemo.composeapp.generated.resources.ic_image_dailog
import cmpfilepickerdemo.composeapp.generated.resources.ic_more_apps
import cmpfilepickerdemo.composeapp.generated.resources.ic_video_camera
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import multiplatform.network.cmpfilepicker.MediaPicker
import multiplatform.network.cmpfilepicker.MediaPickerState
import multiplatform.network.cmpfilepicker.externalviewer.openDocumentInExternalViewer
import multiplatform.network.cmpfilepicker.externalviewer.openVideoInExternalPlayer
import multiplatform.network.cmpfilepicker.models.SharedDocument
import multiplatform.network.cmpfilepicker.models.SharedVideo
import multiplatform.network.cmpfilepicker.permission.PermissionCallback
import multiplatform.network.cmpfilepicker.permission.PermissionStatus
import multiplatform.network.cmpfilepicker.permission.PermissionType
import multiplatform.network.cmpfilepicker.permission.createPermissionsManager
import multiplatform.network.cmpfilepicker.rememberMediaPickerState
import multiplatform.network.cmpfilepicker.utils.MediaResult
import network.chaintech.cmpfilepickerdemo.ui.AlertMessageDialog
import network.chaintech.cmpfilepickerdemo.ui.DocumentScreen
import network.chaintech.cmpfilepickerdemo.ui.EnhancedMediaDisplayScreen1
import network.chaintech.cmpfilepickerdemo.ui.HomeScreen
import network.chaintech.cmpfilepickerdemo.ui.SimpleImageViewer
import network.chaintech.cmpfilepickerdemo.ui.VideoPlayer
import network.chaintech.cmpfilepickerdemo.ui.bottomsheet.OpenWithBottomSheetFactory
import network.chaintech.cmpfilepickerdemo.ui.bottomsheet.SelectDocumentBottomSheet
import network.chaintech.cmpfilepickerdemo.ui.bottomsheet.SelectImageBottomSheet
import network.chaintech.cmpfilepickerdemo.ui.bottomsheet.SelectVideoBottomSheet
import org.jetbrains.compose.resources.ExperimentalResourceApi

// Enum to manage different screen states
enum class AppScreen {
    HOME,
    MEDIA_DISPLAY,
    VIDEO_PLAYER,

    IMAGE_VIEWER,
    DOCUMENT_VIEWER
}

// Data classes for better state management
data class MediaData(
    val images: List<ImageBitmap> = emptyList(),
    val videos: List<SharedVideo> = emptyList(),
    val documents: List<SharedDocument> = emptyList(),
    val singleImage: ImageBitmap? = null
) {
    val hasMedia: Boolean
        get() = images.isNotEmpty() || videos.isNotEmpty() || documents.isNotEmpty() || singleImage != null

    fun clear() = MediaData()
}

data class DialogState(
    val showPermissionDialog: Boolean = false,
    val showImageSelection: Boolean = false,
    val showVideoSelection: Boolean = false,
    val showDocumentSelection: Boolean = false,
    val showPhotoViewer: Boolean = false,
    val showVideoViewer: Boolean = false,
    val showDocumentViewer: Boolean = false,
    val launchSettings: Boolean = false
)

data class SelectedMedia(
    val imageBitmap: ImageBitmap? = null,
    val video: SharedVideo? = null,
    val document: SharedDocument? = null
)

const val maxSelectionCount = 3

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        Scaffold { paddingValues ->
            var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
            var mediaData by remember { mutableStateOf(MediaData()) }
            var dialogState by remember { mutableStateOf(DialogState()) }
            var selectedMedia by remember { mutableStateOf(SelectedMedia()) }

            val coroutineScope = rememberCoroutineScope()

            // Permission Manager
            val permissionsManager = createPermissionsManager(object : PermissionCallback {
                override fun onPermissionStatus(
                    permissionType: PermissionType,
                    status: PermissionStatus
                ) {
                    // Handle permission status if needed
                }
            })

            // Media Picker State
            val pickerState = rememberMediaPickerState()

            // Media Picker Component
            MediaPicker(
                state = pickerState,
                onResult = { result ->
                    when (result) {
                        is MediaResult.Image -> {
                            mediaData = mediaData.copy(
                                images = mediaData.images + result.bitmap
                            )
                            currentScreen = AppScreen.MEDIA_DISPLAY
                        }

                        is MediaResult.Video -> {
                            mediaData = mediaData.copy(
                                videos = mediaData.videos + result.video
                            )
                            currentScreen = AppScreen.MEDIA_DISPLAY
                        }

                        is MediaResult.Document -> {
                            mediaData = mediaData.copy(
                                documents = mediaData.documents + result.document
                            )
                            currentScreen = AppScreen.MEDIA_DISPLAY
                        }
                    }
                },
                onPermissionDenied = { deniedPermission ->
                    dialogState = dialogState.copy(showPermissionDialog = true)
                }
            )

            // Handle Settings Launch
            if (dialogState.launchSettings) {
                permissionsManager.launchSettings()
                dialogState = dialogState.copy(launchSettings = false)
            }

            // Main Content Based on Current Screen
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(paddingValues)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                when (currentScreen) {
                    AppScreen.HOME -> {
                        if (mediaData.hasMedia) {
                            EnhancedMediaDisplayScreen1(
                                imageList = mediaData.images.toMutableList(),
                                videoList = mediaData.videos.toMutableList(),
                                documentList = mediaData.documents.toMutableList(),
                                singleImage = mediaData.singleImage,
                                onVideoClick = { video ->
                                    selectedMedia = selectedMedia.copy(video = video)
                                    dialogState = dialogState.copy(showVideoViewer = true)
                                },
                                onDocumentClick = { document ->
                                    selectedMedia = selectedMedia.copy(document = document)
                                    dialogState = dialogState.copy(showDocumentViewer = true)
                                },
                                onImageClick = {
                                    selectedMedia = selectedMedia.copy(imageBitmap = it)
                                    currentScreen = AppScreen.IMAGE_VIEWER
                                },
                                onBackToSelection = {
                                    mediaData = mediaData.clear()
                                },
                                paddingValue = paddingValues
                            )
                        } else {
                            HomeScreen(
                                onImageGalleryClick = {
                                    dialogState = dialogState.copy(showImageSelection = true)
                                },
                                onVideoGalleryClick = {
                                    dialogState = dialogState.copy(showVideoSelection = true)
                                },
                                onDocumentPickerClick = {
                                    dialogState = dialogState.copy(showDocumentSelection = true)
                                },
                                onCameraClick = {
                                    pickerState.pickCamera()
                                },
                                onAnyTypeClick = {
                                    pickerState.pickMixed()
                                }
                            )
                        }
                    }

                    AppScreen.VIDEO_PLAYER -> {
                        selectedMedia.video?.let { video ->
                            VideoPlayer(
                                modifier = Modifier.fillMaxSize().padding(paddingValues),
                                mySharedVideo = video,
                                onBack = {
                                    currentScreen = AppScreen.HOME
                                    selectedMedia = selectedMedia.copy(video = null)
                                }
                            )
                        }
                    }

                    AppScreen.DOCUMENT_VIEWER -> {
                        selectedMedia.document?.let { document ->
                            DocumentScreen(
                                document = document,
                                onBack = {
                                    currentScreen = AppScreen.HOME
                                    selectedMedia = selectedMedia.copy(document = null)
                                }
                            )
                        }
                    }

                    AppScreen.IMAGE_VIEWER -> {
                        selectedMedia.imageBitmap?.let { imageBitmap ->
                            SimpleImageViewer(
                                modifier = Modifier.padding(paddingValues),
                                bitmap = imageBitmap,
                                onBack = {
                                    currentScreen = AppScreen.HOME
                                    selectedMedia = selectedMedia.copy(imageBitmap = null)
                                }
                            )
                        }
                    }

                    AppScreen.MEDIA_DISPLAY -> {
                        // This case is handled in HOME screen
                        currentScreen = AppScreen.HOME
                    }
                }
            }

            // Dialog Management
            DialogManager(
                dialogState = dialogState,
                selectedMedia = selectedMedia,
                onDialogStateChange = { newState -> dialogState = newState },
                onSelectedMediaChange = { newSelected -> selectedMedia = newSelected },
                onScreenChange = { newScreen -> currentScreen = newScreen },
                onLaunchSettings = {
                    dialogState =
                        dialogState.copy(launchSettings = true, showPermissionDialog = false)
                },
                pickerState = pickerState,
                coroutineScope = coroutineScope
            )
        }
    }
}

@Composable
private fun DialogManager(
    dialogState: DialogState,
    selectedMedia: SelectedMedia,
    onDialogStateChange: (DialogState) -> Unit,
    onSelectedMediaChange: (SelectedMedia) -> Unit,
    onScreenChange: (AppScreen) -> Unit,
    onLaunchSettings: () -> Unit,
    pickerState: MediaPickerState, // Replace with actual picker state type
    coroutineScope: CoroutineScope
) {
    // Permission Dialog
    if (dialogState.showPermissionDialog) {
        AlertMessageDialog(
            title = "Permission Required",
            message = "To access media files, please grant this permission. You can manage permissions in your device settings.",
            positiveButtonText = "Settings",
            negativeButtonText = "Cancel",
            onPositiveClick = { onLaunchSettings() },
            onNegativeClick = {
                onDialogStateChange(dialogState.copy(showPermissionDialog = false))
            }
        )
    }

    // Selection Bottom Sheets
    if (dialogState.showImageSelection) {
        SelectImageBottomSheet(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showImageSelection = false))
            },
            onCameraClick = {
                pickerState.pickCamera()
                onDialogStateChange(dialogState.copy(showImageSelection = false))
            },
            onGallerySingleClick = {
                pickerState.pickImage()
                onDialogStateChange(dialogState.copy(showImageSelection = false))
            },
            onGalleryMultipleClick = {
                pickerState.pickImage(maxCount = maxSelectionCount)
                onDialogStateChange(dialogState.copy(showImageSelection = false))
            }
        )
    }

    if (dialogState.showVideoSelection) {
        SelectVideoBottomSheet(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showVideoSelection = false))
            },
            onGallerySingleVideoClick = {
                pickerState.pickVideo()
                onDialogStateChange(dialogState.copy(showVideoSelection = false))
            },
            onGalleryMultipleVideoClick = {
                pickerState.pickVideo(maxCount = maxSelectionCount)
                onDialogStateChange(dialogState.copy(showVideoSelection = false))
            }
        )
    }

    if (dialogState.showDocumentSelection) {
        SelectDocumentBottomSheet(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showDocumentSelection = false))
            },
            onGallerySingleFileClick = {
                pickerState.pickDocument(isSingle = true)
                onDialogStateChange(dialogState.copy(showDocumentSelection = false))
            },
            onGalleryMultipleFileClick = {
                pickerState.pickDocument()
                onDialogStateChange(dialogState.copy(showDocumentSelection = false))
            }
        )
    }

    // Viewer Bottom Sheets
    if (dialogState.showPhotoViewer) {
        OpenWithBottomSheetFactory.ForPhoto(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showPhotoViewer = false))
            },
            onPhotoViewerClick = {
                onDialogStateChange(dialogState.copy(showPhotoViewer = false))
                // Handle internal photo viewing
            },
            onMoreAppsClick = {
                onDialogStateChange(dialogState.copy(showPhotoViewer = false))
                // Handle external photo viewing
            },
            photoViewerIcon = Res.drawable.ic_image_dailog,
            moreAppsIcon = Res.drawable.ic_more_apps
        )
    }

    if (dialogState.showVideoViewer) {
        OpenWithBottomSheetFactory.ForVideo(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showVideoViewer = false))
                onSelectedMediaChange(selectedMedia.copy(video = null))
            },
            onVideoPlayerClick = {
                onDialogStateChange(dialogState.copy(showVideoViewer = false))
                onScreenChange(AppScreen.VIDEO_PLAYER)
            },
            onMoreAppsClick = {
                onDialogStateChange(dialogState.copy(showVideoViewer = false))
                coroutineScope.launch {
                    selectedMedia.video?.let { video ->
                        openVideoInExternalPlayer(video)
                    }
                }
                onSelectedMediaChange(selectedMedia.copy(video = null))
            },
            videoPlayerIcon = Res.drawable.ic_video_camera,
            moreAppsIcon = Res.drawable.ic_more_apps
        )
    }

    if (dialogState.showDocumentViewer) {
        OpenWithBottomSheetFactory.ForDocument(
            onDismiss = {
                onDialogStateChange(dialogState.copy(showDocumentViewer = false))
                onSelectedMediaChange(selectedMedia.copy(document = null))
            },
            onDocViewerClick = {
                onDialogStateChange(dialogState.copy(showDocumentViewer = false))
                onScreenChange(AppScreen.DOCUMENT_VIEWER)
            },
            onMoreAppsClick = {
                onDialogStateChange(dialogState.copy(showDocumentViewer = false))
                coroutineScope.launch {
                    selectedMedia.document?.let { document ->
                        openDocumentInExternalViewer(document)
                    }
                }
                onSelectedMediaChange(selectedMedia.copy(document = null))
            },
            docViewerIcon = Res.drawable.ic_document,
            moreAppsIcon = Res.drawable.ic_more_apps
        )
    }
}