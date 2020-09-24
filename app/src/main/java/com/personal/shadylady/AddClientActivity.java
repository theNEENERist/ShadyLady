package com.personal.shadylady;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddClientActivity extends AppCompatActivity {
    EditText eText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eText = findViewById(R.id.EditTextDob);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(AddClientActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        saveButton = findViewById(R.id.ButtonSaveClient);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClient();
            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void saveClient() {
        View layout = findViewById(R.id.layout_add_client);
        final EditText firstNameField = layout.findViewById(R.id.EditTextFirstName);
        String fName = firstNameField.getText().toString();

        final EditText lastNameField = findViewById(R.id.EditTextLastName);
        String lName = lastNameField.getText().toString();

        final EditText emailField = findViewById(R.id.EditTextEmail);
        String email = emailField.getText().toString();

        final EditText dobField = findViewById(R.id.EditTextDob);
        String dob = dobField.getText().toString();

        final EditText notesField = findViewById(R.id.EditTextNotes);
        String notes = notesField.getText().toString();

        final Spinner genderSpinner = findViewById(R.id.SpinnerGender);
        String gender = genderSpinner.getSelectedItem().toString();

        final EditText phoneField = findViewById(R.id.EditTextPhone);
        String phone = phoneField.getText().toString();

        final EditText addrLn1Field = findViewById(R.id.addressLine1);
        String addrLn1 = addrLn1Field.getText().toString();

        final EditText cityField = findViewById(R.id.City);
        String city = cityField.getText().toString();

        final Spinner stateSpinner = findViewById(R.id.SpinnerState);
        String state = stateSpinner.getSelectedItem().toString();

        final EditText zipField = findViewById(R.id.zip);
        String zip = zipField.getText().toString();

        final EditText epayField = findViewById(R.id.epay);
        String epay = zipField.getText().toString();

        Map<String, Object> client = new HashMap<>();
        client.put("first", fName);
        client.put("last", lName);
        client.put("born", dob);
        client.put("gender", gender);
        client.put("phone", phone);
        client.put("email", email);
        client.put("epay", epay);
        client.put("notes", notes);

        CustomAdapter.addClient(client);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}