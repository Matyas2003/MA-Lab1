package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_ITEM = 1;
    public static ListActivity ma;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setTitle("Data");

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        progressDialog = new ProgressDialog(this);

        ma = this;
        refreshList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Intent tes = new Intent(ListActivity.this, MainActivity.class);
            startActivity(tes);
        }
        return super.onOptionsItemSelected(item);
    }
    public List<Model> getListItems() {
        return DataRepository.getInstance().getItemList();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshList() {
        // Retrieve the list of items from the repository
        List<Model> listItems = DataRepository.getInstance().getItemList();
        System.out.println(listItems);
        ma = this;
        System.out.println(ma.getListItems());
        // Create or update the adapter with the new data
        if (adapter == null) {
            adapter = new MyAdapter(listItems, getApplicationContext());
            recyclerView.setAdapter(adapter);
        } else {
            ((MyAdapter) adapter).updateList(listItems);
            adapter.notifyDataSetChanged();
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    public void startAddItemActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_ITEM && resultCode == RESULT_OK) {
            refreshList();
        }
    }
}
