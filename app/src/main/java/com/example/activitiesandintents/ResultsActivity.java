package com.example.activitiesandintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "KEY";
    //private static final String EXTRA_REPLY = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, "This is a reply message from the results activity");
        setResult(RESULT_OK, replyIntent);
        //finish();
    }


    public void onReturn(View view) {
        finish();
    }
}