package com.example.ezmilja.libraryapp;

/**
 * Created by ezmilja on 18/07/2017.
 */

public class RequestBook {


    private final String bookName;

    private final String author;

    private int vote;

    private boolean isUpVoted;

    private final String email;

    public RequestBook(final String bookName, final String author, final String email,
                       final int vote){

        this.bookName =bookName;
        this.author = author;
        this.email = email;
        this.vote = vote;
        this.isUpVoted = false;
    }


    public String getBookName(){ return bookName;}

    public String getAuthor(){ return author;}

    public String getEmail(){return email;}

    public int getVote(){return vote;}

    public void addVote(int add){vote += add;}

    public boolean getisUpVoted(){return isUpVoted;}

    public void setisUpVoted(boolean isUpVoted){this.isUpVoted = isUpVoted;}

}

