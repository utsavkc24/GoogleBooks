package com.example.googlebook;

public class Book {
    private String mTitle;
    private String mAuthor;
    //private String mPublisher;
    private Double mAmount;
    private String mCurrency;
    private String mImageUrl;
    private String mBookUrl;
    public Book(String mTitle, String mAuthor, double mAmount, String mCurrency,String mImageUrl,String mBookUrl){
        this.mTitle = mTitle;
        this.mAuthor =  mAuthor;
        this.mAmount = mAmount;
        this.mCurrency = mCurrency;
        this.mImageUrl = mImageUrl;
        this.mBookUrl = mBookUrl;
    }
    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public Double getmAmount() {
        return mAmount;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmBookUrl() {
        return mBookUrl;
    }
}
