package ru.itis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 19.04.2018
 * Competition
 *
 * @author Aleksandrov Andrey
 */
public class Scanner {

    private FileInputStream fileInputStream = null;

    public Scanner(String source){
        try {
            fileInputStream = new FileInputStream(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int nextInt() {
        int res = 0;
        int minus = 1;
        boolean isRead = false;
        try {
            int temp = fileInputStream.read();
            while (temp != -1 && (char) temp != ' ') {
                int f = temp - '0';
                if ((char) temp < '0' || (char) temp > '9') {
                    if(temp == '-' && !isRead){
                        minus = -1;
                    }else
                    throw new NumberFormatException();
                } else {
                    res = res * 10 + f;
                    isRead = true;
                }
                temp = fileInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!isRead)
            throw new NumberFormatException();
        res *= minus;
        return res;
    }
}
