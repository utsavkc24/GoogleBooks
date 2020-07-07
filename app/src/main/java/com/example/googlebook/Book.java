package com.example.googlebook;

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mPublisher;

    public Book(String mTitle,String mAuthor,String mPublisher){
        this.mTitle = mTitle;
        this.mAuthor =  mAuthor;
        this.mPublisher = mPublisher;
    }
    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }
}
