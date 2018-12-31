package com.example.igor.messanger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<ChatItem> chatItems = new ArrayList<>();
    private int layout;
    Context myContext;
    View view;

     ChatItemAdapter(Context context, List<ChatItem> chatItems ) {
        this.chatItems = chatItems;
        inflater = LayoutInflater.from(context);
        this.myContext = context;
    }


    @Override
    public ChatItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ChatItemAdapter.ViewHolder holder, final int position) {

       ChatItem chatItem = chatItems.get(position);
       holder.collocuter.setText(chatItem.getCollocuter());
       holder.lastMessage.setText(chatItem.getLastMessage());
       holder.data.setText(chatItem.getData());
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView collocuter, data, lastMessage;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            collocuter = (TextView) view.findViewById(R.id.collocuter);
            data = (TextView) view.findViewById(R.id.data);
            lastMessage = (TextView) view.findViewById(R.id.lastMessage);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            itemClick(position);

        }
    }

    private void itemClick(int position){

        Intent intent = new Intent(myContext,ChatActivity.class);
        intent.putExtra(MainActivity.INTENT_MESSAGE, chatItems.get(position).getCollocuter());
        intent.putExtra(MainActivity.INTENT_MESSAGE_ID,chatItems.get(position).getId());
        myContext.startActivity(intent);

    }


}
