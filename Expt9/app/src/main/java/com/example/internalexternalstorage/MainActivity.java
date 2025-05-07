package com.example.internalexternalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView outputText;
    private Button saveInternalBtn, readInternalBtn, saveExternalBtn, readExternalBtn;
    private static final String internal_file_name = "internal_data.txt";
    private static final String external_file_name = "external_data.txt";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.output);
        saveInternalBtn = findViewById(R.id.saveInternalBtn);
        readInternalBtn = findViewById(R.id.readInternalBtn);
        saveExternalBtn = findViewById(R.id.saveExternalBtn);
        readExternalBtn = findViewById(R.id.readExternalBtn);

        saveInternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = inputText.getText().toString();
                if(!data.isEmpty())
                {
                    saveToInternalStorage(data);
                    inputText.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        readInternalBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String data = readFromInternalStorage();
                outputText.setText(data);
            }
        });

        saveExternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = inputText.getText().toString();
                if (!data.isEmpty()) {
                    saveToExternalStorage(data);
                    inputText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        readExternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = readFromExternalStorage();
                outputText.setText(data);
            }
        });


    }

    private void saveToInternalStorage(String data)
    {
        try{
            FileOutputStream fos = openFileOutput(internal_file_name, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Saved to internal storage", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error saving to internal storage", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromInternalStorage()
    {
        StringBuilder data = new StringBuilder();
        try{
            FileInputStream fis = openFileInput(internal_file_name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line ;
            while((line = br.readLine()) != null)
            {
                data.append(line).append("\n");
            }
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error reading from internal storage", Toast.LENGTH_SHORT).show();
            return "No data found in internal storage";
        }
        return data.length()>0 ? data.toString() : "No data found in internal Storage";
    }

    private void saveToExternalStorage(String data)
    {
        File externalDir = getExternalFilesDir(null);
        File file = new File(externalDir, external_file_name);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write((data.getBytes()));
            fos.close();
            Toast.makeText(this, "Saved to external storage", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error saving to external storage", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromExternalStorage()
    {
        File externalDir = getExternalFilesDir(null);
        File file = new File(externalDir, external_file_name);
        StringBuilder data = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line).append("\n");
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading from external storage", Toast.LENGTH_SHORT).show();
            return "No data found in external storage";
        }
        return data.length() > 0 ? data.toString() : "No data found in external storage";

    }



}