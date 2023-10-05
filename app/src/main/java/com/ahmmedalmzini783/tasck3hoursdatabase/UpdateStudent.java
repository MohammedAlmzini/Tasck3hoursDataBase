package com.ahmmedalmzini783.tasck3hoursdatabase;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateStudent extends AppCompatActivity {
    private TextInputEditText te_first_name;
    private TextInputEditText te_last_name;
    private TextInputEditText te_Date;
    private Button addButton;
    private RecyclerView recyclerview_subject_to_Add;
    private DpHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        te_first_name = findViewById(R.id.te_first_name);
        te_last_name = findViewById(R.id.te_last_name);
        te_Date = findViewById(R.id.te_Date);
        addButton = findViewById(R.id.addButton);





        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = te_first_name.getText().toString();
                String lastName = te_last_name.getText().toString();
                String ageString = te_Date.getText().toString();

                if (firstName.isEmpty() ||lastName.isEmpty() || ageString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "يرجى إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    int year, monthOfYear, dayOfMonth;
                    Calendar dob = Calendar.getInstance();
                    dob.setTimeInMillis(0);
                    String[] dateParts = ageString.split("/");
                    if (dateParts.length == 3) {
                        dayOfMonth = Integer.parseInt(dateParts[0]);
                        monthOfYear = Integer.parseInt(dateParts[1]) - 1;
                        year = Integer.parseInt(dateParts[2]);
                        dob.set(year, monthOfYear, dayOfMonth);
                        Calendar today = Calendar.getInstance();
                        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                            age--;
                        }
                        int studentAge = age;



                        dbHelper = new DpHelper(getApplicationContext());
                        int studentId = getIntent().getIntExtra("studentId", 0);

                        dbHelper.updateStudents(studentId+"",firstName, lastName, studentAge);

                    } else {
                        Toast.makeText(getApplicationContext(), "يرجى إدخال التاريخ بالتنسيق الصحيح (DD/MM/YYYY)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        te_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateStudent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                te_Date.setText(selectedDate);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}