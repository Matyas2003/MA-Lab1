package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {
    TextView title, description, priority, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        priority = (TextView) findViewById(R.id.priority);
        status = (TextView) findViewById(R.id.status);

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        priority.setText(getIntent().getStringExtra("priority"));
        status.setText(getIntent().getStringExtra("status"));
    }
}
