import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FilterContent {

    void filter(String[] fileNames){
        Filter filter = new Filter();
        FilterFile filterIntegers = new FilterFile();
        FilterFile filterFloats = new FilterFile();
        FilterFile filterStrings = new FilterFile();
        filterIntegers.fileType = "integer";
        filterFloats.fileType = "float";
        filterStrings.fileType = "string";

        for (String s: fileNames){

            try(FileReader reader = new FileReader(s)){
                Scanner scanner = new Scanner(reader);

                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    // вызываем метод создания файла, в нее передаем результат метода checkType, 
                    // например если в файле не встретится integer, то файл не будет создаваться
                    String fileType = filter.checkType(line);
                    switch (fileType) {
                        case "float":
                            filterFloats.appendLine(line);
                            break;
                        case "integer":
                            filterIntegers.appendLine(line);
                            break;
                        case "string":
                            filterStrings.appendLine(line);
                            break;
                        default:
                            break;
                    }
                }
                scanner.close();
                System.out.println(filterFloats.fileContent);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}