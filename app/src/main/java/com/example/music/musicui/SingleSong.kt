package com.example.music.musicui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PauseCircle
import androidx.compose.material.icons.rounded.PauseCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music.R

@Composable
fun SingleSong(modifier: Modifier = Modifier) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp),
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
                    Modifier.size(30.dp)
                )
            }

            Text(
                "Hi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(5.dp)
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
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("00:00")
                Text("00:00")
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "skip Next",
                    Modifier.size(20.dp)
                )
                Icon(
                    imageVector = Icons.Default.Repeat, contentDescription = "skip Next",
                    Modifier.size(20.dp)
                )
                Icon(
                    imageVector = Icons.Default.FormatListNumbered, contentDescription = "skip Next",
                    Modifier.size(20.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Icon(
                    imageVector = Icons.Default.SkipPrevious, contentDescription = "skip Perivous",
                    Modifier.size(50.dp)
                )
                Icon(
                    imageVector = Icons.Rounded.PauseCircleOutline, contentDescription = "skip Next",
                    Modifier.size(70.dp)
                )
                Icon(
                    imageVector = Icons.Default.SkipNext, contentDescription = "skip Next",
                    Modifier.size(50.dp)
                )
            }
        }
    }
