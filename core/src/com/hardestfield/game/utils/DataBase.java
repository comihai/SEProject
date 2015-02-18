package com.hardestfield.game.utils;

import com.hardestfield.game.interfaces.DatabaseResult;

/**
 * Created by mihai on 1/24/2015.
 * THis class that needs to be implemented on Android and Desktop applications
 */
public abstract class DataBase implements DatabaseResult {

    protected static String databaseName = "HardestField";
    protected static DataBase instance = null;
    protected static int version = 1;

    //Runs a sql query like "create".
    public abstract void execute(String sql);

    //Identical to execute but returns the number of rows affected (useful for updates)
    public abstract int executeUpdate(String sql);

    //Runs a query and returns an Object with all the results of the query. [Result Interface is defined below]
    public abstract DatabaseResult query(String sql);

    public void onCreate() {
        execute("CREATE TABLE 'highscores' ('_id' INTEGER PRIMARY KEY  NOT NULL , 'name' VARCHAR NOT NULL , 'score' INTEGER NOT NULL );");
        execute("INSERT INTO 'highscores'(name,score) values ('Mihai',2000)");
    }

    public void onUpgrade() {
        execute("DROP TABLE IF EXISTS 'highscores';");
        onCreate();
    }

}
