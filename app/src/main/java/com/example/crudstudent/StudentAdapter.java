package com.example.crudstudent;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Activity activity;
    private List<Student> students;
    private SQLiteHelper sqLiteHelper;

    public StudentAdapter(Activity activity, List<Student> students, SQLiteHelper sqLiteHelper) {
        this.activity = activity;
        this.students = students;
        this.sqLiteHelper = sqLiteHelper;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_student, parent, false);
        Student student = students.get(position);
        TextView tvStudentName = convertView.findViewById(R.id.tvStudentName);
        TextView tvStudentEmail = convertView.findViewById(R.id.tvStudentEmail);
        TextView tvStudentTel = convertView.findViewById(R.id.tvStudentTel);
        Button btnEditStudent = convertView.findViewById(R.id.btnEditStudent);
        Button btnDeleteStudent = convertView.findViewById(R.id.btnDeleteStudent);
        tvStudentName.setText(student.getName());
        tvStudentEmail.setText(student.getEmail());
        tvStudentTel.setText(student.getTel());
        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpdateStudentActivity.class);
                intent.putExtra("studentId", student.getId());
                activity.startActivity(intent);
            }
        });
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.deleteStudent(student);
                students.remove(student);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
