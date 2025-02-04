import java.io.File;
import java.io.FileWriter;

public class FilterFile {
    String fileParentCatalog;
    String fileType;
    String fileNamePrefix = "";
    //boolean fileRewrite = true;
    String fileContent;
    String fileName;

    public FilterFile(){}

    public FilterFile(String path, String type, String fileNamePrefix){
        fileParentCatalog = path;
        fileType = type;
        //fileRewrite = rewrite;
        fileName = fileNamePrefix + fileType + "s.txt";
    }

    public FilterFile(String path, String type){
        fileParentCatalog = path;
        fileType = type;
        fileName = fileType + "s.txt";
    }

    /*
    public void appendLine(String line){
        if (fileContent != null){
            fileContent = fileContent + System.lineSeparator() + line;
        } else {
            fileContent = line;
        }
    }
    */
    public void createFile(boolean fileRewrite){
        if (fileContent == null || fileContent.isEmpty())
            return;

        boolean append = !fileRewrite;

        if (fileType != null && !fileType.isEmpty()){
            File textFile = new File(fileParentCatalog, fileName);
            try (FileWriter writer = new FileWriter(textFile, append)){
                writer.write(fileContent);
                writer.flush();
                //fileContent = null;
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Не указан тип данных");
        }
    }
}
