package com.electricalmynotes4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class directPdfViewer extends AppCompatActivity {
PDFView directPdfViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_pdf_viewer);
        directPdfViewer=findViewById(R.id.assetPdfView);
        directPdfViewer.fromAsset("module3.pdf").load();
    }
}