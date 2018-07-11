package com.bstech.voicechanger.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class Utils {


    public static final String START_SERVICE = "Start";
    public static final String STOP_SERVICE = "Stop";
    public static final String LIMITED_TIME = "LIMITED_TIME";
    public static final String TIME = "TIME";
    public static final String FILE_SIZE = "FILE_SIZE";
    public static final String STOP_RECORD = "StopRecord";
    public static final String CHECK_FRAGMENT = "Check";
    public static final String CHECK_LIST = "List";
    public static final String CHECK_FAVORITE = "Favorite";
    public static final String EMPTY = "";
    public static final String MP3 = "MP3";
    public static final String M4A = "M4A";
    public static final String OGG = "OGG";
    public static final String FORMAT_MP3 = ".mp3";
    public static final String FORMAT_M4A = ".m4a";
    public static final String FORMAT_OGG = ".ogg";
    public static final String BITRATE_128_ = "128 kbps";
    public static final String BITRATE_160_ = "160 kbps";
    public static final String BITRATE_192_ = "192 kbps";
    public static final String BITRATE_256_ = "256 kbps";
    public static final String BITRATE_320_ = "320 kbps";
    public static final int _MP3 = 0;
    public static final int _M4A = 1;
    public static final int _OGG = 2;

    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";
    public static final String ZERO = "0";
    public static final String PASSWORD = "PASSWORD";
    public static final String CHECK = "Check";
    public static final String MAIN = "Main";
    public static final String SETTING = "Setting";
    public static final String CHANGE_PASS = "ChangePass";
    public static final String UPDATE_LIST = "UpdateList";
    public static final String LANGUAGE = "Language";
    public static final String BITRATE_M4A = "FormatM4A";
    public static final String BITRATE_MP3 = "FormatMP3";
    public static final String BITRATE_OGG = "FormatOGG";
    public static final String FRAGMENT_SEARCH = "Search";
    public static final int LIST_RECORD = 0;
    public static final int VOICE_RECORDER = 1;
    public static final int FAVORITE = 2;
    public static final int N_ZERO = 0;
    public static final int N_ONE = 1;
    public static final int N_ONE_DAY = 1;
    public static final int N_TWO_DAY = 2;
    public static final int N_ONE_WEEK = 7;
    public static final int N_TWO_WEEK = 14;
    public static final int N_ONE_MONTH = 30;
    public static final int MP3_128 = 0;
    public static final int MP3_160 = 1;
    public static final int MP3_192 = 2;
    public static final int MP3_256 = 3;
    public static final int MP3_320 = 4;
    public static final int ONE_SEC = 1000;
    public static final String UPDATE_LIST_FAVORITE = "UpdateListFavorite";
    public static final String CHECK_TIME = "checktime";
    public static final String TOTAL_UPATE = "UpdateList1";
    public static final String STOP_PLAYING = "StopPlaying";
    public static final String LOCAL_SAVE_FILE = "LocalSaveFile";
    public static final String STATUS_PLAY = "STATUS_PLAY";
    public static final String STATUS_START = "Start";
    public static final String STATUS_STOP = "Stop";
    public static final String LOCKED_NAVIGATION = "LOCKED";
    public static final String STOP_AUDIO = "Stop";
    public static final String DEFAULT_PATH = "/storage/emulated/0/AudioRecord";
    public static final String SAVE_PATH = "SavePath";
    public static final String PATH = "PATH";
    public static final String ON = "ON";
    public static final String FIRST_OPEN_APP = "First";
    public static final String STOP_ACTION_MODE = "STOP_ACTION";
    public static final String TREE_URI = "TreeUri1";
    public static final String FOLDER_ENCODE = "DRIVE_FOLDER_ENCODE_ID";
    public static final String FOLDER_RES = "DRIVE_FOLDER_RES_ID";
    public static final String SHARE_PREF = "MyShare";
    public static final String UI_PLAY_SONG = "ui_play_song";
    public static final String UPDATE_PAUSE_NOTIFICATION ="update_pause_notification" ;
    public static final String STOP_MUSIC = "stop_music" ;
    public static final String OPEN_LIST_FILE ="open_file" ;
    public static final String INDEX ="index" ;


    public static int getMediaDuration(String filePath) {
        MediaMetadataRetriever metaInfo = new MediaMetadataRetriever();
        int duration = -1;
        try {
            metaInfo.setDataSource(filePath);
            duration = Integer.valueOf(metaInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        } catch (Exception e) {
            e.printStackTrace();
            metaInfo.release();
            return -1;
        } finally {
            metaInfo.release();
        }
        return duration;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    public static Bitmap getThumbnail(Uri uri, Context context) throws IOException {
        try {
            InputStream input = context.getContentResolver().openInputStream(uri);

            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;
            onlyBoundsOptions.inDither = true;//optional
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            if (input != null) {
                input.close();
            }

            if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
                return null;
            }

            int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

            double ratio = (originalSize > 600) ? (originalSize / 600) : 1.0;

            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
            bitmapOptions.inDither = true; //optional
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//
            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            if (input != null) {
                input.close();
            }
            return bitmap;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readSizeFile(double path) {
        String valueFile = null;

        double Filesize = path / 1024;

        if (Filesize >= 1000) {
            BigDecimal rowOff = new BigDecimal(Filesize / 1024).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            valueFile = rowOff + " Mb";
        } else {
            BigDecimal rowOff = new BigDecimal(Filesize).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            valueFile = rowOff + " Kb";
        }
        return valueFile;
    }

    public static String convertMillisecond(long millisecond) {
        long sec = (millisecond / 1000) % 60;
        long min = (millisecond / (60 * 1000)) % 60;
        long hour = millisecond / (60 * 60 * 1000);

        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;

        String time = "";
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }

    public static String convertNanoMiniSecond(long millisecond) {
        long sec = (millisecond / 1000000) % 60;
        long min = (millisecond / (60 * 1000)) % 60;
        long hour = millisecond / (60 * 60 * 1000);

        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;

        String time = "";
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }

}