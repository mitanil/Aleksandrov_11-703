package ru.itis.rational_fraction;

/**
 * @author Aleksandrov Anrey
 *         11-703
 *         for 050
 */
public class RationalFraction {
    private int a;
    private int b;

    public RationalFraction() {
        a = 0;
        b = 1;
    }

    public RationalFraction(int a, int b){
        this.a = a;
        if (b != 0) {
            this.b = b;
        } else {
            b = 1;
        }
    }

    public void reduce(){
        int t;
        int max = 1;
        if(a == b){
            a = 1;
            b = 1;
            return;
        }
        if (a > b) {
            t = b;
        }else
            t = a;
        for(int i = 1; i <= t; i++){
            if(a%i == 0 && b%i == 0)
                max = i;
        }
        a /= max;
        b /= max;
    }

    public RationalFraction add(RationalFraction rf){
        RationalFraction t;
        if(this.b == rf.b){
            t = new RationalFraction(this.a + rf.a, this.b);
        }else {
            t = new RationalFraction(this.a * rf.b + rf.a * this.b, this.b * rf.b);
        }
        t.reduce();
        return t;
    }

    public void add2(RationalFraction rf){
        if (this.b == rf.b) {
            this.a += rf.a;
        } else {
            this.a = this.a * rf.b + rf.a * this.b;
            this.b *= rf.b;
        }
        reduce();
    }
    public RationalFraction sub(RationalFraction rf){
        RationalFraction t;
        if(this.b == rf.b){
            t = new RationalFraction(this.a - rf.a, this.b);
        }else {
            t = new RationalFraction(this.a * rf.b - rf.a * this.b, this.b * rf.b);
        }
        t.reduce();
        return t;
    }

    public void sub2(RationalFraction rf){
        if (this.b == rf.b) {
            this.a -= rf.a;
        } else {
            this.a = this.a * rf.b - rf.a * this.b;
            this.b *= rf.b;
        }
        reduce();
    }

    public RationalFraction mult(RationalFraction rf){
        return new RationalFraction(this.a * rf.a, this.b * rf.b);
    }

    public void mult2(RationalFraction rf){
        this.a *= rf.a;
        this.b *= rf.b;
    }
    public RationalFraction div(RationalFraction rf){
        return new RationalFraction(this.a * rf.b, this.b * rf.a);
    }

    public void div2(RationalFraction rf){
        this.a *= rf.b;
        this.b *= rf.a;
    }

    public String toString(){
        return "" + a + " / " + b;
    }

    public double value(){
        return (double)a/ b;
    }

    public boolean equals(RationalFraction rf){
        boolean isEquals = false;
        RationalFraction t1 = new RationalFraction(a, b);
        RationalFraction t2 = new RationalFraction(rf.a, rf.b);
        t1.reduce();
        t2.reduce();
        if(t1.a == t2.a && t1.b == t2.b)
            isEquals = true;
        return isEquals;
    }

    public int numberPart(){
        return (int)((double) a / b);
    }
}
