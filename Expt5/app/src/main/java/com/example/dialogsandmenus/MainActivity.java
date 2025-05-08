package com.example.dialogsandmenus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView contextTv = findViewById(R.id.context_textview);
        Button popupMenuBtn = findViewById(R.id.popup_menu);
        Button exitBtn = findViewById(R.id.popup_button);

        // Register context menu for TextView
        registerForContextMenu(contextTv);

        // Popup Menu button (Format)
        popupMenuBtn.setOnClickListener(this::showPopupMenu);

        // Exit button
        exitBtn.setOnClickListener(v -> showExitDialog());
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    finish(); // Close the activity
                })
                .setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(this, "Exit canceled", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.bold) {
                Toast.makeText(this, "Applied Bold formatting", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.italic) {
                Toast.makeText(this, "Applied Italic formatting", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.underline) {
                Toast.makeText(this, "Applied Underline formatting", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    // Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            Toast.makeText(this, "Refreshing.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.settings) {
            Toast.makeText(this, "Opening Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.help) {
            Toast.makeText(this, "Opening Help", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.feedback) {
            Toast.makeText(this, "Opening Feedback", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cut) {
            Toast.makeText(this, "Text cut", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.copy) {
            Toast.makeText(this, "Text copied", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.paste) {
            Toast.makeText(this, "Text pasted", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}