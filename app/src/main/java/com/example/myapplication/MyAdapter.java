package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Model> listItems;
    private Context context;

    public MyAdapter(List<Model> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView title;
        public TextView description;
        public TextView priority;
        public TextView status;
        public CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);
            status = itemView.findViewById(R.id.status);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Model listItem = listItems.get(position);
        holder.id.setText(listItem.getId());
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());
        holder.priority.setText(listItem.getPriority());
        holder.status.setText(listItem.getStatus());

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final CharSequence[] dialogItems = {"View Data", "Edit Data", "Delete Data"};
                builder.setTitle(listItem.getTitle());
                builder.setItems(dialogItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(view.getContext(), DetailData.class);
                                intent.putExtra("id", listItem.getId());
                                intent.putExtra("title", listItem.getTitle());
                                intent.putExtra("description", listItem.getDescription());
                                intent.putExtra("priority", listItem.getPriority());
                                intent.putExtra("status", listItem.getStatus());
                                view.getContext().startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(view.getContext(), EditActivity.class);
                                intent2.putExtra("id", listItem.getId());
                                intent2.putExtra("title", listItem.getTitle());
                                intent2.putExtra("description", listItem.getDescription());
                                intent2.putExtra("priority", listItem.getPriority());
                                intent2.putExtra("status", listItem.getStatus());
                                view.getContext().startActivity(intent2);
                                break;
                            case 2:
                                showDeleteConfirmationDialog(view.getContext(), listItem);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Model> newList) {
        listItems = newList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showDeleteConfirmationDialog(Context context, final Model listItem) {
        AlertDialog.Builder builderDel = new AlertDialog.Builder(context);
        builderDel.setTitle(listItem.getTitle());
        builderDel.setMessage("Are You Sure, You Want to Delete This Task?");
        builderDel.setPositiveButton("Yes", (dialogInterface, i) -> {
            DataRepository.getInstance();
            DataRepository.deleteItem(listItem);
            // Notify the ListActivity to refresh the list
            Toast.makeText(this.context, "Successfully Deleted Task " + listItem.getTitle(), Toast.LENGTH_LONG).show();
            dialogInterface.dismiss();
            Intent intent = new Intent(context, ListActivity.class);
            startActivity(context, intent, null);
        });

        builderDel.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builderDel.create().show();
    }
}
