package bernhardwebstudio.shadowtravelor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tim on 16.09.2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOCATION = "Location";
    public static final String COLUMN_LONG = "Longitude";
    public static final String COLUMN_LAT = "Latitude";

    public static final String TABLE_LOC_TIME_CON = "LocationTime";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_LOC = "Location";

    public static final String TABLE_ROUTE = "Route";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCORE = "Score";

    public static final String TABLE_ROUTE_POINTS = "RoutePoints";
    public static final String COLUMN_ID_ROUTE = "ID_Route";
    public static final String COLUMN_ID_POINT = "ID_Point";

    public static String createTableLocation =
            "CREATE TABLE " + TABLE_LOCATION + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LAT +
                    "real," + COLUMN_LONG + "real);";
    public static String createTableLocationTime =
            "CREATE TABLE " + TABLE_LOC_TIME_CON + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LOC +
                    "real," + COLUMN_LONG + "real);";
    public static String createTableRoute =
            "CREATE TABLE " + TABLE_LOCATION + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LAT +
                    "real," + COLUMN_LONG + "real);";
    public static String createTableRoutePoints =
            "CREATE TABLE " + TABLE_LOCATION + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LAT +
                    "real," + COLUMN_LONG + "real);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
