package com.parth.chatapp.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.parth.chatapp.R;
import com.parth.chatapp.model.Chat;
import com.parth.chatapp.utils.Utils;

public class SelfViewHolder extends RecyclerView.ViewHolder {
    CardView cv_parentContainer;
    TextView tv_message;
    TextView tv_timestamp;

    public SelfViewHolder(View itemView) {
        super(itemView);
        cv_parentContainer = (CardView) itemView.findViewById(R.id.parent_container);
        tv_message = (TextView) itemView.findViewById(R.id.self_message);
        tv_timestamp = (TextView) itemView.findViewById(R.id.timestamp);
    }

    public void setMessage(Chat chat) {
        tv_message.setText(chat.getMessage());
        tv_timestamp.setText(Utils.getTimestampText(chat.getTimestamp()));
    }
}
