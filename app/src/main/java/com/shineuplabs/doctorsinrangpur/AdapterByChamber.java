package com.shineuplabs.doctorsinrangpur;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.transition.platform.Hold;
import com.google.firebase.firestore.DocumentSnapshot;

public class AdapterByChamber extends FirestoreRecyclerAdapter<ModelDoctorList, AdapterByChamber.ListHolder> {

    private OnItemClickListener listener;

    public AdapterByChamber(@NonNull FirestoreRecyclerOptions<ModelDoctorList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListHolder holder, int position, @NonNull ModelDoctorList model) {

        holder.Name.setText(model.getName());
        holder.Chamber.setText(model.getSp());
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_by_chember,
                parent, false);
        return new ListHolder(v);
    }

    class ListHolder extends RecyclerView.ViewHolder {

        TextView Name, Chamber;
        public ListHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.NameTvByChamber);
            Chamber = itemView.findViewById(R.id.SpTvByChamber);

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