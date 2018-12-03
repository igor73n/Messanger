package com.example.igor.messanger;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();
    private List<ListItem> unitedMessages = new ArrayList<>();
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor messageCursor;
    String data, message, sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        setInicisialData();

        HashMap<String, List<Message>> groupedMessages = groupedMessageByDate(messages);

        for (String date: groupedMessages.keySet()) {

            Date dateItem = new Date();
            dateItem.setDate(date);
            unitedMessages.add(dateItem);
            Log.i("Chat", "Дата  " +dateItem.getDate());


            for (Message message : groupedMessages.get(date)) {


             if(message.getSender().equals(MainActivity.MY_NAME)){
                 RightMessage rightMessage = new RightMessage();
                 rightMessage.setMessage(message);
                 unitedMessages.add(rightMessage);
                Log.i("Chat", "Правое  " +rightMessage.getMessage().getMessage());
             }
             else {
                 LeftMessage leftMessage = new LeftMessage();
                 leftMessage.setMessage(message);
                 unitedMessages.add(leftMessage);
                 Log.i("Chat", "Левое  " +leftMessage.getMessage().getMessage());
             }

            }

        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messagesList);
        adapter = new MessageAdapter(this, unitedMessages);
        recyclerView.setAdapter(adapter);

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


    private HashMap<String, List<Message>> groupedMessageByDate( List<Message> listOfMessages)  {

        HashMap<String, List<Message>> groupedMessages = new HashMap<>();

        for (Message message : listOfMessages) {

            String hashMapKey = message.getData();

            if (groupedMessages.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedMessages.get(hashMapKey).add(message);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<Message> list = new ArrayList<>();
                list.add(message);
                groupedMessages.put(hashMapKey, list);
            }
        }


        return groupedMessages;
    }


}



