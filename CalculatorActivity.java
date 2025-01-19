package com.example.zakatcalculatorapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CalculatorActivity extends AppCompatActivity {

    private EditText weightInput, valueInput;
    private RadioGroup typeGroup;
    private TextView totalValueLabel, zakatPayableLabel, totalZakatLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the views
        weightInput = findViewById(R.id.weightInput);
        valueInput = findViewById(R.id.valueInput);
        typeGroup = findViewById(R.id.typeGroup);
        totalValueLabel = findViewById(R.id.totalValueLabel);
        zakatPayableLabel = findViewById(R.id.zakatPayableLabel);
        totalZakatLabel = findViewById(R.id.totalZakatLabel);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button resetButton = findViewById(R.id.resetButton);

        // Calculate button logic
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                double weight = Double.parseDouble(weightInput.getText().toString());
                double valuePerGram = Double.parseDouble(valueInput.getText().toString());

                int selectedTypeId = typeGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedTypeId);

                double X = 0;
                if (selectedRadioButton != null) {
                    if (selectedRadioButton.getText().toString().equals("Keep")) {
                        X = 85;
                    } else if (selectedRadioButton.getText().toString().equals("Wear")) {
                        X = 200;
                    }
                }

                double totalValue = weight * valuePerGram;
                double zakatPayable = Math.max(0, (weight - X) * valuePerGram);
                double totalZakat = zakatPayable * 0.025;

                totalValueLabel.setText("Total Gold Value: RM " + totalValue);
                zakatPayableLabel.setText("Zakat Payable Value: RM " + zakatPayable);
                totalZakatLabel.setText("Total Zakat (2.5%): RM " + totalZakat);
            }
        });

        // Reset button logic
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightInput.setText("");
                valueInput.setText("");
                typeGroup.clearCheck();
                totalValueLabel.setText("Total Gold Value: RM 0");
                zakatPayableLabel.setText("Zakat Payable Value: RM 0");
                totalZakatLabel.setText("Total Zakat (2.5%): RM 0");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Navigate to the About Us page
            Intent intent = new Intent(CalculatorActivity.this, About_Us.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_share) {
            // Share the application
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check Out This Application");
            intent.putExtra(Intent.EXTRA_TEXT, "Link Application Here");
            startActivity(Intent.createChooser(intent, "Share Via"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
