package com.electricalmynotes4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class notesActivity extends AppCompatActivity {
    RecyclerView firstSemRecycler;
    DatabaseReference databaseReference;
    pdfAdapter sem1Adapter;
    FirebaseDatabase firebaseDatabase;
    List<DataClass> list;
    ProgressDialog dialog;
    ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        firstSemRecycler = findViewById(R.id.notesRecycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(notesActivity.this,1);
        firstSemRecycler.setLayoutManager(new LinearLayoutManager(notesActivity.this));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");

        list=new ArrayList<>();
        sem1Adapter=new pdfAdapter(this,list);
        firstSemRecycler.setAdapter(sem1Adapter);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("myNotes4U").child("sem1").child("notes");
        Log.d("fLocationPath", String.valueOf(databaseReference));
        dialog.show();
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item:snapshot.getChildren()){
                    DataClass dataClass=item.getValue(DataClass.class);
                    String nam=dataClass.getName();
                    Log.d("pdfname",nam);
                    list.add(dataClass);
                }sem1Adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}