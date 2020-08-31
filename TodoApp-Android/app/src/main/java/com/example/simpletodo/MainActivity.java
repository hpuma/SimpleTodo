package com.example.simpletodo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.IOException;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import java.io.File;

public class MainActivity extends AppCompatActivity {
  // Data
  List<String> items;

  // Components
  Button btnAdd;
  EditText etItem;
  RecyclerView rvItems;
  ItemsAdapter itemsAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnAdd = findViewById(R.id.btnAdd);
    etItem = findViewById(R.id.etItem);
    rvItems = findViewById(R.id.rvItems);

    // Creating data
    loadItems(); // Getting items from data file

    // Setting data
    ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {
      @Override
      public void onItemLongClicked(int position) {
        // Delete the item from the model
        items.remove(position);
        // Notify the adapter
        itemsAdapter.notifyItemRemoved(position);
        Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
        saveItems();
      }
    };

    itemsAdapter = new ItemsAdapter(items, onLongClickListener);
    rvItems.setAdapter(itemsAdapter);
    rvItems.setLayoutManager(new LinearLayoutManager(this));

    btnAdd.setOnClickListener(new View.OnClickListener() {
      // Adding todo logic
      @Override
      public void onClick(View view) {
        String todoItem = etItem.getText().toString();
        // Add item to the model
        items.add(todoItem);
        // Notify adapter that an item is inserted
        itemsAdapter.notifyItemInserted(items.size()-1);
        etItem.setText("");
        // Notification after todo is added
        Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
        saveItems();
      }
    });
  }
  // Managing Data
  private File getDataFile() {
    return new File(getFilesDir(), "data.txt");
  }
  // Load items by reading every line of the data file
  private void loadItems() {
    try {
      items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
    } catch (IOException e) {
      items = new ArrayList<>();
      Log.e("MainActivity", "Error reading items", e);
    }
  }
  // Saves the items by writing them into the data file
  private  void saveItems() {
    try {
      FileUtils.writeLines(getDataFile(), items);
    } catch (IOException e) {
      Log.e("MainActivity", "Error with writing items", e);
    }
  }
}