package roiattia.com.newpopularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {

    // Empty private constructor to avoid accidentally instantiating this contract class
    private MovieContract(){}

    // The authority, which is how the code knows which Content Provider to access
    public static final String AUTHORITY = "roiattia.com.newpopularmovies";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // The path for the "movies" directory
    public static final String PATH_MOVIES = "movie";

    /* MovieEntry defines the contents of the movie table */
    public static final class MovieEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        // Task table and column names
        public static final String TABLE_NAME = "movie";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        // column "MOVIE_ID" will store the id of the movie from the server
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PLOT = "plot";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_AVERAGE_RATING = "average_rating";
//        public static final String COLUMN_POSTER = "poster";

    }
}
