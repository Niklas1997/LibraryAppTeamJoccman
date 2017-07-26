package com.example.ezmilja.libraryapp;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by emirmun on 13/07/2017.
 */

public class Book implements Comparable<Book>, Serializable {

    private int id;
    private final String imageId;
    private final String isbn;
    private final String bookName;
    private final String author;
    private final String description;
    private final String page;
    private final String publisher;
    private float rating;
    private int num_rating;
    private boolean isRated;
    private int numberOfCopys;
    private final int MAX_COPYS;

    public Book(int id, String imageId, final String isbn, final String bookName, final String author, final String description,
                final String page, final String publisher, float rating, int num_rating, boolean isRated,
                int numberOfCopys, final int MAX_COPYS ){

        this.id = id;
        this.imageId = imageId;
        this.isbn = isbn;
        this.bookName =bookName;
        this.author = author;
        this.description = description;
        this.page = page;
        this.publisher = publisher;
        this.rating = rating;
        this.num_rating = num_rating;
        isRated = false;
        this.numberOfCopys = numberOfCopys;
        this.MAX_COPYS = MAX_COPYS;

    }


 @Override
 public String toString() {
  return "Book{" +
          "isbn='" + isbn + '\'' +
          ", bookName='" + bookName + '\'' +
          ", author='" + author + '\'' +
          ", description='" + description + '\'' +
          ", page='" + page + '\'' +
          ", publisher='" + publisher + '\'' +
          '}';
 }
    public int getId() {
        return id;
    }
    public  String getImageId(){return  imageId;}
    public String getIsbn() {return isbn;}
    public String getBookName(){ return bookName;}
    public String getAuthor(){ return author;}
    public String getDescription(){return  description;}
    public String getPage(){return  page;}
    public String getPublisher(){return  publisher;}
    public float getRating() {return rating;}
    public int getNum_rating() {return num_rating;}
    public boolean getIsRated(){return isRated;}

    public void addRating(float addRating){
        isRated = true;
        num_rating += 1.0;
        rating += addRating;
    }

    public int getNumberOfCopys(){return numberOfCopys;}

    public int getMAX_COPYS(){return MAX_COPYS;}



    public void addToNumberOfCopys(int added){
        numberOfCopys = numberOfCopys + added;
    }

//Need to have a look
    @Override
    public int compareTo(@NonNull Book book) {
        return 0;
    }
}
