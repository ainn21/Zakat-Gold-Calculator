package com.example.zakatcalculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Start button
        Button startButton = findViewById(R.id.Button1);

        // Set an OnClickListener for the button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the second activity when the button is clicked
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent); // This will navigate to the second activity
            }
        });
    }

}
