package com.example.crudstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "studentApp";

    private static final String TABLE_STUDENT = "students";

    private static final String KEY_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TEL = "tel";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " VARCHAR(255),"
                + COLUMN_EMAIL + " VARCHAR(255),"
                + COLUMN_TEL + " VARCHAR(20)" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        // Create tables again
        onCreate(db);
    }

    public void createStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_TEL, student.getTel());
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public Student findStudentById(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[] {
                        KEY_ID,COLUMN_NAME,COLUMN_EMAIL, COLUMN_TEL
                }, KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Student student = new Student(Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return student;
    }

    public List<Student> findAllStudent() {
        List<Student> students = new ArrayList<>();
        String txtQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(txtQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getLong(0));
                student.setName(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                student.setTel(cursor.getString(3));
                students.add(student);
            }while (cursor.moveToNext());
        }
        return students;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_TEL, student.getTel());

        // updating row
        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }
}