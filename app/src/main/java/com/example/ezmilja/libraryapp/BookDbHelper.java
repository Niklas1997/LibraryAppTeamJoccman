package com.example.ezmilja.libraryapp;

/**
 * Created by elundni on 21/07/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class BookDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Books.db";
    public static final String TABLE_NAME = "BOOKS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AUTHOR";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "IMAGEID";
    public static final String COL_6 = "ISBN";
    public static final String COL_7 = "PAGE";
    public static final String COL_8 = "PUBLISHER";
    public static final String COL_9 = "RATING";
    public static final String COL_10 = "NUMBEROFCOPYS";
    public static final String COL_11 = "NUMRATING";
    public static final String COL_12 = "MAXCOPYS";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


        BookCache bookCache = BookCache.CACHE;
        Cursor cursor = this.getAllData();
        if (cursor.getCount() == 0){
            for (int i = 0; i < bookCache.getNumberOfBooks(); i++){
                this.insertData(bookCache.getBook(i));
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,AUTHOR TEXT,DESCRIPTION TEXT,IMAGEID INTEGER,ISBN INTEGER,PAGE TEXT,PUBLISHER TEXT," +
                "RATING DOUBLE,NUMBEROFCOPYS INTEGER,NUMRATING INTEGER,MAXCOPYS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String author, String description, String image_id,
                              String isbn, String page, String publisher, String rating, String numberOfCopys,
                              String max_copys, String num_rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, author);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, image_id);
        contentValues.put(COL_6, isbn);
        contentValues.put(COL_7, page);
        contentValues.put(COL_8, publisher);
        contentValues.put(COL_9, rating);
        contentValues.put(COL_10, numberOfCopys);
        contentValues.put(COL_11, num_rating);
        contentValues.put(COL_12, max_copys);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, book.getBookName());
        contentValues.put(COL_3, book.getAuthor());
        contentValues.put(COL_4, book.getDescription());
        contentValues.put(COL_5, book.getImageId());
        contentValues.put(COL_6, book.getIsbn());
        contentValues.put(COL_7, book.getPage());
        contentValues.put(COL_8, book.getPublisher());
        contentValues.put(COL_9, book.getRating());
        contentValues.put(COL_10, book.getNumberOfCopys());
        contentValues.put(COL_11, book.getNum_rating());
        contentValues.put(COL_12, book.getMAX_COPYS());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id, String name, String author, String description, String image_id,
                              String isbn, String page, String publisher, String rating, String numberOfCopys,
                              String max_copys, String num_rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, author);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, image_id);
        contentValues.put(COL_6, isbn);
        contentValues.put(COL_7, page);
        contentValues.put(COL_8, publisher);
        contentValues.put(COL_9, rating);
        contentValues.put(COL_10, numberOfCopys);
        contentValues.put(COL_11, num_rating);
        contentValues.put(COL_12, max_copys);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public boolean updateData(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, book.getBookName());
        contentValues.put(COL_3, book.getAuthor());
        contentValues.put(COL_4, book.getDescription());
        contentValues.put(COL_5, book.getImageId());
        contentValues.put(COL_6, book.getIsbn());
        contentValues.put(COL_7, book.getPage());
        contentValues.put(COL_8, book.getPublisher());
        contentValues.put(COL_9, book.getRating());
        contentValues.put(COL_10, book.getNumberOfCopys());
        contentValues.put(COL_11, book.getNum_rating());
        contentValues.put(COL_12, book.getMAX_COPYS());
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{book.getID() + ""});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public List<Book> getAllBooks(){
        List<Book> list = new ArrayList<Book>();
        Cursor cursor = this.getAllData();
        while (cursor.moveToNext()) {
            int t_id = Integer.parseInt(cursor.getString(0));
            String t_name = cursor.getString(1);
            String t_author = cursor.getString(2);
            String t_description = cursor.getString(3);
            int t_imageid = Integer.parseInt(cursor.getString(4));
            String t_isbn = cursor.getString(5);
            String t_page = cursor.getString(6);
            String t_publisher = cursor.getString(7);
            double t_rating = Double.parseDouble(cursor.getString(8));
            int t_numberOfCopys = Integer.parseInt(cursor.getString(9));
            int t_numRating = Integer.parseInt(cursor.getString(10));
            int t_maxCopys = Integer.parseInt(cursor.getString(11));
            list.add( new Book(t_id, t_isbn, t_name, t_imageid, t_author, t_description,
                    t_page, t_publisher, t_rating, t_numberOfCopys, t_maxCopys, t_numRating));
        }
        return list;
    }

    public Book getBook(int ID){
        List<Book> list = this.getAllBooks();
        for (Book b: list){
            if (b.getID() == ID){
                return b;
            }
        }
        return null;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Integer deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getAllData();
        for (int i = 0; i < cursor.getCount(); i++) {
            deleteData(i + "");
        }
        return 0;
    }
}