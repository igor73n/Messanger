package com.example.igor.messanger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class ChatItemAdapter extends ArrayAdapter<ChatItem> {

    private  List<ChatItem> chatItems;
    private LayoutInflater inflater;
    private int layout;

    public ChatItemAdapter(Context context, int resource, List<ChatItem> chatItems ) {
        super(context, resource, chatItems);
        this.chatItems = chatItems;
        this.layout = resource;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChatItem chatItem = chatItems.get(position);

        viewHolder.collocuter.setText(chatItem.getCollocuter());
        viewHolder.lastMessage.setText(chatItem.getLastMessage());
        viewHolder.data.setText(chatItem.getData());

        return convertView;
    }

    private class ViewHolder {

        final TextView collocuter, lastMessage, data;

        private ViewHolder(View view) {
            collocuter = (TextView)view.findViewById(R.id.collocuter);
            lastMessage = (TextView)view.findViewById(R.id.lastMessage);
            data = (TextView)view.findViewById(R.id.data);

        }

    }

}
