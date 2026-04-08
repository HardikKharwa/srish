package com.example.srish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    public static final String myPreferences = "myPrefs";
    SharedPreferences sharedPreferences;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);
        if (sharedPreferences.getAll().containsKey("isLoggedIn")) {
            if (sharedPreferences.getAll().containsKey("username")) {
                username = sharedPreferences.getString("username", "");
                Toast.makeText(this, "username" + username, Toast.LENGTH_LONG).show();
            } else {
                logout();
            }

        } else {
            logout();
        }


    }
    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        Intent i = new Intent(SecondActivity.this,MainActivity.class);
        startActivity(i);
        finish();

    }
}
