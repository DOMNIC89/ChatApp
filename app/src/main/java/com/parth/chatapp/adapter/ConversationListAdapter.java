package com.parth.chatapp.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.R;
import com.parth.chatapp.adapter.viewholder.CounterPartViewHolder;
import com.parth.chatapp.adapter.viewholder.SelfViewHolder;
import com.parth.chatapp.model.Chat;
import java.util.List;

public class ConversationListAdapter extends RecyclerView.Adapter {

    private static final int SELF = 0;
    private static final int COUNTERPART = 1;

    SortedList.Callback<Chat> callback = new SortedListAdapterCallback<Chat>(this) {

        @Override
        public int compare(Chat o1, Chat o2) {
            if (o1.getTimestamp() > o2.getTimestamp()) {
                return 1;
            } else if (o1.getTimestamp() < o2.getTimestamp()) {
                return -1;
            }
            return 0;
        }

        @Override
        public boolean areContentsTheSame(Chat oldItem, Chat newItem) {
            return oldItem.getMessage().equals(newItem.getMessage());
        }

        @Override
        public boolean areItemsTheSame(Chat item1, Chat item2) {
            return item1.getTimestamp() == item2.getTimestamp() && item1.getUserName().equals(item2.getUserName());
        }
    };

    private SortedList<Chat> lists;

    public ConversationListAdapter(List<Chat> lists) {
        this.lists = new SortedList<>(Chat.class, callback);
        this.lists.addAll(lists);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SELF) {
            return new SelfViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_self_view, null));
        }
        return new CounterPartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_counterpart_view, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Chat chat = lists.get(position);
        if (holder instanceof SelfViewHolder) {
            ((SelfViewHolder) holder).setMessage(chat);
        } else {
            ((CounterPartViewHolder) holder).setMessage(chat);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = lists.get(position);
        if (chat.getUserName().equals(AppSingleton.INSTANCE.getLoggedInUserName())) {
            return SELF;
        }
        return COUNTERPART;
    }

    public void addChat(Chat chat) {
        lists.add(chat);
        notifyItemInserted(lists.size());
    }

    public void updateChat(Chat chat) {
        int position = lists.indexOf(chat);
        if (position != -1) {
            lists.updateItemAt(position, chat);
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }
}
