package com.parth.chatapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.parth.chatapp.R;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.UserViewHolder> {

    private List<String> usersList;
    private InboxItemClickListener clickListener;

    public InboxAdapter(List<String> usersList, InboxItemClickListener clickListener) {
        this.usersList = usersList;
        this.clickListener = clickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, null));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        holder.tv_userName.setText(usersList.get(position));
        holder.tv_userName.setOnClickListener(new View.OnClickListener() {
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

        UserViewHolder(View itemView) {
            super(itemView);
            tv_userName = (TextView) itemView.findViewById(R.id.user_name_inbox);
        }
    }

    public interface InboxItemClickListener {
        void onItemClicked(String userName);
    }
}
