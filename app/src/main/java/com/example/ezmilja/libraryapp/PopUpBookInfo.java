package com.example.ezmilja.libraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by elundni on 13/07/2017.
 */

public class PopUpBookInfo {
    private final Book book;
    private final int id;

    public PopUpBookInfo(final int id, Book book){
        this.id= id;
        this.book = book;
    }

    public void createPopUp(final Context context, final Class nextActivity , final String message){
        System.out.println(book);
        final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(book.getBookName() + '\n' + book.getAuthor())
                .setMessage(message).setIcon(book.getImageId()).setNeutralButton("Close", null)
                .setPositiveButton("More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, nextActivity);
                        intent.putExtra("id", id+"");
                        context.startActivity(intent);
                    }
                }).setNegativeButton("Book", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, book.getBookName() + '\n' + "By " + book.getAuthor() + " is now booked", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
