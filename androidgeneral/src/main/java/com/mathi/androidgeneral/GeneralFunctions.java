package com.mathi.androidgeneral;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathiazhagan
 * @version 1.0
 */

public class GeneralFunctions {

    private GeneralFunctions() {
    }


    /**
     * To get instance of GeneralFunctions
     *
     * @return instance of GeneralFunctions
     * @since 1.0
     */
    public static GeneralFunctions getInstance() {
        return new GeneralFunctions();
    }

    /**
     * To save value to shared preference
     *
     * @param context context from activity
     * @param fileKey Shared preference file key
     * @param mode    Shared preference mode
     * @param key     Shared preference key
     * @param value   Shared preference value
     * @since 1.0
     */
    public void saveValueToSharedPreference(Context context, String fileKey, Integer mode, String key, @Nullable String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, mode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * To get value from shared preference
     *
     * @param context context from activity
     * @param fileKey Shared preference file key
     * @param mode    Shared preference mode
     * @param key     Shared preference key
     * @return Value of shared preference for given key
     * @since 1.0
     */
    public String getSharePreferenceValue(Context context, String fileKey, Integer mode, String key) {
        return getSharePreferenceValue(context, fileKey, mode, key, null);
    }

    /**
     * To get value from shared preference
     *
     * @param context      context from activity
     * @param fileKey      Shared preference file key
     * @param mode         Shared preference mode
     * @param key          Shared preference key
     * @param defaultValue Default value to return if there is no value from shared preference
     * @return defaultValue or value of shared preference or given key
     * @since 1.0
     */
    public String getSharePreferenceValue(Context context, String fileKey, Integer mode, String key, @Nullable String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, mode);
        return sharedPreferences.contains(key) ? sharedPreferences.getString(key, defaultValue) : "";
    }

    /**
     * To remove value from shared preference
     *
     * @param context context from activity
     * @param fileKey Shared preference file key
     * @param mode    Shared preference mode
     * @param key     Shared preference key
     * @since 1.0
     */
    public void removeValuePreferenceValue(Context context, String fileKey, Integer mode, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, mode);
        sharedPreferences.edit()
                .remove(key)
                .apply();
    }

    /**
     * To remove all values from shared preference
     *
     * @param context context from activity
     * @param fileKey Shared preference file key
     * @param mode    Shared preference mode
     * @since 1.0
     */
    public void removeAllSharedPreference(Context context, String fileKey, Integer mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, mode);
        sharedPreferences.edit()
                .clear()
                .apply();
    }

    /**
     * To check shared preference available or not
     *
     * @param context context from activity
     * @param fileKey Shared preference file key
     * @param mode    Shared preference mode
     * @param key     Shared preference key
     * @return boolean value
     * @since 1.0
     */
    public boolean isSharedPreferenceAvailable(Context context, String fileKey, Integer mode, String key) {
        SharedPreferences preferences = context.getSharedPreferences(fileKey, mode);
        String value = preferences.getString(key, null);
        return value != null;
    }

    /**
     * To open a url from browser
     *
     * @param context context from activity
     * @param url     url to open
     * @since 1.0
     */
    public void openBrowser(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    /**
     * To replace fragment on frame layout
     *
     * @param fragmentManager Fragment manager from activity
     * @param containerViewId Frame layout id
     * @param fragment        Fragment to view
     * @param tag             Tag for the fragment
     * @since 1.0
     */
    public void replaceFragment(FragmentManager fragmentManager, @IdRes Integer containerViewId, Fragment fragment, @Nullable String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag).commit();
    }

    /**
     * To replace fragment on frame layout
     *
     * @param fragmentManager Fragment manager from activity
     * @param containerViewId Frame layout id
     * @param fragment        Fragment to view
     * @since 1.0
     */
    public void replaceFragment(FragmentManager fragmentManager, @IdRes Integer containerViewId, Fragment fragment) {
        replaceFragment(fragmentManager, containerViewId, fragment, null);
    }

    /**
     * To replace fragment on frame layout with back enabled
     *
     * @param fragmentManager Fragment manager from activity
     * @param containerViewId Frame layout id
     * @param fragment        Fragment to view
     * @param tag             Tag for the fragment
     * @param name            Name for the fragment
     * @since 1.0
     */
    public void replaceFragmentWithBackStack(FragmentManager fragmentManager, @IdRes Integer containerViewId, Fragment fragment, @Nullable String tag, @Nullable String name) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag).addToBackStack(name).commit();
    }

    /**
     * To replace fragment on frame layout with back enabled
     *
     * @param fragmentManager Fragment manager from activity
     * @param containerViewId Frame layout id
     * @param fragment        Fragment to view
     * @since 1.0
     */
    public void replaceFragmentWithBackStack(FragmentManager fragmentManager, @IdRes Integer containerViewId, Fragment fragment) {
        replaceFragmentWithBackStack(fragmentManager, containerViewId, fragment, null, null);
    }

    /**
     * To restart activity
     *
     * @param context context from activity
     * @since 1.0
     */
    public void restartActivity(Context context) {
        Intent intent = ((Activity) context).getIntent();
        ((Activity) context).finish();
        context.startActivity(intent);
    }

    /**
     * To Create folder/directory
     *
     * @param pathname Full path
     * @since 1.0
     */
    public void createFolder(@NonNull String pathname) {
        File file = new File(pathname);
        if (!file.mkdir()) {
            Log.e(ConstantVariables.ERR, ConstantVariables.FILE_NOT_CREATED);
        }
    }

    /**
     * To save file from assets folder
     *
     * @param context         context from activity
     * @param sourceDirectory Source directory
     * @param filename        Name of the file
     * @throws IOException IOException
     * @since 1.0
     */
    public void saveAsset(Context context, String sourceDirectory, @NonNull String filename) throws IOException {
        InputStream inputStream = context.getAssets().open(filename);
        File outFile = new File(sourceDirectory, filename);
        OutputStream outputStream = new FileOutputStream(outFile);
        copyFile(inputStream, outputStream);
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /**
     * To copy file from one directory to other
     *
     * @param sourceFile Source file
     * @param destFile   Destination file
     * @throws IOException IOException
     * @since 1.0
     */
    public void copyFile(@NonNull File sourceFile, @NonNull File destFile) throws IOException {
        if (!destFile.getParentFile().exists()) {
            if (!destFile.getParentFile().mkdirs())
                Log.e(ConstantVariables.ERR, ConstantVariables.DIR_NOT_CREATED);
        } else {
            Log.e(ConstantVariables.ERR, ConstantVariables.DIR_ALREADY_EXISTS);
        }

        if (!destFile.exists()) {
            if (!destFile.createNewFile())
                Log.e(ConstantVariables.ERR, ConstantVariables.FILE_NOT_CREATED);
        } else
            Log.e(ConstantVariables.ERR, ConstantVariables.FILE_ALREADY_EXISTS);

        try (
                FileChannel source = new FileInputStream(sourceFile).getChannel();
                FileChannel destination = new FileOutputStream(destFile).getChannel();
        ) {
            destination.transferFrom(source, 0, source.size());
        }
    }

    /**
     * To rename a file
     *
     * @param from File with old name/path
     * @param to   File with new name/path
     * @since 1.0
     */
    public void renameFile(File from, File to) {
        if (!from.renameTo(to)) {
            Log.e(ConstantVariables.ERR, ConstantVariables.RENAME_FAILED);
        }
    }

    /**
     * To get Uri from file
     *
     * @param context   context from activity
     * @param authority File provider authority
     * @param file      File
     * @return Uri
     * @since 1.0
     */
    public Uri getUri(Context context, @NonNull String authority, @NonNull File file) {
        return FileProvider.getUriForFile(context, authority, file);
    }

    /**
     * To get filename without extension
     *
     * @param pathname File name with or without full path
     * @return String
     * @since 1.0
     */
    public String getFilenameWithoutExtension(@NonNull String pathname) {
        File srcFile = new File(pathname);
        String srcFilename = srcFile.getName();
        int lastPeriodPos = srcFilename.lastIndexOf('.');
        return lastPeriodPos > 0 ? srcFilename.substring(0, lastPeriodPos) : srcFilename;
    }

    /**
     * To view photo,video or any media
     *
     * @param context   context from activity
     * @param authority File provider authority
     * @param file      Photo, Video or any media file
     * @param type      Type of media
     * @param bundle    Bundle
     * @since 1.0
     */
    public void viewMedia(Context context, @NonNull String authority, @NonNull File file, @Nullable String type, @Nullable Bundle bundle) {
        Uri uri = getUri(context, authority, file);
        viewMedia(context, uri, type, bundle);
    }


    /**
     * To view photo,video or any media
     *
     * @param context context from activity
     * @param data    Uri of media file
     * @param type    Type of media
     * @param bundle  Bundle
     * @since 1.0
     */
    public void viewMedia(Context context, @Nullable Uri data, @Nullable String type, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .setDataAndType(data, type);
        context.startActivity(intent, bundle);
    }

    /**
     * To share file to other apps
     *
     * @param context   context from activity
     * @param authority File provider authority
     * @param file      File to be shared
     * @param mimeType  Type of the file
     * @param text      Share text
     * @param bundle    Bundle
     * @since 1.0
     */
    public void shareFile(Context context, @NonNull String authority, @NonNull File file, String mimeType, CharSequence text, @Nullable Bundle bundle) {
        Uri streamUri = getUri(context, authority, file);
        shareFile(context, streamUri, mimeType, text, bundle);
    }

    /**
     * To share file to other apps
     *
     * @param context   context from activity
     * @param streamUri Uri of file
     * @param mimeType  Type of file
     * @param text      Share text
     * @param bundle    Bundle
     * @since 1.0
     */
    public void shareFile(Context context, @Nullable Uri streamUri, String mimeType, CharSequence text, @Nullable Bundle bundle) {
        Intent shareIntent = ShareCompat.IntentBuilder.from((Activity) context)
                .setType(mimeType)
                .setStream(streamUri)
                .setText(text)
                .getIntent();
        if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(shareIntent, bundle);
        }
    }

    /**
     * To share multiple files to other apps
     *
     * @param context      context from activity
     * @param type         Type of files
     * @param extraSubject Extra subject
     * @param extraText    Extra text
     * @param value        List of uri of files
     * @since 1.0
     */
    public void shareMultipleFiles(Context context, @Nullable String type, String extraSubject, String extraText, List<Uri> value) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE)
                .setType(type)
                .putExtra(Intent.EXTRA_SUBJECT, extraSubject)
                .putExtra(android.content.Intent.EXTRA_TEXT, extraText)
                .putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) value);
        context.startActivity(intent);
    }
}
