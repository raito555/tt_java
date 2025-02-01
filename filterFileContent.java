import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class filterFileContent {
    void filter(String[] fileNames){
        checkTypeOfStringLine typeChecker = new checkTypeOfStringLine();
        for (String s: fileNames){
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