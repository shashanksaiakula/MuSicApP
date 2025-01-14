package com.example.music.musicui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PauseCircle
import androidx.compose.material.icons.rounded.PauseCircleOutline
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.music.R
import com.example.music.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSong(modifier: Modifier = Modifier) {
    val viewModel: SingleSongViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("")
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Down",
                Modifier.size(40.dp)
            )
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = "More",
                Modifier
                    .size(30.dp)
                    .clickable {
                        viewModel.chekBottomShettOpen(!viewModel.isBottomSheetOpen.value)
                    }
            )
        }

        Text(
            "song title",
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        // Image with rounded corners
        Image(
            painter = painterResource(R.drawable.launchscreen),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(35.dp)),
            contentScale = ContentScale.Crop
        )

        // Slider
        Slider(
            value = 5f,
            valueRange = 1f..10f,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
            )
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("00:00", style = Typography.bodySmall)
            Text("00:00", style = Typography.bodySmall)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "like",
                Modifier.size(20.dp)
            )
            Icon(
                imageVector = Icons.Default.Shuffle, contentDescription = "List",
                Modifier.size(20.dp)
            )
            Icon(
                imageVector = Icons.Default.Repeat, contentDescription = "Repeate",
                Modifier.size(20.dp)
            )
            Icon(
                imageVector = Icons.Default.FormatListNumbered, contentDescription = "List",
                Modifier.size(20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious, contentDescription = "skip Perivous",
                Modifier.size(50.dp)
            )
//            Icon(imageVector = Icons.Default.Ar)
            val isPayOrPause =
                if (viewModel.isPlayOrPause.value) Icons.Rounded.PauseCircleOutline else Icons.Rounded.PlayCircle
            Icon(
                imageVector = isPayOrPause, contentDescription = "Pause or play",
                Modifier
                    .size(70.dp)
                    .clickable {
                        var value = ""
                        if (isPayOrPause == Icons.Rounded.PlayCircle) {
                            value = "play"
                        } else {
                            value = "pause"
                        }
                        viewModel.pauseAndPlayToggle(value)
                    }
            )
            Icon(
                imageVector = Icons.Default.SkipNext, contentDescription = "skip Next",
                Modifier.size(50.dp)
            )
        }
    }
        var showBottomSheet by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false,
        )
        if (viewModel.isBottomSheetOpen.value) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(.3f),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false
                    viewModel.chekBottomShettOpen(!viewModel.isBottomSheetOpen.value)},
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
//                shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
            ) {
               Column (
                   modifier = Modifier.padding(20.dp)
               ) {
                   IconWithText(image = Icons.Outlined.Timer, text = "Timer")
                   IconWithText(image = Icons.Outlined.Share, text = "Share the Song")
               }
            }

        }

    }

}

@Composable
fun IconWithText(modifier: Modifier = Modifier,image :ImageVector, text :String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp,)
            .clickable {
                Log.e("check", "IconWithText: $text", )
            }
    ){
        Icon(imageVector = image,"Timer")
        Text(
            text,
            style = Typography.bodyLarge,
            modifier = Modifier.padding(start = 20.dp,)
        )
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

