package com.example.ezmilja.libraryapp;

/**
 * Created by emirmun on 13/07/2017.
 */

public class Book {

    private final String name;

    private final int imageId;

    private final String description;

    public Book(final String name, final int imageId, final String decription){
        this.name =name;
        this.imageId = imageId;
        this.description = decription;
    }

    public String getName(){ return name;}

    public int getImageId(){return  imageId;}

    public String getDescription(){return  description;}

    @Override
    public boolean equals(final Object obj) {
        final Book other = (Book) obj;
        return other.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + imageId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
