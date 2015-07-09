package com.kyostudios.filmjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{

	SQLiteDatabase db;

	public DatabaseHelper(Context context) {
		super(context, "filmJournal2.db", null, 1);
		Log.d("TESTING", "Created Database filmJournal");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {


	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS FilmRolls");
		db.execSQL("DROP TABLE IF EXISTS Cameras");
		db.execSQL("DROP TABLE IF EXISTS Lenses");
		db.execSQL("DROP TABLE IF EXISTS Photos");
		onCreate(db);
	}

    public void createDatabase() {
        /*
			There are four tables {FilmRolls, Cameras, Lenses, Photos}
			The fields AutoFocus and Flash are listed as INTEGER, but should be treated as Boolean.
			To work as such, use a value of 0 or 1 to denote false, true.

			The nickname field in each table will be used to identify the record separate from the
			_id field. I noticed that when using the _id field for identifying records, it didn't
			really work with listView's, as the position of the selected item would not match up
			with the _id, and id's won't adjust upon deletion of older records.

			The nickname is made up of different parts of the records.
			EXAMPLE==================================================
			Scenario:

			    Camera Make: Canon
			    Camera Model:Rebel 2000

			    Examine database for the count of all records that contain the make and model of the
			    camera, then add 1.

			    Nickname = Make + " " + Model + " #" + count

			    When output to the listView, it will look as such:
			     _____________________
			    |item 1               |
			    |---------------------|
			    |item 2               |
			    |---------------------|
			    |Canon Rebel 2000 #1  |
			    |_____________________|

			    If item is selected, and an option chosen, it will use the value in the text
			    of the selected item to query for the nickname of the record, which should return
			    all necessary information.
		*/
            db = this.getReadableDatabase();
            db.execSQL("CREATE TABLE FilmRolls (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "RollNumber INTEGER, Brand TEXT, Type TEXT, ISO INTEGER, Exposures INTEGER, " +
                    "ExpireDate TEXT, Nickname TEXT);");
            db.execSQL("CREATE TABLE Cameras (_id INTEGER PRIMARY KEY AUTOINCREMENT, Make TEXT, " +
                    "Model TEXT, Number INTEGER, Nickname TEXT);");
            db.execSQL("CREATE TABLE Lenses (_id INTEGER PRIMARY KEY AUTOINCREMENT, Make TEXT, " +
                    "Model TEXT, Type TEXT, MinF REAL, MaxF REAL, MinLength INTEGER, " +
                    "MaxLength INTEGER, Autofocus INTEGER, Number INTEGER, Nickname TEXT);");
            db.execSQL("CREATE TABLE Photos (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "RollNumber INTEGER, Name TEXT, RollPosition INTEGER, fStop REAL, " +
                    "ShutterSpeed TEXT, FocalLength INTEGER, ExposureComp REAL, Flash INTEGER, " +
                    "Camera INTEGER, Lens INTEGER, Number INTEGER, Nickname TEXT);");
            Log.d("TESTING", "Created table FilmRolls");
            Log.d("TESTING", "Created table Cameras");
            Log.d("TESTING", "Created table Lenses");
            Log.d("TESTING", "Created table Photos");

    }

    public void startDatabase(){
        boolean dbExists = checkDataBase();
        Log.d("TESTING", "checkDatabase() has returned " + Boolean.toString(dbExists));
        if (dbExists) {

        }
        else {
            createDatabase();
        }
    }
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = "/data/data/com.kyostudios.filmjournal/databases/filmJournal2.db";
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();
            return checkDB != null ? true : false;
        }
        else
            return false;

    }

    public ArrayList<String> getCameras(){
        String[] columns = new String[]{"_id", "Make", "Model", "Nickname"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Cameras", columns,"_id >= 1", null, null, null, null);
        ArrayList<String> results = new ArrayList<>();
        if(c!=null){
            c.moveToFirst();
            do {
                String nickname = c.getString(c.getColumnIndex("Nickname"));
                results.add(nickname);
            }while(c.moveToNext());
        return results;

        }
        return null;
    }

    public ArrayList<String> getFilmRolls(){
        String[] columns = new String[]{"_id", "RollNumber", "Brand", "Type", "ISO","Exposures", "ExpireDate", "Nickname"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("FilmRolls", columns,"_id >= 1", null, null, null, null);

        ArrayList<String> results = new ArrayList<>();
        if(c!=null){
            c.moveToFirst();
            do {
                long id = c.getLong(c.getColumnIndex("_id"));
                int rollNumber = c.getInt(c.getColumnIndex("RollNumber"));
                String brand = c.getString(c.getColumnIndex("Brand"));
                String type = c.getString(c.getColumnIndex("Type"));
                int ISO = c.getInt(c.getColumnIndex("ISO"));
                int exposures = c.getInt(c.getColumnIndex("Exposures"));
                String expireDate = c.getString(c.getColumnIndex("ExpireDate"));
                String nickname = c.getString(c.getColumnIndex("Nickname"));
                results.add(nickname);
            }while(c.moveToNext());
            return results;

        }
        return null;
    }

    public ArrayList<String> getLenses(){
        String[] columns = new String[]{"_id", "Make", "Model"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Cameras", columns,"_id >= 1", null, null, null, null);
        ArrayList<String> results = new ArrayList<>();
        if(c!=null){
            c.moveToFirst();
            do {
                long id = c.getLong(c.getColumnIndex("_id"));
                String make = c.getString(c.getColumnIndex("Make"));
                String model = c.getString(c.getColumnIndex("Model"));

                results.add( Long.toString(id) + ": " + make + " " + model);
            }while(c.moveToNext());
            return results;

        }
        return null;
    }

    public ArrayList<String> getPhotos(){
        String[] columns = new String[]{"_id", "Make", "Model"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Cameras", columns,"_id >= 1", null, null, null, null);
        ArrayList<String> results = new ArrayList<>();
        if(c!=null){
            c.moveToFirst();
            do {
                long id = c.getLong(c.getColumnIndex("_id"));
                String make = c.getString(c.getColumnIndex("Make"));
                String model = c.getString(c.getColumnIndex("Model"));

                results.add( Long.toString(id) + ": " + make + " " + model);
            }while(c.moveToNext());
            return results;

        }
        return null;
    }
    public void removeCamera(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Cameras", "_id = " + id, null);

    }

    public String getCameraModel(long l){
        String[] columns = new String[]{"_id", "Make", "Model"};
        Cursor c = db.query("Cameras", columns, "_id = 1", null, null, null, null);
        if(c!=null){
            c.moveToFirst();
            String model = c.getString(2);
            return model;
        }
        return null;
    }
}
