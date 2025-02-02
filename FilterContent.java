import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {

    void filter(String[] args){
        Filter filter = new Filter();
        FilterFile filterIntegers = new FilterFile();
        FilterFile filterFloats = new FilterFile();
        FilterFile filterStrings = new FilterFile();
        filterIntegers.fileType = "integer";
        filterFloats.fileType = "float";
        filterStrings.fileType = "string";

        Pattern patternOptions = Pattern.compile("\\-{1}.");
        Pattern patternTextFile = Pattern.compile(".+\\.txt");
            

        for (String arg: args){
            
            Matcher matcherOptions = patternOptions.matcher(arg);
            if (matcherOptions.find()){
                
            }

            Matcher matcherTextFile = patternTextFile.matcher(arg);
            if (matcherTextFile.find()){
                try(FileReader reader = new FileReader(arg)){
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
                    filterIntegers.createFile();
                    filterFloats.createFile();
                    filterStrings.createFile();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}