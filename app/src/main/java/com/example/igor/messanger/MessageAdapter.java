package com.example.igor.messanger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

 class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<ListItem> messages = new ArrayList<>();
    View view;
    Integer i = 0;
    String [] monthArray = {"Zero","January", "February", "Marth", "April", "May", "June", "Jule", "August", "September", "October", "November", "December"};
    Context mContext;


    public MessageAdapter(Context context, List<ListItem> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
        this.mContext = context;
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
                break;

             case ListItem.TYPE_LOAD_MORE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more, parent, false);
        }

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, final int position) {

        int type = getItemViewType(position);


        switch (type) {

           case ListItem.TYPE_LOAD_MORE:
                LoadMore loadMore = (LoadMore) messages.get(position);
               holder.loadMore.setText(loadMore.getLoadMore());

               holder.loadMore.setOnClickListener (new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       ((ChatActivity)mContext).loadMore();
                   }
               });

               break;

            case ListItem.TYPE_LEFT_MESSAGE:

                LeftMessage leftMessage = (LeftMessage) messages.get(position);
               holder.message.setText(leftMessage.getMessage().getMessage());
                holder.data.setText(leftMessage.getMessage().getData().substring(10,16));
                break;

            case ListItem.TYPE_RIGHT_MESSAGE:
                RightMessage rightMessage = (RightMessage) messages.get(position);
                holder.message.setText(rightMessage.getMessage().getMessage());
                holder.data.setText(rightMessage.getMessage().getData().substring(10,16));
                break;

            case ListItem.TYPE_DATE:

                Date date = (Date) messages.get(position);
                holder.data.setText(date.getDate());
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

        final TextView message,data, loadMore;

        ViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.message);
            data = (TextView) view.findViewById(R.id.data);
            loadMore = (TextView) view.findViewById(R.id.load_more);


        }
    }

}