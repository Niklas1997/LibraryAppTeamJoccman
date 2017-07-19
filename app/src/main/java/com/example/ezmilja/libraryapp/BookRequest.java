package com.example.ezmilja.libraryapp;

/**
 * Created by ezmilja on 18/07/2017.
 */

public class BookRequest {


    private final String bookName;

    private final String author;

    private int vote;

    private boolean isUpVoted;

    public BookRequest( final String bookName, final String author,
                final int vote){

        this.bookName =bookName;
        this.author = author;
        this.vote = vote;
        this.isUpVoted = false;
    }


    public String getBookName(){ return bookName;}

    public String getAuthor(){ return author;}

    public int getVote(){return vote;}

    public void addVote(int add){vote += add;}

    public boolean getisUpVoted(){return isUpVoted;}

    public void setisUpVoted(boolean isUpVoted){this.isUpVoted = isUpVoted;}

}

