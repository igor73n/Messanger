package com.example.igor.messanger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Message> messages;
    private final int TYPE_MESSAGE_LEFT = 0;
    private final int TYPE_MESSAGE_RIGHT = 1;
    private final int TYPE_MESSAGE_DATA = 2;
    View view;
    Integer i = 0;
    String currentData = "";
    String [] monthArray = {"Zero","January", "February", "Marth", "April", "May", "June", "Jule", "August", "September", "October", "November", "December"};
    String temp;

    MessageAdapter(Context context, List<Message> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


       switch (viewType) {

         case TYPE_MESSAGE_DATA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data, parent, false);
               break;

         case TYPE_MESSAGE_LEFT:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_message, parent, false);
              break;

           case TYPE_MESSAGE_RIGHT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_message, parent, false);

        }

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {

        int type = getItemViewType(position);


        switch (type) {

            case TYPE_MESSAGE_LEFT:
                holder.message.setText(messages.get(position).getMessage());
                Log.i("MessageAdapter", "сообщение " + messages.get(position).getMessage());
                holder.data.setText(messages.get(position).getData().substring(10, 16));
                break;

            case TYPE_MESSAGE_RIGHT:
                holder.message.setText(messages.get(position).getMessage());
                holder.data.setText(messages.get(position).getData().substring(10, 16));
                break;

            case TYPE_MESSAGE_DATA:

                temp = messages.get(position).getData();


                holder.centralData.setText(messages.get(position).getData().substring(0, 4) +" "+ monthArray[Integer.valueOf(temp.substring(5,7))] + " " + temp.substring(8,11));

             if(messages.get(position).getSender().equals(MainActivity.MY_NAME)) {
              holder.rightMessage.setText(messages.get(position).getMessage());
              holder.rightData.setText(messages.get(position).getData().substring(10, 16));
            }
           else {

              holder.message.setText(messages.get(position).getMessage());
              holder.data.setText(messages.get(position).getData().substring(10,16));
             }
        }
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) return TYPE_MESSAGE_DATA;


        if (messages.get(position).getData().substring(0, 10).equals(messages.get(position-1).getData().substring(0, 10))) {

            if(messages.get(position).getSender().equals(MainActivity.MY_NAME)) {
                return TYPE_MESSAGE_RIGHT;
            }
            else {
                return TYPE_MESSAGE_LEFT;
            }
        }

        return TYPE_MESSAGE_DATA;
    }

       @Override
       public int getItemCount() {
           return messages.size();
       }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView message,data, centralData, rightMessage, rightData;

        ViewHolder(View view) {
              super(view);
                message = (TextView) view.findViewById(R.id.message);
                data = (TextView) view.findViewById(R.id.data);
                centralData = (TextView)view.findViewById(R.id.centralData);
                rightMessage =  (TextView)view.findViewById(R.id.rightMessage);
                rightData =  (TextView)view.findViewById(R.id.rightData);

    }
}
}

