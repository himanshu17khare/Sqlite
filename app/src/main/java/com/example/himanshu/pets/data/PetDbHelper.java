package com.example.himanshu.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.himanshu.pets.CatalogActivity;

public class PetDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="shelter.db";
    public static final int VERSION=1;
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE="CREATE TABLE "+ PetContract.PetEntry.TABLE_NAME+"( "
                +PetContract.PetEntry._id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +PetContract.PetEntry.name+ " TEXT NOT NULL, "
                +PetContract.PetEntry.breed+ " TEXT, "
                +PetContract.PetEntry.gender+ " INTEGER NOT NULL, "
                +PetContract.PetEntry.weight+ " INTEGER NOT NULL DEFAULT 0);";
         db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
