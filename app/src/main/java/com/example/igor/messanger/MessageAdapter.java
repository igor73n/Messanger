package com.example.igor.messanger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

 class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<ListItem> messages = new ArrayList<>();
   // private final int TYPE_LEFT_MESSAGE = 0;
   // private final int TYPE_RIGHT_MESSAGE = 1;
   /// private final int TYPE_MESSAGE_DATE = 2;
    View view;
    Integer i = 0;
    String currentData = "";
    String [] monthArray = {"Zero","January", "February", "Marth", "April", "May", "June", "Jule", "August", "September", "October", "November", "December"};
    String temp;

    public MessageAdapter(Context context, List<ListItem> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {

            case ListItem.TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data, parent, false);
                break;

            case ListItem.TYPE_LEFT_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_message, parent, false);
                break;

            case ListItem.TYPE_RIGHT_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_message, parent, false);

        }

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {

        int type = getItemViewType(position);


        switch (type) {

            case ListItem.TYPE_LEFT_MESSAGE:
               // holder.data.setText(messages.get(position).getData().substring(10, 16));
                LeftMessage leftMessage = (LeftMessage) messages.get(position);
               holder.message.setText(leftMessage.getMessage().getMessage());
                break;

            case ListItem.TYPE_RIGHT_MESSAGE:
                RightMessage rightMessage = (RightMessage) messages.get(position);
                holder.message.setText(rightMessage.getMessage().getMessage());
                break;

            case ListItem.TYPE_DATE:

                Date date = (Date) messages.get(position);
                holder.data.setText(date.getDate());

                //holder.data.setText(messages..getDate().substring(10, 16));


               // holder.centralData.setText(messages.get(position).getData().substring(0, 4) +" "+ monthArray[Integer.valueOf(temp.substring(5,7))] + " " + temp.substring(8,11));


                }
        }


    @Override
    public int getItemViewType(int position) {

        return messages.get(position).getType();

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView message,data;

        ViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.message);
            data = (TextView) view.findViewById(R.id.data);


        }
    }
}