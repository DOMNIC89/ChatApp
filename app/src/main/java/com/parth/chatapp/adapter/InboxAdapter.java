package com.parth.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.parth.chatapp.R;
import com.parth.chatapp.model.Chat;
import com.parth.chatapp.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.UserViewHolder> {

    private List<String> usersList;
    private List<Chat> userChatList = new ArrayList<>();
    private InboxItemClickListener clickListener;

    public InboxAdapter(Context context, List<String> usersList, InboxItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.usersList = usersList;
        for (String userName :
            this.usersList) {
            userChatList.add(Chat.getLastChat(context, userName));
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, null));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        Chat chat = userChatList.get(position);
        holder.tv_message.setText(chat.getMessage());
        holder.tv_timestamp.setText(Utils.getTimestampText(chat.getTimestamp()));
        holder.tv_userName.setText(usersList.get(position));
        holder.rl_parentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClicked(usersList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tv_userName;
        RelativeLayout rl_parentContainer;
        TextView tv_message;
        TextView tv_timestamp;

        UserViewHolder(View itemView) {
            super(itemView);
            tv_userName = (TextView) itemView.findViewById(R.id.user_name_inbox);
            tv_message = (TextView) itemView.findViewById(R.id.message_inbox);
            tv_timestamp =(TextView) itemView.findViewById(R.id.timestamp_inbox);
            rl_parentContainer = (RelativeLayout) itemView.findViewById(R.id.inbox_item_container);
        }
    }

    public interface InboxItemClickListener {
        void onItemClicked(String userName);
    }
}
