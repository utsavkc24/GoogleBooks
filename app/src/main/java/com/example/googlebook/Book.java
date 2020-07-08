package com.example.googlebook;

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mPublisher;
    private Double mAmount;
    private String mCurrency;
public Book(String mTitle){
    this.mTitle = mTitle;
    }
    public Book(String mTitle, String mAuthor, String mPublisher, double mAmount, String mCurrency){
        this.mTitle = mTitle;
        this.mAuthor =  mAuthor;
        this.mPublisher = mPublisher;
        this.mAmount = mAmount;
        this.mCurrency = mCurrency;
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

    public Double getmAmount() {
        return mAmount;
    }

    public String getmCurrency() {
        return mCurrency;
    }
}
