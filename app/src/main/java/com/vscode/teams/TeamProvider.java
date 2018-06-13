package com.vscode.teams;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TeamProvider extends ContentProvider {

    private static final String AUTHORITY = "com.vscode.teams.teamprovider";
    private static final String BASE_PATH = "teams";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    private static final int TEAM = 1;
    private static final int TEAM_ID = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    public static final String CONTENT_ITEM_TYPE = "Team";

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, TEAM);
        uriMatcher.addURI(AUTHORITY, BASE_PATH +  "/#", TEAM_ID);
    }

    private SQLiteDatabase database;


    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        if (uriMatcher.match(uri) == TEAM_ID) {
            selection = DBOpenHelper.TEAM_ID + "=" + uri.getLastPathSegment();
        }

        return database.query(DBOpenHelper.TABLE_TEAMS, DBOpenHelper.ALL_COLUMNS,
                selection, selectionArgs, null, null,
                sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id = database.insert(DBOpenHelper.TABLE_TEAMS,
                null, contentValues);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return database.delete(DBOpenHelper.TABLE_TEAMS, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(DBOpenHelper.TABLE_TEAMS,
                values, selection, selectionArgs);
    }
}
