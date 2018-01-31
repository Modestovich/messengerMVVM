package com.mody.messagermvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mody.messagermvvm.adapter.MessagesAdapter;
import com.mody.messagermvvm.model.Message;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MessengerViewModel extends RecyclerViewModel {

    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = MessengerViewModel.class.getSimpleName();
    private Message mOutgoingMessage = new Message();
    //private MutableLiveData<List<Message>> mMessages;
    private List<Message> mMessages;
    private DatabaseReference mDatabaseReference;
    private MessagesAdapter mAdapter;

    public MessengerViewModel() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAdapter = new MessagesAdapter(getMessages());
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mLayoutManager = layoutManager;
    }

    @Override
    RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    RecyclerView.LayoutManager createLayoutManager() {
        return mLayoutManager;
    }

   /* public LiveData<List<Message>> getMessages() {
        if (mMessages == null) {
            mMessages = new MutableLiveData<>();
            mMessages.setValue(new ArrayList<Message>());
            loadMessages();
        }
        return mMessages;
    }*/

    public List<Message> getMessages() {
        if (mMessages == null) {
            mMessages = new ArrayList<>();
            loadMessages();
        }
        return mMessages;
    }

    private void loadMessages(){
        mDatabaseReference.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildAdded, DataSnapshot[" + dataSnapshot + "], s[" + s + "]");
                Message message = dataSnapshot.getValue(Message.class);
                mMessages.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Message getOutgoingMessage(){
        return mOutgoingMessage;
    }

    public void onSendClicked(View view){
        Log.i(TAG, "onSendClicked, outgoing message: " + mOutgoingMessage);
        if(!isValid(mOutgoingMessage)){
            Toast.makeText(view.getContext(), "Message and username cannot be empty", LENGTH_LONG).show();
            return;
        }
        mDatabaseReference.child("messages").push().setValue(mOutgoingMessage);
    }

    private boolean isValid(Message outgoingMessage) {
        return !TextUtils.isEmpty(outgoingMessage.getMessageContent()) && !TextUtils.isEmpty(outgoingMessage.getUserName());
    }

}