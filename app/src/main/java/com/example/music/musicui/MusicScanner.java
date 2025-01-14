package com.example.music.musicui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.music.model.MusicItem;

import java.util.ArrayList;

public class MusicScanner {

    // This class will return all audio files along with their names.
    public static ArrayList<MusicItem> getAllMusicFiles(Context context) {
        ArrayList<MusicItem> musicFiles = new ArrayList<>();

        // Uri for music files
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // Columns to query
        String[] projection = {
                MediaStore.Audio.Media.TITLE,  // Music title
                MediaStore.Audio.Media.DISPLAY_NAME,  // File name (e.g., "song.mp3")
                MediaStore.Audio.Media.DATA  // File path
        };

        // Query the content resolver to get all audio files
        Cursor cursor = context.getContentResolver().query(
                musicUri, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Get the music file name (Title) and file path
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                // Optionally, you can store both the name and the path in your list
                musicFiles.add(new MusicItem(title, fileName, filePath));
            }
            cursor.close();
        }

        return musicFiles;
    }
}


