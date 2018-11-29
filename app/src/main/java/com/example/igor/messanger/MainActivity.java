package com.example.igor.messanger;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private List<ChatItem> chatItems = new ArrayList<>();
    ChatItemAdapter adapter;
    ListView chatItemsList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor, messageCursor;
    String collocuter, lastMessage, data;
    public static final String MY_NAME = "Igor";
    public static final String INTENT_MESSAGE = "From MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView chatItemsList = (ListView) findViewById(R.id.chatItemsList);
        adapter = new ChatItemAdapter(this, R.layout.chat_item, chatItems);
        chatItemsList.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ChatItem selectedChatItem = (ChatItem) parent.getItemAtPosition(position);

             Intent intent = new Intent(MainActivity.this,ChatActivity.class);
             intent.putExtra(INTENT_MESSAGE, selectedChatItem.getCollocuter());
             startActivity(intent);
            }
        };

        chatItemsList.setOnItemClickListener(itemListener);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        setInicisialData();
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
                Log.i("main", "Собеседник "   +collocuter);
                Log.i("main", "Последнее сообщение "   +lastMessage);
                Log.i("main", "Дата и время "   +data);
                chatItems.add(new ChatItem(collocuter, lastMessage, data));
            }
            while (userCursor.moveToNext());
         }
         else
            userCursor.close();
            messageCursor.close();
        adapter.notifyDataSetChanged();
        }
    }

