package com.example.ezmilja.libraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

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
        String mDrawableName = book.getImageId();
        int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
        final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(book.getBookName())
                .setMessage(book.getAuthor()).setIcon(resID).setNeutralButton("Close", null)
                .setPositiveButton("Read More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, nextActivity);
                        intent.putExtra("id", id+"");
                        context.startActivity(intent);
                    }
                }).setNegativeButton("Check OUT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, CheckoutActivity.class);
                        intent.putExtra("isbn", book.getIsbn());
                        context.startActivity(intent);
                    }
                }).show();
    }
}
