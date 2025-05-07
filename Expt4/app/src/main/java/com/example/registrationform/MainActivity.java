package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner subjectSpinner;
    private RadioGroup genderRadioGroup;
    private CheckBox highSchoolCheckBox, bachelorsCheckBox, mastersCheckBox;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEdittext); // Corrected ID to match XML
        subjectSpinner = findViewById(R.id.subjectSpinner);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        highSchoolCheckBox = findViewById(R.id.highSchoolCheckBox);
        bachelorsCheckBox = findViewById(R.id.bachelorsCheckBox);
        mastersCheckBox = findViewById(R.id.mastersCheckBox);
        submitButton = findViewById(R.id.submitButton);

        String[] subjects = {"Mathematics", "Science", "History", "Computer Science"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String subject = subjectSpinner.getSelectedItem().toString();
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                String gender = "";
                if (selectedGenderId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedGenderId);
                    gender = selectedRadioButton.getText().toString();
                }

                StringBuilder qualifications = new StringBuilder();
                if (highSchoolCheckBox.isChecked()) qualifications.append("High School, ");
                if (bachelorsCheckBox.isChecked()) qualifications.append("Bachelor's, ");
                if (mastersCheckBox.isChecked()) qualifications.append("Master's, ");
                String qualificationsStr = qualifications.length() > 0 ? qualifications.substring(0, qualifications.length() - 2) : "None";

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else if (gender.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show(); // Corrected typo "gnder" to "gender"
                } else {
                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("subject", subject); // Corrected key from "Subjecct" to "subject"
                    intent.putExtra("gender", gender);
                    intent.putExtra("qualifications", qualificationsStr);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}