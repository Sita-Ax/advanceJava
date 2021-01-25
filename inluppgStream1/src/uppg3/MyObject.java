package uppg3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyObject {

    boolean bool;
    int value;
    String name;

    public MyObject(boolean bool, int value, String name) {
        this.bool = bool;
        this.value = value;
        this.name = name;
    }

    public String toString(){
        return bool + " " + value + " " + name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

            public boolean isBool() {
                return bool;
            }

            public void setBool(boolean bool) {
                this.bool = bool;
            }

    public static void main(String[] args) {

        String[] nameList = {"Agge", "Aida", "Adele", "Agda", "Agneta", "Aina", "Albin", "Alexa", "Alexis", "Alex", "Belle", "Benita", "Bettan", "Bettina", "Birgit", "Blenda", "Bodil", "Bonnie", "Brandie", "Britt", "Christer", "Carla", "Casper", "Cloetta", "Celia", "Cleo", "Colette", "Connie", "Christian", "Colin", "Susanne", "Sofie", "Sune", "Svea", "Sven", "Sonja", "Ramona", "Ronja", "Rebecka", "Rosita", "Rikard", "Rune", "Robert", "Robin", "Ola", "Filip", "Frida", "Olga", "Per", "Agust"};
        boolean bool = true;
        List<MyObject> list = new ArrayList<>(); //create my list of typ myobject

        for (int i = 0; i < 50; i++) {
            int names = (int) (Math.random() * 49) + 1;
            int values = (int) (Math.random() * 50) + 1;
            int bools = (int) (Math.random() * 2) + 1;
            if (bools == 2) {
                bool = true;
            } else {
                bool = false;
            }
            MyObject myObj1 = new MyObject(bool, values, nameList[names]);
            list.add(myObj1);                      //put in my objects in my list inside the loop 50 times
        }

        Object [] newString =  list
                .stream()
                .filter(s -> s.getValue() > 20)
                .toArray();
        System.out.println("1: " + Arrays.toString(newString));

        double obj = list.stream().mapToDouble(s-> s.getValue()).average().getAsDouble();
        System.out.println("2: " + obj);

        Arrays.toString(list
                .stream()
                .filter(s -> s.isBool())
                .map(s -> s.name = "this is true")
                .toArray());
        System.out.println("3: " + list);

    }

}
