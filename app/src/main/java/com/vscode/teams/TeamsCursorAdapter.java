package com.vscode.teams;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class TeamsCursorAdapter extends CursorAdapter {

    private Cursor mCursor;
    TextView name;


    public TeamsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view =  LayoutInflater.from(context).inflate(
                R.layout.teams_list_item, parent, false);

        mCursor = cursor;

        name = view.findViewById(R.id.tvTeamName);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String teamName = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.TEAM_NAME));

        name.setText(teamName);

    }
}
