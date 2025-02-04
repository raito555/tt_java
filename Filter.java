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

        /*
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
                //System.out.println(ex.getMessage());
                return null;
            }
            return lines;
        } 
        */

        public boolean isEmptyFile(String fileName){
            boolean isEmpty = true;
            try(FileReader reader = new FileReader(fileName)){
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if(line != null && !line.isEmpty()){
                        isEmpty = false;
                        break;
                    } 
                }
                scanner.close();
                return isEmpty;
            }
            catch(IOException ex){
                //System.out.println(ex.getMessage());
                return true;
            }
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
}
