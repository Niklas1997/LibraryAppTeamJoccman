package com.example.ezmilja.libraryapp;

/**
 * Created by ezmilja on 18/07/2017.
 */

public class BookRequest {


    private final String bookName;

    private final String author;

    private final String vote;

    private boolean isUpVoted;

    public BookRequest( final String bookName, final String author,
                final String vote){

        this.bookName =bookName;
        this.author = author;
        this.vote = vote;
        this.isUpVoted = false;
    }


    public String getBookName(){ return bookName;}

    public String getAuthor(){ return author;}

    public String getVote(){return  vote;}

    public boolean getisUpVoted(){return isUpVoted;}

    public void setisUpVoted(){isUpVoted = true;}

}

