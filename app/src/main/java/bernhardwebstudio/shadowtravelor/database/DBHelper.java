package bernhardwebstudio.shadowtravelor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.PreparedStatement;
import java.util.GregorianCalendar;

import bernhardwebstudio.shadowtravelor.data.Location;
import bernhardwebstudio.shadowtravelor.data.LocationTimeConnection;
import bernhardwebstudio.shadowtravelor.data.Route;

/**
 * Created by Tim on 16.09.2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOCATION = "Location";
    public static final String COLUMN_LONG = "Longitude";
    public static final String COLUMN_LAT = "Latitude";

    public static final String TABLE_LOC_TIME_CON = "LocationTime";
    public static final String COLUMN_VOLUME = "Volume";
    public static final String COLUMN_USAGE = "SmartphoneUsage";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_LOC = "Location";
    public static final String COLUMN_VELOCITY = "Velocity";

    public static final String TABLE_ROUTE = "Route";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCORE = "Score";
    public static final String COLUMN_DATE = "Date";

    public static final String TABLE_ROUTE_POINTS = "RoutePoints";
    public static final String COLUMN_ID_ROUTE = "ID_Route";
    public static final String COLUMN_ID_POINT = "ID_Point";

    public static final String TABLE_PROFILE = "Profile";

    public static final String TABLE_DAY = "Day";


    public static String createTableLocation =
            "CREATE TABLE " + TABLE_LOCATION + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LAT +
                    "real," + COLUMN_LONG + "real);";
    public static String createTableLocationTime =
            "CREATE TABLE " + TABLE_LOC_TIME_CON + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_LOC +
                    "Integer," + COLUMN_VOLUME + "Integer," + COLUMN_USAGE +
                    "Integer," + COLUMN_TIME + "Integer," + COLUMN_VELOCITY + "real, FOREIGN KEY("+COLUMN_LOC+") REFERENCES artist(Location.ID));";
    public static String createTableRoute =
            "CREATE TABLE " + TABLE_ROUTE + "(" + COLUMN_ID + "Integer Primary Key ," + COLUMN_SCORE +
                    "real," + COLUMN_DATE + "real);";
    public static String createTableRoutePoints =
            "CREATE TABLE " + TABLE_ROUTE_POINTS + "(" + COLUMN_ID_ROUTE + "Integer," + COLUMN_ID_POINT +
                    "Integer, FOREIGN KEY("+COLUMN_ID_ROUTE+") REFERENCES artist(Route.ID), FOREIGN KEY("+COLUMN_ID_POINT+") REFERENCES artist(LocationTime.ID));";

    public int oldVersion = 0;
    public int currentVersion = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableLocation);
        db.execSQL(createTableLocationTime);
        db.execSQL(createTableRoute);
        db.execSQL(createTableRoutePoints);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertLocation(Location location, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LAT, location.getLatitude());
        values.put(COLUMN_LONG, location.getLongitude());
        long id = db.insert(TABLE_LOCATION, null, values);
        return id;
    }

    public long insertLocationTimeConnection(LocationTimeConnection locTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIME, locTime.getDatetime().getTimeInMillis());
        values.put(COLUMN_LOC, insertLocation(locTime.getLocation(), db));
        values.put(COLUMN_USAGE, locTime.getUsedSmartphone());
        values.put(COLUMN_VOLUME, locTime.getVolume());
        long id = db.insert(TABLE_LOC_TIME_CON, null, values);
        db.close();
        return id;
    }

    public void addVelocity(double velocity, long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO LocationTime("+COLUMN_VELOCITY+") VALUES "+velocity+ " WHERE ID = "+id+";");
        db.close();
    }

    public void insertRoute(Route route){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, route.getScore());
        values.put(COLUMN_DATE, route.getDate().getTimeInMillis());
        long id = db.insert(TABLE_ROUTE, null, values);
        for(int i=0; i<route.getRoute().size(); i++){
            LocationTimeConnection ltc = route.getRoute().get(i);
            long ltcId = getLocTimeId(ltc);
            ContentValues cValues = new ContentValues();
            cValues.put(COLUMN_ID_ROUTE, id);
            cValues.put(COLUMN_ID_POINT, ltcId);
            db.insert(TABLE_ROUTE_POINTS, null, cValues);
        }
        db.close();
    }

    public long getLocTimeId(LocationTimeConnection locTime){
        //finish implemetation
        return 0;
    }

    public LocationTimeConnection getLocationTimeConnectionByDate(GregorianCalendar cal){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE "+TABLE_LOC_TIME_CON+","+TABLE_LOCATION+
                " WHERE LocationTime.Time = "+cal.getTimeInMillis()+" AND "+
                "Location.ID = LocationTime.Location;", null);
        LocationTimeConnection ltc = new LocationTimeConnection();
        while(cursor.moveToNext()){

            return ltc;
        }
        return null;
    }
}
