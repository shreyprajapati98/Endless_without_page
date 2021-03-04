package com.demo.endless_without_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Message> messages;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new MessageVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Message message = messages.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                MessageVH messageVH = (MessageVH) holder;
                messageVH.id.setText(message.getId());
                messageVH.textView.setText(message.getTitle());
                messageVH.body.setText(message.getBody());
                break;
            case LOADING:

                break;
        }

    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == messages.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }



    public void add(Message mc) {
        messages.add(mc);
        notifyItemInserted(messages.size() - 1);
    }

    public void addAll(List<Message> mcList) {
        for (Message mc : mcList) {
            add(mc);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Message());

    }

    public void remove(Message mc) {
        int position = messages.indexOf(mc);
        if (position > -1) {
            messages.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Message getItem(int position) {
        return messages.get(position);
    }



    protected class MessageVH extends RecyclerView.ViewHolder {
        private TextView textView,id,body;

        public MessageVH(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            body = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}

