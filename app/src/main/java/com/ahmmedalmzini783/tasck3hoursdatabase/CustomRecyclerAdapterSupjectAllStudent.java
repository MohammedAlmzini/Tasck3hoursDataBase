package com.ahmmedalmzini783.tasck3hoursdatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerAdapterSupjectAllStudent extends RecyclerView.Adapter<CustomRecyclerAdapterSupjectAllStudent.MyHolder> {

    Context context;
    ArrayList<Students> data;
    onItemClickListener listener;
    DpHelper dpHelper;

    public CustomRecyclerAdapterSupjectAllStudent(Context context, ArrayList<Students> data, onItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_all_stuent,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {
        Students student = data.get(i);
        holder.subjectName.setText(student.getFirstName()+" "+ student.getLastName());

        dpHelper = new DpHelper(context.getApplicationContext());

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemDelete(student.getId(), holder.getAdapterPosition());
            }
        });
        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(student, holder.getAdapterPosition());
            }
        });
        holder.recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClick(student,holder.getAdapterPosition());
                return false;
            }
        });
    }




    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        TextView suParsen;
        ConstraintLayout recyclerView;
        ImageView imageDelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.t_supName);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            imageDelete = itemView.findViewById(R.id.imageDelete);
        }
    }

    public interface onItemClickListener{
        void onItemDelete(int id,int position);
        void onItemClick(Students students, int position);
        void onItemLongClick(Students students, int position);
    }

    public void setData(ArrayList<Students> data) {
        this.data = data;
    }

}
