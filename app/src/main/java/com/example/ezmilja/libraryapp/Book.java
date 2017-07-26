package com.example.ezmilja.libraryapp;

/**
 * Created by emirmun on 13/07/2017.
 */

public class Book {


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

    public Book(String imageId, final String isbn, final String bookName, final String author, final String description,
                final String page, final String publisher, float rating,
                int numberOfCopys, final int MAX_COPYS , int num_rating){

       this.imageId = imageId;
        this.isbn = isbn;
        this.bookName =bookName;
        this.author = author;
        this.description = description;
        this.page = page;
        this.publisher = publisher;
        this.rating = rating;
        this.MAX_COPYS = MAX_COPYS;
        this.num_rating = num_rating;
        isRated = false;
        this.numberOfCopys = numberOfCopys;
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

    public  String getImageId(){return  imageId;}
    public String getIsbn() {return isbn;}
    public String getBookName(){ return bookName;}
    public String getAuthor(){ return author;}
    public String getDescription(){return  description;}
    public String getPage(){return  page;}
    public String getPublisher(){return  publisher;}
    public float getRating() {return rating;}
    public boolean getIsRated(){return isRated;}


    public void addRating(double addRating){
        isRated = true;
        num_rating += 1;
        rating += addRating;
    }

    public int getNumberOfCopys(){return numberOfCopys;}

    public int getMAX_COPYS(){return MAX_COPYS;}

    public int getNum_rating(){return num_rating;}

    public void addToNumberOfCopys(int added){
        numberOfCopys = numberOfCopys + added;
    }

    @Override
    public boolean equals(final Object obj) {
        final Book other = (Book) obj;
        return other.bookName.equals(this.bookName);
    }


}
