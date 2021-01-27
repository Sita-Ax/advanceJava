import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int count = 0;
    public static void main(String[] args) throws FileNotFoundException {

        File text = new File("src/RegulExpre.txt"); //create file text and reads in in java
        Scanner scanner = new Scanner(text);                //scanner to read file
        //String myString = " "; //my line
        String pattern = "abcdefghij";        //1:a
        //String pattern = "Ola";               //2:a
        //String pattern = "[aaa]{3,5}";        //3:an
        //String pattern = "[\\W]{2,}";         //4:an
        //String pattern = "[A-Za-z0-9._%-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,3}";  //5:an
        //String pattern = "\\d{2,10}[0-9+\\s-/]+\\d{5,}";         //6:an
        //String pattern = "([a-zA-Z])\\1{3}";//7:an
        //String pattern = "[a-zA-Z]{3}[_/][\\d]{3}";//8:an
        //String pattern = "\\((.*?)\\)";//9:an
        //String pattern = "if[(].+[}]|for[(].+[}]";//10:an

        int number = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            number++;
            finder(line, pattern, number);
        }
    }

    public static void finder (String myString, String pattern, int number) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);

        while (matcher.find()){
            if(matcher.group().length() != 0){
                count++;
                System.out.println("1: " + matcher.group() + " is on row: " + number);                  //532
                //System.out.println("2: " + matcher.group() + " is on row: " + number);                  //line 240,331
                //System.out.println("3: " + matcher.group() + count + " is on row: " + number);          //on 5 places line 315,334,341,362,376
                //System.out.println("4: " + count + " is on row: " + number);                            //4296
                //System.out.println("5: " + count+ " " + matcher.group() +" is on row: " + number);      //5st
                //System.out.println("6: on row " + number + " is the phonenumber: " + matcher.group());  //8st
                //System.out.println("7: "+ count + " " + matcher.group()+ " is on row: " + number);      //5st
                //System.out.println("8: " + matcher.group() + " is on row: " + number + " " + count + " " );//3st on line 96,230,645
                //System.out.println("9: " + count + " is on row: " + number + " and contains " + matcher.group());      //13st
                //System.out.println("9: " +matcher.group() + " " + count + " " + " is on row: " + number);// 4st
            }
        }
    }
}
