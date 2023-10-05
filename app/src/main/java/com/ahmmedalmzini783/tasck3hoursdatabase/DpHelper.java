package com.ahmmedalmzini783.tasck3hoursdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.ArrayList;

public class DpHelper extends SQLiteOpenHelper {
    public static final String COL_ATTENDANCE_RATE = "attendance_rate";

    public DpHelper(@Nullable Context context) {
        super(context, "Admin", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Students.CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Students.TABLE_NAME);
        onCreate(db);
    }



    //  ******************************* Student دوال ******************************

    public long insertStudent(String firstName, String lastName, int age) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_AGE, age);

        return  db.insert(Students.TABLE_NAME, null, values);
    }




    public long updateStudents(String id, String firstName, String lastName, int age) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_AGE, age);


        return db.update(Students.TABLE_NAME, values, Students.COL_ID + "=?", new String[]{id});

    }



    public boolean deleteStudents(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(Students.TABLE_NAME, Students.COL_ID + "=?", new String[]{id});
        return rowID > 0;
    }


    public ArrayList<Students> getAllDataStudents() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Students> data = new ArrayList<>();

        String query = "SELECT * FROM " + Students.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                Students students = new Students(id, firstName, lastName, age, parthDay);
                data.add(students);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }








}
