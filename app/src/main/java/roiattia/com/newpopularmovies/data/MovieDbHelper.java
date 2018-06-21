package roiattia.com.newpopularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import roiattia.com.newpopularmovies.data.MovieContract.MovieEntry;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";

    private static final int VERSION = 1;

    MovieDbHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create movies table
        final String CREATE_TABLE = "CREATE TABLE "  + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID                + " INTEGER PRIMARY KEY, " +
                MovieEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL UNIQUE, " +
                MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_PLOT    + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_RELEASE_DATE    + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_AVERAGE_RATING    + " DOUBLE NOT NULL" +
//                MovieEntry.COLUMN_POSTER    + " BLOB NOT NULL" +
        ");";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
