import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Filter {

        public String checkType(String line){
                try {
                    if(line.toLowerCase().contains("e") || line.toLowerCase().contains(".")){
                        Float.parseFloat(line);
                        return "float";
                    }else{
                        Integer.parseInt(line);
                        return "integer";
                    }
                } catch (Exception e) {
                    return "string"; 
                }
        }

   
        
        public String appendLine(String lines, String line){
            if (lines != null){
                lines = lines + System.lineSeparator() + line;
            } else {
                lines = line;
            }
            return lines;
        }

        public String readResultFile(String fileName, String lines){
            try(FileReader reader = new FileReader(fileName)){
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    lines = appendLine(lines, line);
                }
                scanner.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            return lines;
        } 

        public String readInputFile(String fileType, String lines, String inputFile){

            try(FileReader reader = new FileReader(inputFile)){
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if(checkType(line) == fileType){
                        lines = appendLine(lines, line);
                    }
                }
                scanner.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            return lines;
        }

        
        /*
        // ДОБАВИТЬ ПАРАМЕТР УКАЗЫВАЮЩИЙ КАКОЙ ФАЙЛ СОЗДАТЬ!!!!!
        public void createFiles(String type, boolean rewrite){
            try {
                switch (type) {
                    case ("float"):
                        System.out.println("float");
                        if (!floatTextFile.exists()){
                            floatTextFile.createNewFile();
                        } else if (rewrite){
                            floatTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("integer"):
                        System.out.println("integer");
                        if (!integerTextFile.exists()){
                            integerTextFile.createNewFile();
                        } else if (rewrite){
                            integerTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("string"):
                        System.out.println("string");
                        if (!stringTextFile.exists()){
                            stringTextFile.createNewFile();
                        } else if (rewrite){
                            stringTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("createNewAll"):
                    {
                        stringTextFile.createNewFile();
                        integerTextFile.createNewFile();
                        floatTextFile.createNewFile();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
            */
}
