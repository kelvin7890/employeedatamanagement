package com.example.employeemanagementapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DBNAME="login.db";
    public DBHandler(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table admins(username Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {
        db.execSQL("drop table if exists admins");
    }
    public boolean InsertData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result= db.insert("admins", null, values);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean checkusername (String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from admins where userame=?", new String[] {username});

        if(cursor.getCount()>1)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from admins where username=? and password=?", new String[] {username, password});

        if(cursor.getCount()>1)
            return true;
        else
            return false;
    }
}
