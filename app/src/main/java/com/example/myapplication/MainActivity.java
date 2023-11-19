package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText title, description, priority, status;
    String Title, Description, Priority, Status;
    Button button;
    Boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        priority = findViewById(R.id.priority);
        status = findViewById(R.id.status);
        button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Title = title.getText().toString();
            Description = description.getText().toString();
            Priority = priority.getText().toString();
            Status = status.getText().toString();
            if (TextUtils.isEmpty(Title)) {
                title.setError("Title Cannot be Empty");
                valid = false;
            } else {
                valid = true;

                if (TextUtils.isEmpty(Description)) {
                    description.setError("Description Cannot be Empty");
                    valid = false;
                } else {
                    valid = true;

                    if (TextUtils.isEmpty(Status)) {
                        priority.setError("Status Cannot be Empty");
                        valid = false;
                    } else {
                        valid = true;

                        if (TextUtils.isEmpty(Priority)) {
                            status.setError("Priority Cannot be Empty");
                            valid = false;
                        } else {
                            valid = true;
                        }
                    }
                }
            }
            if (valid) {
                // Create a new Model object with the entered data
                Model newItem = new Model(null, Title, Description, Status, Priority);
                // Add the new item to the list
                DataRepository.getInstance();
                DataRepository.addItem(newItem);
                // Notify the ListActivity to refresh the list
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
