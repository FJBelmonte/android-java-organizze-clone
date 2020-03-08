package com.coffeit.organizze.activity;

import android.content.Intent;
import android.os.Bundle;

import com.coffeit.organizze.config.ConfigFirebase;
import com.coffeit.organizze.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.coffeit.organizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;

public class HomeActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private TextView textWelcome, textTotal;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private Double totalExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        calendarView.findViewById(R.id.calendarView);
        textWelcome.findViewById(R.id.textWelcome);
        textTotal.findViewById(R.id.textTotal);
        databaseReference = ConfigFirebase.getDatabase();
        auth = ConfigFirebase.getFirebaseAuth();
        returnStatus();

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void btnDespesa(View view) {
        startActivity(new Intent(this, DespesaActivity.class));
    }

    public void btnReceita(View view) {
        startActivity(new Intent(this, ReceitaActivity.class));
    }

    public void returnStatus(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                Double total = user.getIncome() - user.getExpense();

                textWelcome.setText("Olá, "+user.getName());
                textTotal.setText(decimalFormat.format(total));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
