package com.example.igor.messanger;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ChatItem> chatItems = new ArrayList<>();
    ChatItemAdapter adapter;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor, messageCursor;
    String collocuter, lastMessage, data, id;
    public static final String MY_NAME = "Igor";
    public static final String INTENT_MESSAGE = "From MainActivity";
    public static final String INTENT_MESSAGE_ID = "id from MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        setInicisialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.chatItemsList);
        adapter = new ChatItemAdapter(this, chatItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
   public void onResume() {
       super.onResume();
   }

    public void setInicisialData() {
        db = databaseHelper.open();
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_USERS, null);
        Log.i("main", "Результат запроса"   +userCursor);
        if (userCursor.moveToFirst()) {
            do {
                collocuter = userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                messageCursor =  db.rawQuery("select * from "+ collocuter, null);
                messageCursor.moveToLast();
                lastMessage = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_MESSAGE));
                data = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_DATA));
                id = messageCursor.getString(messageCursor.getColumnIndex("id"));
                Log.i("main", "Собеседник "   +collocuter);
                Log.i("main", "Последнее сообщение "   +lastMessage);
                Log.i("main", "Дата и время "   +data);
                Log.i("main", "Ид "   +id);
                chatItems.add(new ChatItem(collocuter, lastMessage, data, id));
            }
            while (userCursor.moveToNext());
         }
         else
            userCursor.close();
            messageCursor.close();
        }
    }

