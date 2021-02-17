package ru.example.weathertestapp.data.listeners;

import android.database.sqlite.SQLiteDatabase;

public interface IDbTablesCreatedListener {

    void onDbTablesCreated(SQLiteDatabase db);
}
