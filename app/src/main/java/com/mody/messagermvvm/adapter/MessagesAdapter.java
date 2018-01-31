package com.mody.messagermvvm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mody.messagermvvm.R;
import com.mody.messagermvvm.databinding.MessageLayoutBinding;
import com.mody.messagermvvm.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<Message> mMessages = new ArrayList<>();

    public MessagesAdapter(List<Message> messages) {
        mMessages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_layout, parent, false);

        MessageLayoutBinding binding = MessageLayoutBinding.bind(itemView);
        return new MessageViewHolder(itemView, binding);
    }

    public void setItems(List<Message> newItems) {
        mMessages.clear();
        mMessages.addAll(newItems);
        notifyDataSetChanged();
    }

    public List<Message> getItems() {
        return mMessages;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.setItem(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private MessageLayoutBinding mBinding;

        public MessageViewHolder(View itemView, MessageLayoutBinding binding) {
            super(itemView);
            mBinding = binding;
        }

        public void setItem(Message message){
            mBinding.setMessage(message);
            mBinding.executePendingBindings();
        }
    }
}