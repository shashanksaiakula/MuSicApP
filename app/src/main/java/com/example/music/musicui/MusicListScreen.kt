package com.example.music.musicui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.music.R
import com.example.music.ui.theme.Typography

@Composable
fun MusicListScreen(modifier: Modifier = Modifier) {
    val viewModel : SingleSongViewModel = viewModel()
    val context = LocalContext.current
    LaunchedEffect(viewModel.musicList.value) {
        viewModel.scanMusicList(context)
    }
    Column(
        modifier = Modifier.padding(10.dp).fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Search")
            Icon(imageVector = Icons.Default.MoreVert,"menu")

        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(.9f)
        ) {
            items(viewModel.musicList.value){
                SongItem(title = it.title)
            }

        }
        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            BottomSongShow(
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun SongItem(modifier: Modifier = Modifier,title: String="") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp).clickable {

            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = Typography.bodyLarge, modifier = Modifier)
        Icon(imageVector = Icons.Outlined.MoreVert,"more")
    }
}

@Composable
fun BottomSongShow(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .border(width = .01.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(5.dp))
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(R.drawable.launchscreen), contentDescription = "image",
                modifier = Modifier.size(50.dp).padding(5.dp)
            )
                Text("title", style = Typography.labelLarge,)
        }
        Row {
            Icon(imageVector = Icons.Default.Pause, "pause")
            Icon(imageVector = Icons.Default.SkipNext, "Play")
        }
    }
}
