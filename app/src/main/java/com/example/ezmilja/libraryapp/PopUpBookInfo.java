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


    public PopUpBookInfo(Book book){
        this.book = book;

    }

    public void createPopUp(final Context context, final Class nextActivity , final String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(book.getName() + '\n' + book.getDescription())
                .setMessage(message).setIcon(book.getImageId()).setNeutralButton("Close", null)
                .setPositiveButton("More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, nextActivity);
                        intent.putExtra("bookName", book.getName());
                        intent.putExtra("author", book.getDescription());
                        intent.putExtra("message", message);
                        context.startActivity(intent);
                    }
                }).setNegativeButton("Book", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, book.getName() + '\n' + "By " + book.getDescription() + " is now booked", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
