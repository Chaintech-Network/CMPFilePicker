package network.chaintech.cmpfilepickerdemo.ui.typo

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cmpfilepickerdemo.composeapp.generated.resources.Res
import cmpfilepickerdemo.composeapp.generated.resources.sf_pro
import cmpfilepickerdemo.composeapp.generated.resources.sf_pro_bold
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Black
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Bold
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_ExtraBold
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_ExtraLight
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Light
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Medium
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Regular
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_SemiBold
import cmpfilepickerdemo.composeapp.generated.resources.Poppins_Thin
import cmpfilepickerdemo.composeapp.generated.resources.ps
import cmpfilepickerdemo.composeapp.generated.resources.ps_bold
import cmpfilepickerdemo.composeapp.generated.resources.ps_medium
import cmpfilepickerdemo.composeapp.generated.resources.sf_pro_medium
import cmpfilepickerdemo.composeapp.generated.resources.sf_pro_semibold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@Composable
fun FontSfPro() = FontFamily(
    Font(Res.font.sf_pro, weight = FontWeight.Normal),
    Font(Res.font.sf_pro_bold, weight = FontWeight.Bold),
    Font(Res.font.sf_pro_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.sf_pro_medium, weight = FontWeight.Medium)
)

@Composable
fun PS() = FontFamily(
    Font(Res.font.ps, weight = FontWeight.Normal),
    Font(Res.font.ps_bold, weight = FontWeight.Bold),
    Font(Res.font.ps_medium, weight = FontWeight.Medium)
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.Poppins_Thin, weight = FontWeight.Thin),
    Font(Res.font.Poppins_ExtraLight, weight = FontWeight.ExtraLight),
    Font(Res.font.Poppins_Light, weight = FontWeight.Light),
    Font(Res.font.Poppins_Regular, weight = FontWeight.Normal),
    Font(Res.font.Poppins_Medium, weight = FontWeight.Medium),
    Font(Res.font.Poppins_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Poppins_Bold, weight = FontWeight.Bold),
    Font(Res.font.Poppins_ExtraBold, weight = FontWeight.ExtraBold),
    Font(Res.font.Poppins_Black, weight = FontWeight.Black)
)