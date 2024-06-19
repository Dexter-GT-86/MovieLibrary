package com.example.movielibrary.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movielibrary.provider.MovieDatabase;

public class MovieContentProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "fit2081.app.hengyu";

    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public MovieContentProvider() {
    }

    MovieDatabase db;


    private static final UriMatcher sUriMatcher = createUriMatcher();


    //multiple row
    private static final int MULTIPLE_ROWS_MovieDB = 1;
    //single row
    private static final int SINGLE_ROW_MovieDB = 2;

//    private static final int MULTIPLE_ROWS_USERS = 3;
//    private static final int SINGLE_ROW_USERS = 4;


    //
    private static UriMatcher createUriMatcher() {

        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        //sUriMatcher will return code 1 if uri like authority/tasks
        uriMatcher.addURI(authority, MovieDB.TABLE_NAME, MULTIPLE_ROWS_MovieDB);

        //sUriMatcher will return code 2 if uri like e.g. authority/tasks/7 (where 7 is id of row in tasks table)
        uriMatcher.addURI(authority, MovieDB.TABLE_NAME + "/#", SINGLE_ROW_MovieDB);

//        //sUriMatcher will return code 1 if uri like authority/users
//        uriMatcher.addURI(authority, "users", MULTIPLE_ROWS_USERS);
//
//        //sUriMatcher will return code 2 if uri like e.g. authority/users/7 (where 7 is id of row in users table)
//        uriMatcher.addURI(authority, "users" + "/#", SINGLE_ROW_USERS);

        return uriMatcher;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(MovieDB.TABLE_NAME, selection, selectionArgs);

        return deletionCount;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(MovieDB.TABLE_NAME, 0, contentValues);

        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db =MovieDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    //select xxx=projection from xxx=table where xxx=selection order by xxx=order by
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(MovieDB.TABLE_NAME);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);

        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(MovieDB.TABLE_NAME, 0, values, selection, selectionArgs);

        return updateCount;
    }
}