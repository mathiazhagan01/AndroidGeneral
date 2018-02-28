package com.mathi.androidgeneral.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mathi.androidgeneral.GeneralFunctions;
import com.mathi.androidgeneral.R;
import com.mathi.androidgeneral.fragments.MainFragment;
import com.mathi.androidgeneral.general.ConstantVariables;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeneralFunctions generalFunctions = GeneralFunctions.getInstance();

        generalFunctions.replaceFragment(getSupportFragmentManager(), R.id.frame_layout, new MainFragment(), ConstantVariables.TAG);
    }
}
