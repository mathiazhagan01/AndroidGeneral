package com.mathi.androidgeneral.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mathi.androidgeneral.GeneralFunctions;
import com.mathi.androidgeneral.R;
import com.mathi.androidgeneral.general.ConstantVariables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private GeneralFunctions generalFunctions;

    private static final String PATHNAME = Environment.getExternalStorageDirectory() + File.separator + ConstantVariables.PATH;
    private static final String TYPE = "image/jpg";
    private static final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 1;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        Button openBrowser = mainView.findViewById(R.id.openbrowser);
        Button openSecondFragment = mainView.findViewById(R.id.opensecondfragment);
        Button restartActivity = mainView.findViewById(R.id.restartactivity);
        Button viewMedia = mainView.findViewById(R.id.viewmedia);
        Button shareFile = mainView.findViewById(R.id.sharefile);
        Button shareAllFiles = mainView.findViewById(R.id.shareallfiles);

        openBrowser.setOnClickListener(this);
        openSecondFragment.setOnClickListener(this);
        restartActivity.setOnClickListener(this);
        viewMedia.setOnClickListener(this);
        shareFile.setOnClickListener(this);
        shareAllFiles.setOnClickListener(this);

        generalFunctions = GeneralFunctions.getInstance();

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_STORAGE);
        }

        generalFunctions.saveValueToSharedPreference(context, ConstantVariables.FILE_KEY, ConstantVariables.MODE, ConstantVariables.USERNAME, ConstantVariables.NAME);
        Log.v("getSharePreferenceValue", generalFunctions.getSharePreferenceValue(context, ConstantVariables.FILE_KEY, ConstantVariables.MODE, ConstantVariables.USERNAME));
        generalFunctions.removeValuePreferenceValue(context, ConstantVariables.FILE_KEY, ConstantVariables.MODE, ConstantVariables.USERNAME);
        Log.v("isSharedPreferenceAvail", String.valueOf(generalFunctions.isSharedPreferenceAvailable(context, ConstantVariables.FILE_KEY, ConstantVariables.MODE, ConstantVariables.USERNAME)));
        generalFunctions.removeAllSharedPreference(context, ConstantVariables.FILE_KEY, ConstantVariables.MODE);

        storageRelatedFunctions();
        return mainView;
    }

    private void storageRelatedFunctions() {
        try {
            generalFunctions.createFolder(PATHNAME);
            generalFunctions.saveAsset(context, PATHNAME, ConstantVariables.IMGNAME);
            Uri uri = generalFunctions.getUri(context, getString(R.string.file_provider_authority), new File(PATHNAME, ConstantVariables.IMGNAME));
            Log.v("Uri", String.valueOf(uri));
            Log.v("FilenameWithoutExt", generalFunctions.getFilenameWithoutExtension(PATHNAME + File.separator + ConstantVariables.IMGNAME));
            generalFunctions.copyFile(new File(PATHNAME, ConstantVariables.IMGNAME), new File(PATHNAME, ConstantVariables.IMGNAME3));
            generalFunctions.renameFile(new File(PATHNAME, ConstantVariables.IMGNAME3), new File(PATHNAME, ConstantVariables.IMGNAME2));
        } catch (IOException e) {
            Log.e("Err", e.getLocalizedMessage());
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openbrowser:
                generalFunctions.openBrowser(context, ConstantVariables.URL);
                break;
            case R.id.opensecondfragment:

                generalFunctions.replaceFragmentWithBackStack(getFragmentManager(), R.id.frame_layout, new SecondFragment(), ConstantVariables.TAG, ConstantVariables.SECOND_FRAGMENT);
                break;
            case R.id.restartactivity:
                generalFunctions.restartActivity(context);
                break;
            case R.id.viewmedia:
                generalFunctions.viewMedia(context, getString(R.string.file_provider_authority), new File(PATHNAME, ConstantVariables.IMGNAME), TYPE, null);
                break;
            case R.id.sharefile:
                generalFunctions.shareFile(context, getString(R.string.file_provider_authority), new File(PATHNAME, ConstantVariables.IMGNAME), TYPE, getString(R.string.shared_via_android_general), null);
                break;
            case R.id.shareallfiles:
                List<Uri> uriList = new ArrayList<>();
                uriList.add(generalFunctions.getUri(context, getString(R.string.file_provider_authority), new File(PATHNAME, ConstantVariables.IMGNAME)));
                uriList.add(generalFunctions.getUri(context, getString(R.string.file_provider_authority), new File(PATHNAME, ConstantVariables.IMGNAME2)));
                generalFunctions.shareMultipleFiles(context, TYPE, getString(R.string.test), getString(R.string.shared_via_android_general), uriList);
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            storageRelatedFunctions();
        }
    }
}
