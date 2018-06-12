package com.vscode.teams;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "team.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_TEAMS = "teams";
    public static final String TEAM_ID = "_id";
    public static final String TEAM_NAME = "teamName";

    public static final String[] ALL_COLUMNS =
            {TEAM_ID, TEAM_NAME};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TEAMS + " (" +
                    TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TEAM_NAME + " TEXT " +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        onCreate(db);
    }


}
