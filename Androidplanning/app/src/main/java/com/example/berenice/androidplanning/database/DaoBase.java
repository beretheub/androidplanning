package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Abstract class to permit all the DAO classes to communicate with the Database
 */
public abstract class DaoBase {

    protected final static int VERSION = 1;
    protected final static String NOM = Constants.DATABASE_NAME;

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DaoBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

