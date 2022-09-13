package com.example.activitiesandintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView conTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this connects the activity to our layout
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {

        // create a new intent passing the context (this) and the TestActivity class (EXPLICIT intent example)
        Intent intent = new Intent(this, TestActivity.class);

        // this call starts the activity based on the intent
        startActivity(intent);
    }

    public void onImplicit(View view) {
        // Implicit intent example
        Uri uri = Uri.parse("http://google.com");

        // as you can see here we do not specify an activity to start, therefore the system will find one that can handle this request
        Intent impIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(impIntent);
    }

    // click handler for testing passing data from one activity to another
    public void onSending(View view) {

        // explicitly define an intent to open the TestActivity class
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("Zac", "this is an extra that was sent from MainActivity");
        startActivity(intent);
    }

    // click handler to start our activity which will return data
    public void onResults(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivityForResult(intent, 123);
    }

    // this runs when we return back from the results activity and sets the text view to data passed from the ResultsActivity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // grab the textView
        conTextView = findViewById(R.id.textView3);
        if (intent != null) {
            // get the intent extras
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // grab the string data set in our ResultsActivity
                String contact = bundle.getString("KEY");
                conTextView.setText(contact);
            }
        }
    }

}