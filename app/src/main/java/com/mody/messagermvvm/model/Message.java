package com.mody.messagermvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.firebase.database.IgnoreExtraProperties;
import com.mody.messagermvvm.BR;

@IgnoreExtraProperties
public class Message extends BaseObservable{

    private String mUserName;
    private String mMessageContent;
    private boolean mWasChanged;

    public Message() {
    }

    public Message(String userName, String messageContent) {
        mUserName = userName;
        mMessageContent = messageContent;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    @Bindable
    public String getMessageContent() {
        return mMessageContent;
    }

    public Message(String userName, String messageContent, boolean wasChanged) {
        mUserName = userName;
        mMessageContent = messageContent;
        mWasChanged = wasChanged;
    }

    public boolean wasChanged() {
        return mWasChanged;
    }

    public void setWasChanged(boolean wasChanged) {
        mWasChanged = wasChanged;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public void setMessageContent(String messageContent) {
        mMessageContent = messageContent;
        notifyPropertyChanged(BR.messageContent);
    }

    @Override
    public String toString() {
        return "Message{" +
                "mUserName='" + mUserName + '\'' +
                ", mMessageContent='" + mMessageContent + '\'' +
                ", mWasChanged=" + mWasChanged +
                '}';
    }
}
