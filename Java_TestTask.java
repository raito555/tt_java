import java.io.*;
//import test.java;
import java.util.Scanner;

public class Java_TestTask {

    public static void main(String[] args) {

        for (String s: args){

            try(FileReader reader = new FileReader(s)){
                
                Scanner scanner = new Scanner(reader);

                if (scanner.hasNextInt()){
                    String line = scanner.nextLine();
                    System.out.println("Integer - " + line);
                } else {
                    System.out.println("Не интеджер");
                }

                scanner.close();
            }
            
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}