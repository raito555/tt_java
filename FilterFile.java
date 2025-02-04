import java.io.File;
import java.io.FileWriter;

public class FilterFile {
    String fileParentPath;
    String fileType;
    String fileNamePrefix = "";
    String fileContent;
    String fileName;
    String filePath;

    public FilterFile(){}

    public FilterFile(String parentPath, String type, String fileNamePrefix){
        fileParentPath = parentPath;
        fileType = type;
        fileName = fileNamePrefix + fileType + "s.txt";
        filePath = fileParentPath + fileName;
    }

    public FilterFile(String parentPath, String type){
        fileParentPath = parentPath;
        fileType = type;
        fileName = fileType + "s.txt";
        filePath = fileParentPath + fileName;
    }

    public void writeFile(boolean fileRewrite, boolean isNewFile){
        if (fileContent == null || fileContent.isEmpty())
            return;

        if (!isNewFile && !fileRewrite){
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
}
