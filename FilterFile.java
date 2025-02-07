import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterFile {
    String fileParentPath;
    String fileType;
    String fileNamePrefix = "";
    String fileContent;
    String fileName;
    String filePath;

    public FilterFile(){}

    public FilterFile(String parentPath, String type, String prefix){
        fileParentPath = addSlashesToPath(parentPath);
        fileType = type;
        fileNamePrefix = prefix;
        fileName = fileNamePrefix + fileType + "s.txt";
        filePath = fileParentPath + fileName;
    }

    public FilterFile(String parentPath, String type){
        fileParentPath = addSlashesToPath(parentPath);
        fileType = type;
        fileName = fileType + "s.txt";
        filePath = fileParentPath + fileName;
    }

    public void writeFile(boolean fileRewrite){
        if (fileContent == null || fileContent.isEmpty())
            return;

        if (!isEmptyFile(filePath) && !fileRewrite){
            fileContent = System.lineSeparator() + fileContent;
        }

        boolean append = !fileRewrite;

        if (fileType != null && !fileType.isEmpty()){
            File textFile = new File(fileParentPath, fileName);
            try (FileWriter writer = new FileWriter(textFile, append)){
                writer.write(fileContent);
                writer.flush();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Не указан тип данных");
        }
    }

    public boolean isEmptyFile(String path){
        boolean isEmpty = true;
        try(FileReader reader = new FileReader(path)){
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
            return true;
        }
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

    public void readInputFile(String inputFile){

        try(FileReader reader = new FileReader(inputFile)){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if(checkType(line) == fileType){
                    if (fileContent != null){
                        fileContent = fileContent + System.lineSeparator() + line;
                    } else {
                        fileContent = line;
                    }
                }
            }
            scanner.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

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
}
