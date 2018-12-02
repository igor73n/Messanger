package com.example.igor.messanger;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor messageCursor;
    String data, message, sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messagesList);
        adapter = new MessageAdapter(this, messages);
        recyclerView.setAdapter(adapter);


        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        setInicisialData();

    }

        public void setInicisialData () {

            Intent intent = getIntent();
            String collocuter = intent.getStringExtra(MainActivity.INTENT_MESSAGE);
            Log.i("chat", "Получено из MainActivity"   +collocuter);
            db = databaseHelper.open();
            messageCursor = db.rawQuery("select * from " + collocuter, null );
            Log.i("chat", "Результат запроса"   +collocuter);

            if(messageCursor.moveToFirst()){

                do{

                    message = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_MESSAGE));
                    data = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_DATA));
                    sender = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_SENDER));
                    messages.add(new Message(message, sender, data));

                }

                while (messageCursor.moveToNext());

            }
        }
    }



