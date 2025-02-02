import java.io.File;
import java.io.FileWriter;

public class FilterFile {
    String filePath = ".";
    String fileType;
    String fileNamePrefix = "";
    boolean fileRewrite = true;

    String fileContent;

    String fileName;

    public FilterFile(){}

    public FilterFile(String path, String type, String prefix, boolean rewrite){
        filePath = path;
        fileType = type;
        fileNamePrefix = prefix;
        fileRewrite = rewrite;
    }

    public void appendLine(String line){
        if (fileContent != null){
            fileContent = fileContent + System.lineSeparator() + line;
        } else {
            fileContent = line;
        }
    }

    public void createFile(){
        if (fileContent == null || fileContent.isEmpty())
            return;
        
        boolean append = !fileRewrite;

        if (fileType != null && !fileType.isEmpty()){
            fileName = fileNamePrefix + fileType + "s.txt";
            File textFile = new File(filePath, fileName);

            try (FileWriter writer = new FileWriter(textFile, append)){
                fileRewrite = false;
                writer.write(fileContent);
                fileContent = "";
                writer.flush();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Не указан тип данных");
        }
    }
}
