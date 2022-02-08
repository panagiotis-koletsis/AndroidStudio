package gr.hua.dit.android.ergasiaapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "gr.hua.dit.android.geofencetserpes";
    private static final String PATH = "GEODATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            new Thread(()->{
                ContentResolver contentResolver = getContentResolver();
                Uri uri = Uri.parse("content://"+AUTHORITY+"/"+PATH);
                Cursor cursor = contentResolver.query(uri,null,null,null,null);
                if(cursor.moveToFirst()){
                    do {
                        Log.d("Cursor",cursor.getString(0));
                    }while (cursor.moveToNext());
                }
            }).start();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,MapsActivity.class);
            startActivity(intent);

        });


    }
}