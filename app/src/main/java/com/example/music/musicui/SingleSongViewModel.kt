package com.example.music.musicui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.music.model.MusicItem

class SingleSongViewModel: ViewModel() {

    private val _isPlayOrPause = mutableStateOf(false)
    val isPlayOrPause : State<Boolean> get() = _isPlayOrPause

    private val _isBottomSheetOpen = mutableStateOf(false)
    val isBottomSheetOpen : State<Boolean> get() = _isBottomSheetOpen

    private val _musicList = mutableStateOf<List<MusicItem>>(emptyList())
    val musicList: State<List<MusicItem>> = _musicList

    fun pauseAndPlayToggle(value : String) {
        if(value.equals("play")) {
            _isPlayOrPause.value = true
        } else{
            _isPlayOrPause.value = false
        }
    }

    fun chekBottomShettOpen(vale : Boolean){
        _isBottomSheetOpen.value = vale
    }

    fun scanMusicList(context : Context) {
        // Check permissions before scanning based on the Android version
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13+ (API 33+), we need READ_MEDIA_AUDIO permission for audio files
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            // For Android 12 and below, we use READ_EXTERNAL_STORAGE
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, scan music files
            _musicList.value = MusicScanner.getAllMusicFiles(context)


        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(permission),
                1 // Request code
            )
            Log.e("check", "scanMusic: permission not granted")
        }
    }
}