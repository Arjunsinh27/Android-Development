package com.example.loginpage;

import  android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity
{
    private EditText registerUsernameEditText, registerPasswordEditText;
    private Button registerButton, backToLoginButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerUsernameEditText = findViewById(R.id.registerUsernameEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backToLoginButton = findViewById(R.id.backToLoginButton);
        db = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = registerUsernameEditText.getText().toString().trim();
                String password = registerPasswordEditText.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (! isValidPassword(password))
                {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters with 1 letter, 1 digit, and 1 special symbol", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.insertUser(username, password))
                    {
                        Toast.makeText(RegisterActivity.this, "Registration succesful",Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean isValidPassword(String password)
    {
        if(password.length() < 8 ) return false;
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for(char c: password.toCharArray()){
            if(Character.isLetter(c)) hasLetter = true;
            else if(Character.isDigit(c)) hasDigit = true;
            else if(! Character.isLetterOrDigit(c)) hasSpecial = true;

        }

        return hasDigit && hasLetter && hasSpecial;
    }
}
