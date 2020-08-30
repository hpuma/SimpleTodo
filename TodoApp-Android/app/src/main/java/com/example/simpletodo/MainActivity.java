package com.example.simpletodo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Button;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  // Data
  List<String> items;

  // Components
  Button btnAdd;
  EditText etItem;
  RecyclerView rvItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btnAdd = findViewById(R.id.btnAdd);
    etItem = findViewById(R.id.etItem);
    rvItems = findViewById(R.id.rvItems);

    // Creating data
    items = new ArrayList<>();
    items.add("Buy Something");
    items.add("Go to the Gym");
    items.add("Have Fun!");

    // Setting data
    ItemsAdapter itemsAdapter = new ItemsAdapter(items);
    rvItems.setAdapter(itemsAdapter);
    rvItems.setLayoutManager(new LinearLayoutManager(this));
  }
}