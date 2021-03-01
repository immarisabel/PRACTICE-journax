package nl.marisabel.testcodes;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {

GenericExample();
    }


// This gives no errors but it is is wrong as there is a string in the list!
    // Exception in thread "main" java.lang.ClassCastException: class
//  java.lang.String cannot be cast to class java.lang.Integer
    public static void GenericExample () {
        ArrayList a = new ArrayList();
        a.add(15);
        a.add("15");
        Integer b = (Integer)a.get(1);
    }

// This gives an error and it is good! Because we can fix it and see it easier!
    public static <Integer> void GenericExampleGood () {
        ArrayList<Integer> a = new ArrayList< >();
        a.add(15);
        a.add("15");
        Integer b = a.get(1);
    }

}
