import java.io.*;
//import test.java;
import java.util.Scanner;

public class Java_TestTask {

    public static void main(String[] args) {
        
        checkTypeOfStringLine typeChecker = new checkTypeOfStringLine();

        for (String s: args){

            try(FileReader reader = new FileReader(s)){
                
                Scanner scanner = new Scanner(reader);

                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    switch (typeChecker.checkType(line)) {
                        case ("float"):
                            System.out.println("float");
                            break;
                        case ("integer"):
                            System.out.println("integer");
                            break;
                        case ("string"):
                            System.out.println("string");
                            break;
                    }
                }  

                scanner.close();
            }

            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}