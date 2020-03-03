package com.coffeit.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.coffeit.organizze.R;
import com.coffeit.organizze.helper.DateCustom;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText fieldDate, fieldCategory, fieldDesc;
    private EditText fieldValue;
    private FloatingActionButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        fieldDate = findViewById(R.id.editDate);
        fieldCategory = findViewById(R.id.editCategoy);
        fieldDesc = findViewById(R.id.editDesc);
        fieldValue = findViewById(R.id.editValue);

        fieldDate.setText(new DateCustom().actualDate());

        btnSave = findViewById(R.id.fabSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
