import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        public String addSlashesToPath(String path){
            Pattern parentPath = Pattern.compile("[a-zA-Z]{1}:.+");
            Matcher matcherParentPath = parentPath.matcher(path);

            Pattern slashFirst = Pattern.compile("^\\" + File.separator + "(.+)");
            Matcher matcherSlashFirst = slashFirst.matcher(path);

            Pattern slashEnd = Pattern.compile("(.+)\\" + File.separator);
            Matcher matcherSlashEnd = slashEnd.matcher(path);

            if (!matcherSlashFirst.find() && !matcherParentPath.find())
                path = File.separator + path;
            if (!matcherSlashEnd.find()) 
                path = path + File.separator;
            return path;
        }

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
