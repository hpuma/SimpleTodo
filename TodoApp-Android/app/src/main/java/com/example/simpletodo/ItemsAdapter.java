package com.example.simpletodo;

import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;

// Displays data from the model into a row
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

  List<String> items;

  public ItemsAdapter(List<String> items) {
    this.items = items;
  }
  @NonNull
  @Override// Creates each view
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    // Use layout inflator to inflate a view
    // Wrap it inside a View Holder and return it
    View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
    return new ViewHolder(todoView);
  }
  @Override // Taking data at a particular position and putting it in the view holder
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Grab the item at the position
    String item = items.get(position);
    // Bind the item into the specified view holder
    holder.bind(item);
  }
  // Returns the amount of items on the list
  @Override
  public int getItemCount() {
    return items.size();
  }


  // Provides easy access to views that represents each row of the list
  class ViewHolder extends RecyclerView.ViewHolder {
      TextView tvItem;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvItem = itemView.findViewById(android.R.id.text1);
    }

    // Updates the view inside of the view holder with the String item data
    public void bind(String item) {
      tvItem.setText(item);
    }
  }
}