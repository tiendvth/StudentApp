package com.example.crudstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddStudent;
    private StudentAdapter studentAdapter;
    private ListView listViewStudent;
    private List<Student> students;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        sqLiteHelper = new SQLiteHelper(this);
        students = sqLiteHelper.findAllStudent();
        studentAdapter = new StudentAdapter(this, students, sqLiteHelper);
        listViewStudent = findViewById(R.id.listViewStudent);
        listViewStudent.setAdapter(studentAdapter);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}