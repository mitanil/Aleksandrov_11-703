package ru.itis;

import java.security.cert.X509Certificate;

/**
 * Created by Admon on 08.02.2018.
 */
public class Hit {
    private int corX;
    private int corY;

    public Hit(int x, int y) {
        corX = x;
        corY = y;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public int getRadius()
    {
        return (int) Math.sqrt(corX * corX + corY * corY);
    }
}
