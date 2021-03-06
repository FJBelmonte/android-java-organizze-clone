package com.coffeit.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeit.organizze.R;
import com.coffeit.organizze.config.ConfigFirebase;
import com.coffeit.organizze.helper.DateCustom;
import com.coffeit.organizze.model.Financial;
import com.coffeit.organizze.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText fieldDate, fieldCategory, fieldDesc;
    private EditText fieldValue;
    private FloatingActionButton btnSave;
    private Financial financial;
    private DatabaseReference firebaseRef = ConfigFirebase.getDatabase();
    private FirebaseAuth firebaseAuth = ConfigFirebase.getFirebaseAuth();
    private Double totalExpense;
    private Double finalExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        fieldDate = findViewById(R.id.editDate);
        fieldCategory = findViewById(R.id.editCategory);
        fieldDesc = findViewById(R.id.editDesc);
        fieldValue = findViewById(R.id.editValue);
        fieldDate.setText(DateCustom.actualDate());
        setExpense();

        btnSave = findViewById(R.id.fabSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Double fieldExpenseValue = Double.parseDouble(fieldValue.getText().toString());

                    financial = new Financial();
                    financial.setValue(fieldExpenseValue);
                    financial.setCategory(fieldCategory.getText().toString());
                    financial.setDesc(fieldDesc.getText().toString());
                    financial.setDate(fieldDate.getText().toString());
                    financial.setType("e");
                    finalExpense = fieldExpenseValue + totalExpense;
                    DatabaseReference userRef = firebaseRef.child("users").child(firebaseAuth.getCurrentUser().getUid());
                    userRef.child("expense").setValue(finalExpense);
                    financial.save();
                    finish();
                }
            }
        });
    }

    public boolean validateFields() {
        if (!fieldDate.getText().toString().isEmpty()) {
            if (!fieldValue.getText().toString().isEmpty()) {
                if (!fieldCategory.getText().toString().isEmpty()) {
                    if (!fieldDesc.getText().toString().isEmpty()) {
                        return true;
                    } else {
                        Toast.makeText(DespesaActivity.this, "Descrição não foi preenchido", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(DespesaActivity.this, "Categoria não foi preenchido", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(DespesaActivity.this, "Valor não foi preenchido", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(DespesaActivity.this, "Data não foi preenchida", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void setExpense(){
         DatabaseReference userRef = firebaseRef.child("users").child(firebaseAuth.getCurrentUser().getUid());
         userRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 User user = dataSnapshot.getValue(User.class);
                 totalExpense = user.getExpense();

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
    }
}
