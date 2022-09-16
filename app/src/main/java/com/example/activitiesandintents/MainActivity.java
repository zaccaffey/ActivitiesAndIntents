package com.example.activitiesandintents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView conTextView;
    EditText etName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this connects the activity to our layout
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);

    }

    public void onClick(View view) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.i("This is the token: ", task.getResult());
                    return;
                }
            }
        });
    }

    // when the activity is paused call the save data function to save the preferences
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {
        String name = etName.getText().toString();
        // create a shared preferences file in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", MODE_PRIVATE);
        // declare an editor to add to the preferences file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // put the name string into the shared preferences file
        editor.putString("nkey", name);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        // access the shared preferences file that was created
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", MODE_PRIVATE);
        // grab the name from our shared preferences file
        String name = sharedPreferences.getString("nkey", "No such data found");
        etName.setText(name);
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

    // invoked when one of the service buttons is clicked
    public void serviceHandler(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("filename", "YourMovie.mov");
        switch (view.getId()) {
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBind:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    // create the service connection --- interface for monitoring the state of an application service
    ServiceConnection serviceConnection = new ServiceConnection() {
        // create an instance of myService
        MyService myService;
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            // create a local binder to MyService
            MyService.LocalBinder myLocalBinder = (MyService.LocalBinder) binder;
            // use the local binder to get the service
            myService = myLocalBinder.getService();
            // make a call to the service function (add)
            int result = myService.add(3, 4);
            Log.i("This is the tag ----- ", result+"");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}