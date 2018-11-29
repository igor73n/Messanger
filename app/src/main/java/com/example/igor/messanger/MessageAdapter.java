package com.example.igor.messanger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    private List<Message> messages;
    private LayoutInflater inflater;
    private int layout;

    public MessageAdapter(Context context, int resource, List<Message> messages ) {
        super(context, resource, messages);
        this.messages = messages;
        this.layout = resource;
        inflater = LayoutInflater.from(context);
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        MessageAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new MessageAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (MessageAdapter.ViewHolder) convertView.getTag();
        }

        Message message = messages.get(position);

        if(message.getSender().equals(MainActivity.MY_NAME)) {
            viewHolder.rightMessage.setText(message.getMessage());
            viewHolder.rightData.setText(message.getData());

        }
        else {
            viewHolder.leftMessage.setText(message.getMessage());
            viewHolder.leftData.setText(message.getData());
        }

        return convertView;
    }

    private class ViewHolder {

        final TextView leftMessage, rightMessage, leftData, rightData;

        private ViewHolder(View view) {

                leftMessage = (TextView) view.findViewById(R.id.leftMessage);
                rightMessage = (TextView) view.findViewById(R.id.rightMessage);
                leftData = (TextView) view.findViewById(R.id.leftData);
                rightData =(TextView) view.findViewById(R.id.rightData);

        }
    }
}

