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
    boolean isFullStat = false;
    boolean fileRewrite = true;

    public FilterFile(String fileParentPath, String fileType){
        this.fileParentPath = addSlashesToPath(fileParentPath);
        this.fileType = fileType;
    }

    public static String checkType(String line){
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

    public static boolean isEmptyFile(String path){
        boolean isEmpty = true;

        try(FileReader reader = new FileReader(path)){
            Scanner scannerOutFile = new Scanner(reader);

            while (scannerOutFile.hasNext()) {
                String line = scannerOutFile.nextLine();
                if(line != null && !line.isEmpty()){
                    isEmpty = false;
                    break;
                } 
            }
            scannerOutFile.close();
            return isEmpty;
        }
        catch(IOException ex){
            return true;
        }
    }

    public static String addSlashesToPath(String path){
        
        Pattern patternSlash = Pattern.compile("(.*)"+ "/" + "(.*)");
        Matcher matcherSlash = patternSlash.matcher(path);

        if (matcherSlash.find()){
            path = path.replace("/", File.separator);
        }

        Pattern parentPath = Pattern.compile("[a-zA-Z]{1}:.+");
        Matcher matcherParentPath = parentPath.matcher(path);

        Pattern slashFirst = Pattern.compile("^\\" + File.separator + "(.+)");
        Matcher matcherSlashFirst = slashFirst.matcher(path);

        Pattern slashEnd = Pattern.compile("(.+)\\" + File.separator);
        Matcher matcherSlashEnd = slashEnd.matcher(path);

        if (!matcherSlashFirst.find() && !matcherParentPath.find())
            path = File.separator + path;

        if (!matcherSlashEnd.find()){
            path = path + File.separator;
        }

        return path;
    }

    public void writeFile(){
        boolean append = !fileRewrite;
        fileName = fileNamePrefix + fileType + "s.txt";
        filePath = fileParentPath + addSlashesToPath(fileName);

        if (fileContent == null || fileContent.isEmpty())
            return;

        if (fileType != null && !fileType.isEmpty()){
            File textFile = new File(fileParentPath, fileName);
            try (FileWriter writer = new FileWriter(textFile, append)){

                if (fileRewrite == false && isEmptyFile(filePath) == false){
                    writer.write(System.lineSeparator() + fileContent);
                } else {
                    writer.write(fileContent);
                }
                
                writer.flush();
                fileRewrite = false;
            } catch (Exception e) {
                System.out.println("Неудается найти указанный путь для сохранения файла: " + textFile);
            }
        } else {
            System.out.println("Не указан тип данных");
        }
    }

    public void readInputFile(String inputFile){
        try(FileReader reader = new FileReader(inputFile)){
            Scanner scannerInFile = new Scanner(reader);

            while (scannerInFile.hasNext()) {
                String line = scannerInFile.nextLine();

                if(checkType(line) == fileType){
                    if (fileContent != null){
                        fileContent = fileContent + System.lineSeparator() + line;
                    } else {
                        fileContent = line;
                    }
                }
            }
            scannerInFile.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
