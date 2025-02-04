import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
    boolean fileRewrite = true;

    void start(String[] args){
        Filter filter = new Filter();

        Pattern patternOptionPath = Pattern.compile("\\-{1}o");
        Pattern patternTextFile = Pattern.compile(".+\\.txt");

        for (String arg: args){
            
            Matcher matcherOptionPath = patternOptionPath.matcher(arg);
            if (matcherOptionPath.find()){
                
            }

            Matcher matcherTextFile = patternTextFile.matcher(arg);
            if (matcherTextFile.find()){
                FilterFile filterFloats = new FilterFile("./", "float");
                FilterFile filterStrings = new FilterFile("./", "string");
                FilterFile filterIntegers = new FilterFile("./", "integer");  
                filterIntegers.fileContent = filter.readInputFile(filterIntegers.fileType, filterIntegers.fileContent, arg);
                filterStrings.fileContent = filter.readInputFile(filterStrings.fileType, filterStrings.fileContent, arg);
                filterFloats.fileContent = filter.readInputFile(filterFloats.fileType, filterFloats.fileContent, arg);

                filterIntegers.writeFile(fileRewrite, filter.isEmptyFile(filterIntegers.filePath));
                filterStrings.writeFile(fileRewrite, filter.isEmptyFile(filterStrings.filePath));
                filterFloats.writeFile(fileRewrite, filter.isEmptyFile(filterFloats.filePath));
                
                fileRewrite = false;
            }
        }
    }
}