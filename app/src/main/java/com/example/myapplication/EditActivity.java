package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText title, description, priority, status;
    String Title, Description, Priority, Status, Id;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        priority = findViewById(R.id.priority);
        status = findViewById(R.id.status);
        progressDialog = new ProgressDialog(this);
        button = findViewById(R.id.button);

        Id = getIntent().getStringExtra("id");
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        priority.setText(getIntent().getStringExtra("priority"));
        status.setText(getIntent().getStringExtra("status"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                        if (TextUtils.isEmpty(Priority)) {
                            priority.setError("Priority Cannot be Empty");
                            valid = false;
                        } else {
                            valid = true;

                            if (TextUtils.isEmpty(Status)) {
                                status.setError("Status Cannot be Empty");
                                valid = false;
                            } else {
                                valid = true;
                            }
                        }
                    }
                }

                if (valid) {
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                    // Update the local list
                    Model newItem = new Model(null, Title, Description, Status, Priority);
                    // Add the new item to the list
                    DataRepository.getInstance();
                    DataRepository.updateItem(newItem);
                    // Notify the ListActivity to refresh the list
                    setResult(RESULT_OK);
                    Intent intent = new Intent(EditActivity.this, ListActivity.class);
                    startActivity(intent);

                    progressDialog.dismiss();
                    Toast.makeText(EditActivity.this, "Edit Data Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

//    private void updateLocalList(String id, String title, String description, String priority, String status) {
//        for (Model item : ListActivity.ma.getListItems()) {
//            if (item.getId().equals(id)) {
//                item.setUId(title);
//                item.setName(description);
//                item.setAddress(priority);
//                item.setPhone(status);
//                break;
//            }
//        }
//    }
}
