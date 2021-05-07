package com.example.cinemoment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtGrmName, txtBrdName, txtContNo, txtDate, txtTime, txtAddress, txtAgenda;
    Button btnBook;
    DatabaseReference dbRef;
    Booking bkg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGrmName = findViewById(R.id.inputName);
        txtBrdName = findViewById(R.id.inputName2);
        txtContNo = findViewById(R.id.inputPhone);
        txtDate = findViewById(R.id.inputDate);
        txtTime = findViewById(R.id.inputTime);
        txtAddress = findViewById(R.id.inputAddress);
        txtAgenda = findViewById(R.id.inputAgenda);

        btnBook = findViewById(R.id.button4);

        bkg = new Booking();
    }


    public void onClick(View view) {


        dbRef = FirebaseDatabase.getInstance("https://cinemoment-8c5c9-default-rtdb.firebaseio.com/").getReference().child("Booking");
        try {
            if (TextUtils.isEmpty(txtGrmName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Groom's Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtBrdName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Bride's Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtContNo.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtDate.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Event Date", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtTime.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Event Time", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtAddress.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Venue", Toast.LENGTH_SHORT).show();
            else {

                bkg.setGrmName(txtGrmName.getText().toString().trim());
                bkg.setBrdName(txtBrdName.getText().toString().trim());
                bkg.setContact(Integer.parseInt(txtContNo.getText().toString().trim()));
                bkg.setDate(txtDate.getText().toString().trim());
                bkg.setTime(txtTime.getText().toString().trim());
                bkg.setAddress(txtAddress.getText().toString().trim());
                bkg.setAgenda(txtAgenda.getText().toString().trim());

                dbRef.push().setValue(bkg);
                Toast.makeText(getApplicationContext(), "Details Saved Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,PaymentUi.class);
                startActivity(intent);


            }


        } catch (NumberFormatException e) {

            Toast.makeText(getApplicationContext(), "Please enter valid contact #", Toast.LENGTH_SHORT).show();
            clearControls();
        }

    }

    private void clearControls() {

        txtGrmName.setText("");
        txtBrdName.setText("");
        txtContNo.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtAddress.setText("");
        txtAgenda.setText("");

    }
}
