package com.electricalmynotes4u;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class pdfAdapter extends RecyclerView.Adapter<pdfViewHolder> {
    private Context context;
    private List<DataClass> dataList;


    public pdfAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public pdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdfview,parent,false);
        return new pdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfViewHolder holder, int position) {
        holder.pdfTextView.setText(dataList.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,notesPdfViewer.class);
                intent.putExtra("pdf",dataList.get(holder.getAdapterPosition()).getPdf() );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class pdfViewHolder extends RecyclerView.ViewHolder{
    TextView pdfTextView;
    CardView cardView;
    public pdfViewHolder(@NonNull View itemView) {
        super(itemView);
        pdfTextView=itemView.findViewById(R.id.pdfTextView);
        cardView=itemView.findViewById(R.id.cardView);
    }
}
