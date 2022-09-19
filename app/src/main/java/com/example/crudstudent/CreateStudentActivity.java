package com.example.crudstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateStudentActivity extends AppCompatActivity {
    private Button btnBackToList;
    private SQLiteHelper sqLiteHelper;
    private EditText editName;
    private EditText editEmail;
    private EditText editTel;
    private Button btnSaveStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_student);
        btnBackToList = findViewById(R.id.btnBackToList);
        sqLiteHelper = new SQLiteHelper(this);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editTel = findViewById(R.id.editTel);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);
        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(editName.getText().toString());
                student.setEmail(editEmail.getText().toString());
                student.setTel(editTel.getText().toString());
                sqLiteHelper.createStudent(student);
                backToList();
            }
        });
        btnBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToList();
            }
        });
    }

    public void backToList() {
        Intent intent = new Intent(CreateStudentActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
