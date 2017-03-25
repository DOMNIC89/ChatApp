package com.parth.chatapp.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.parth.chatapp.R;
import com.parth.chatapp.model.Chat;
import com.parth.chatapp.utils.Utils;

public class CounterPartViewHolder extends RecyclerView.ViewHolder {

    private CardView cv_parentContainer;
    private TextView tv_message;
    private TextView tv_timestamp;
    private TextView tv_author;

    public CounterPartViewHolder(View itemView) {
        super(itemView);
        cv_parentContainer = (CardView) itemView.findViewById(R.id.parent_container);
        tv_message = (TextView) itemView.findViewById(R.id.counterpart_message);
        tv_timestamp = (TextView) itemView.findViewById(R.id.timestamp);
        tv_author = (TextView) itemView.findViewById(R.id.author_name);
    }

    public void setMessage(Chat chat) {
        tv_message.setText(chat.getMessage());
        tv_timestamp.setText(Utils.getTimestampText(chat.getTimestamp()));
        tv_author.setText(chat.getUserName());
    }
}
