import java.util.Scanner;

public class Closet {
    //main
    public static void menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Random Outfit Selector! Would you like to \na)Select a random outfit from our database \nb)Add an outfit item to the database");
        String input = in.next();
        if (input.equals("a") || input.equals("A")){

        }
        else if (input.equals("b") || input.equals("B")){

        } else menu();
    }
    public static void main(String[] args){
        menu();
    }
}
