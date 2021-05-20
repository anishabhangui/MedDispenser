package com.example.android.bluetoothchat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeItem extends ItemTouchHelper.SimpleCallback {

    MedicationAdapter mMedAdapter;

    SwipeItem(MedicationAdapter medicationAdapter) {
        super(0,ItemTouchHelper.LEFT);
        this.mMedAdapter = medicationAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getBindingAdapterPosition();
        this.mMedAdapter.removeItem(position);
        mMedAdapter.notifyDataSetChanged();
    }
}
