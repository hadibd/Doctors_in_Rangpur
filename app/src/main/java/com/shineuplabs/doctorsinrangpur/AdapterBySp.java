package com.shineuplabs.doctorsinrangpur;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class AdapterBySp extends FirestoreRecyclerAdapter<ModelDoctorList, AdapterBySp.SpListHolder> {

    private OnItemClickListener listener;

    public AdapterBySp(@NonNull FirestoreRecyclerOptions<ModelDoctorList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SpListHolder holder, int position, @NonNull ModelDoctorList model) {
        holder.Name.setText(model.getName());
    }

    @NonNull
    @Override
    public SpListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_by_sp, parent, false);
        return new SpListHolder(view);
    }

    class SpListHolder extends RecyclerView.ViewHolder {

        TextView Name;

        public SpListHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.doctorListItemSpTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
