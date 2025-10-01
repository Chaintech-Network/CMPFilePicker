package network.chaintech.cmpfilepickerdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.ic_back
import multiplatform.network.cmpfilepicker.inappviewer.DocumentViewer
import multiplatform.network.cmpfilepicker.inappviewer.InAppVideoPlayer
import multiplatform.network.cmpfilepicker.models.SharedDocument
import multiplatform.network.cmpfilepicker.models.SharedVideo
import org.jetbrains.compose.resources.painterResource

@Composable
fun VideoPlayer(modifier: Modifier, mySharedVideo: SharedVideo, onBack: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Back button in top row
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier.size(16.dp).clickable {
                    onBack()
                },
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("Playing video: ${mySharedVideo.name}")
        }
        InAppVideoPlayer(video = mySharedVideo, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun DocumentScreen(document: SharedDocument, onBack : () -> Unit) {
    DocumentViewer(document = document, modifier = Modifier.fillMaxSize(), onBack =onBack)
}