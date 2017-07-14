package com.example.ezmilja.libraryapp;

/**
 * Created by elundni on 13/07/2017.
 */
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class TextFileReader {

    private String file_path;

    public TextFileReader(String file_path){

        this.file_path = file_path;

    }

    public String[] getBook(String book_name, String book_author){
        try {
            FileReader fileReader = new FileReader(file_path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] temp = new String[1];
            temp[0] = "Error no book found";

            int num_boooks = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < num_boooks; i++){
                String temp_name = bufferedReader.readLine();
                String temp_author = bufferedReader.readLine();

                int descript_length = Integer.parseInt(bufferedReader.readLine());
                if (book_name.equals(temp_name) && book_author.equals(temp_author)){
                    temp = new String[descript_length];
                    for (int j = 0; j < temp.length; j++ ){
                        temp[j] = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                    fileReader.close();
                    return temp;
                }
                else {
                    for (int j = 0; j < descript_length + 1; j++ )
                        bufferedReader.readLine();
                }
            }
            bufferedReader.close();
            fileReader.close();
            return temp;
        }
        catch (IOException e){
            String[] temp = new String[1];
            temp[0] = "Error";
            return temp;
        }
    }
}
