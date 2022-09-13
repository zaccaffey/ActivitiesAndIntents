package com.example.activitiesandintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // grab the intent that was created in the main activity
        Intent intent = getIntent();

        // grab the extras as a bundle
        Bundle bundle = intent.getExtras();

        // assign the textView
        textView = findViewById(R.id.textView);

        // set the text in the textView
        textView.setText(bundle.getString("Zac"));
    }
}