package com.example.igor.messanger;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


 class DatabaseHelper extends SQLiteOpenHelper {

     private static String DB_PATH;
     private static String DB_NAME = "messager.db";
     private static final int SCHEMA = 1;
     static final String TABLE_USERS = "users";
     static final String COLUMN_NAME = "name";
     static final String COLUMN_MESSAGE ="message";
     static final String COLUMN_DATA = "data";
     static final String COLUMN_SENDER = "sender";
     private Context myContext;

     public DatabaseHelper(Context context) {
         super(context, DB_NAME, null, SCHEMA);
         this.myContext = context;
         DB_PATH =context.getFilesDir().getPath() + DB_NAME;
     }

     @Override
     public void onCreate(SQLiteDatabase db) {

     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

     }

     void create_db(){
         InputStream myInput = null;
         OutputStream myOutput = null;
         try {
             File file = new File(DB_PATH);
             if (!file.exists()) {
                 this.getReadableDatabase();
                 myInput = myContext.getAssets().open(DB_NAME);
                 String outFileName = DB_PATH;
                 myOutput = new FileOutputStream(outFileName);
                 byte[] buffer = new byte[1024];
                 int length;
                 while ((length = myInput.read(buffer)) > 0) {
                     myOutput.write(buffer, 0, length);
                 }

                 myOutput.flush();
                 myOutput.close();
                 myInput.close();
             }
         }
         catch(IOException ex){
             Log.d("DatabaseHelper", ex.getMessage());
         }
     }
     public SQLiteDatabase open()throws SQLException {

         return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
     }

 }
