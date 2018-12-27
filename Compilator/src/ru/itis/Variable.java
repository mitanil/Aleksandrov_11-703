package ru.itis;

public class Variable{
    String name;
    int value;
    Variable(String name, int value){
        this.name = name;
        this.value = value;
    }
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Variable))
            return false;
        if(o == this)
            return true;
        Variable t = (Variable)o;
        return this.name.equals(t.name) && this.value == t.value;
    }
}
