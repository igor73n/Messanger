package com.example.igor.messanger;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    MessageAdapter adapter;
    private List<Message> messages = new ArrayList<>();
    private List<ListItem> unitedMessages = new ArrayList<>();
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor messageCursor, cursor;
    String data, message, sender, collocuter, id;
    Integer i;
    Integer m = 0;
    String count = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
        setInicisialData();
        setInicisialUnited();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messagesList);
        adapter = new MessageAdapter(this, unitedMessages);
        recyclerView.setAdapter(adapter);
    }

    public void setInicisialUnited() {

        LinkedHashMap<String, List<Message>> groupedMessages = groupedMessageByDate(messages);

        if(!count.equals("1") )

        {
            LoadMore loadMore = new LoadMore();
            loadMore.setLoadMore("Download next 5 messages");
            unitedMessages.add(loadMore);

        }

        for (String date : groupedMessages.keySet()) {

            Date dateItem = new Date();
            dateItem.setDate(date);
            unitedMessages.add(dateItem);

            for (Message message : groupedMessages.get(date)) {


                if (message.getSender().equals(MainActivity.MY_NAME)) {
                    RightMessage rightMessage = new RightMessage();
                    rightMessage.setMessage(message);
                    unitedMessages.add(rightMessage);
                    Log.i("Chat", "Правое  " + rightMessage.getMessage().getMessage());
                } else {
                    LeftMessage leftMessage = new LeftMessage();
                    leftMessage.setMessage(message);
                    unitedMessages.add(leftMessage);
                    Log.i("Chat", "Левое  " + leftMessage.getMessage().getMessage());
                }
            }
        }
    }

    public void setInicisialData () {

        Intent intent = getIntent();
        collocuter = intent.getStringExtra(MainActivity.INTENT_MESSAGE);
        db = databaseHelper.open();
        i = 5;
        messageCursor = db.rawQuery("select * from " + collocuter +" where id > (select max(id)-" +i +" from " +collocuter +" )" , null );
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


        public  void loadMore(){
            db = databaseHelper.open();
            i = i + 5;
            messageCursor = db.rawQuery("select * from " + collocuter +" where id > (select max(id)-" +i +" from " +collocuter +") and id <= (select max(id)-" +i +"+ 5 from " +collocuter +") " , null );
            Log.i("chat", "Результат запроса"   +"select * from " + collocuter +" where id > (select max(id)-" +i +" from " +collocuter +") and id < (select max(id)-" +i +"+ 5 from " +collocuter +") ");
            unitedMessages.clear();

            if(messageCursor.moveToFirst()){

                do{
                    message = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_MESSAGE));
                    data = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_DATA));
                    sender = messageCursor.getString(messageCursor.getColumnIndex(DatabaseHelper.COLUMN_SENDER));
                    messages.add(m,new Message(message, sender, data));
                    m++;
                }
                while (messageCursor.moveToNext());
            }

            messageCursor.moveToFirst();
            count = messageCursor.getString(messageCursor.getColumnIndex("id"));
            m = 0;
            setInicisialUnited();
            adapter.notifyDataSetChanged();
        }


    private LinkedHashMap<String, List<Message>> groupedMessageByDate( List<Message> listOfMessages)  {

        LinkedHashMap<String, List<Message>> groupedMessages = new LinkedHashMap<>();

        for (Message message : listOfMessages) {

            String hashMapKey = message.getData().substring(0,10);

            if (groupedMessages.containsKey(hashMapKey)) {
                groupedMessages.get(hashMapKey).add(message);
            } else {
                List<Message> list = new ArrayList<>();
                list.add(message);
                groupedMessages.put(hashMapKey, list);
            }
        }
        return groupedMessages;
    }
}



