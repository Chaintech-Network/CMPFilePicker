package network.chaintech.cmpfilepickerdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.dashboard_bg
import cmpfilepickerdemo.composeapp.generated.resources.ic_anytype
import cmpfilepickerdemo.composeapp.generated.resources.ic_arrow
import cmpfilepickerdemo.composeapp.generated.resources.ic_camera_new
import cmpfilepickerdemo.composeapp.generated.resources.ic_curve_line
import cmpfilepickerdemo.composeapp.generated.resources.ic_document
import cmpfilepickerdemo.composeapp.generated.resources.ic_image
import cmpfilepickerdemo.composeapp.generated.resources.ic_video_camera
import network.chaintech.cmpfilepickerdemo.ui.typo.FontSfPro
import network.chaintech.cmpfilepickerdemo.ui.typo.PoppinsFontFamily
import org.jetbrains.compose.resources.painterResource

val purpule = Color(0xFFC293FF)
val yellow = Color(0xFFFFBB4E)
val gray = Color(0xFF939393)
val light_green = Color(0xFFB0C241)
val wild_blue_yonder = Color(0xFF7686C6)
val bluish_purple = Color(0xFF694CF1)
val salmon_pink = Color(0xFFFF737D)
val orange_gold = Color(0xFFD09A16)
val white = Color(0xFFFFFFFF)
//val fontFamilyExtraBold: FontFamily = fontFamilyResource(Res.font.Poppins_Bold)

const val CornerRadius = 20
const val CardPadding = 18

@Composable
fun HomeScreen(
    onImageGalleryClick: () -> Unit,
    onCameraClick: () -> Unit,
    onVideoGalleryClick: () -> Unit,
    onDocumentPickerClick: () -> Unit,
    onAnyTypeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(Res.drawable.dashboard_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Media Picker",
                    color = purpule,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = PoppinsFontFamily(),
                    modifier = Modifier.padding(top = 102.dp, start = 25.dp, end = 25.dp)
                )
                Image(
                    painter = painterResource(Res.drawable.ic_curve_line),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 55.dp)
                )
                Text(
                    text = "CMP Library",
                    color = yellow,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontSfPro(),
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Choose your media source",
                    color = gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontSfPro(),
                    modifier = Modifier.padding(top = 10.dp, bottom = 57.dp)
                )

                Row(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    Column(modifier = Modifier.fillMaxWidth(.5f).padding(end = 7.dp)) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(160.dp).clip(
                                RoundedCornerShape(
                                    topStartPercent = CornerRadius,
                                    bottomStartPercent = CornerRadius,
                                    bottomEndPercent = CornerRadius
                                )
                            ).background(light_green).clickable {
                                onImageGalleryClick()
                            }
                        ) {
                            Row(
                                modifier = Modifier.padding(CardPadding.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight().fillMaxWidth(.75f),
//                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Text(
                                        text = "Images",
                                        color = white,
                                        fontSize = 18.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = "Select Single or multiple images",
                                        color = white,
                                        fontSize = 14.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.ic_image),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                    Image(
                                        painter = painterResource(Res.drawable.ic_arrow),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(37.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().height(105.dp).clip(
                                RoundedCornerShape(
                                    topStartPercent = CornerRadius,
                                    bottomStartPercent = CornerRadius,
                                    topEndPercent = CornerRadius
                                )
                            ).background(wild_blue_yonder)
                                .clickable {
                                    onDocumentPickerClick()
                                }
                        ) {
                            Row(modifier = Modifier.padding(CardPadding.dp)) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(.75f)
                                ) {
                                    Text(
                                        text = "Documents",
                                        color = white,
                                        fontSize = 18.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = "Select files & docs",
                                        color = white,
                                        fontSize = 14.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.ic_document),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                    Image(
                                        painter = painterResource(Res.drawable.ic_arrow),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(37.dp)
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp)) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(130.dp).clip(
                                RoundedCornerShape(
                                    topEndPercent = CornerRadius,
                                    bottomStartPercent = CornerRadius,
                                    bottomEndPercent = CornerRadius
                                )
                            ).background(bluish_purple)
                                .clickable {
                                    onCameraClick()
                                }
                        ) {
                            Row(modifier = Modifier.padding(CardPadding.dp)) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(.75f)
                                ) {
                                    Text(
                                        text = "Camera",
                                        color = white,
                                        fontSize = 18.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = "Take a New photo",
                                        color = white,
                                        fontSize = 14.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.ic_camera_new),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                    Image(
                                        painter = painterResource(Res.drawable.ic_arrow),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(37.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth().height(135.dp).clip(
                                RoundedCornerShape(
                                    topStartPercent = CornerRadius,
                                    bottomEndPercent = CornerRadius,
                                    topEndPercent = CornerRadius
                                )
                            ).background(salmon_pink)
                                .clickable {
                                    onVideoGalleryClick()
                                }
                        ) {
                            Row(modifier = Modifier.padding(CardPadding.dp)) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(.75f)
                                ) {
                                    Text(
                                        text = "Videos",
                                        color = white,
                                        fontSize = 18.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = "Select single or multiple videos",
                                        color = white,
                                        fontSize = 14.sp,
                                        fontFamily = FontSfPro(),
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.ic_video_camera),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp)
                                    )
                                    Image(
                                        painter = painterResource(Res.drawable.ic_arrow),
                                        contentScale = ContentScale.FillWidth,
                                        contentDescription = null,
                                        modifier = Modifier.size(37.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth().height(73.dp).padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(CornerRadius))
                        .background(orange_gold).clickable {
                            onAnyTypeClick()
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_anytype),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                        Column {
                            Text(
                                text = "Any Selection",
                                color = white,
                                fontSize = 18.sp,
                                fontFamily = FontSfPro(),
                                fontWeight = FontWeight.Medium,
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Select single or multiple videos",
                                color = white,
                                fontSize = 14.sp,
                                fontFamily = FontSfPro(),
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Image(
                            painter = painterResource(Res.drawable.ic_arrow),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier.size(37.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tap to select your preferred option",
                        color = gray,
                        fontSize = 18.sp,
                        fontFamily = FontSfPro(),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}