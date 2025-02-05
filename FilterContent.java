import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
    boolean fileRewrite = true;
    String parentPath = "./";
    String prefix = "";

    void start(String[] args){
        Filter filter = new Filter();

        Pattern patternOption = Pattern.compile("\\-{1}.");
        Pattern patternTextFile = Pattern.compile(".+\\.txt");

        for (int i = 0; i < args.length; i++){
            
            Matcher matcherOptionPath = patternOption.matcher(args[i]);
            if (matcherOptionPath.find()){
                switch (args[i]) {
                    case "-a":
                        fileRewrite = false;
                        break;
                    case "-o":
                        parentPath = args[i+1];
                        break;
                    case "-p":
                        prefix = args[i+1];
                        break;
                    default:
                        break;
                }
            }

            Matcher matcherTextFile = patternTextFile.matcher(args[i]);
            if (matcherTextFile.find()){
                FilterFile filterFloats = new FilterFile(filter.addSlashesToPath(parentPath), "float", prefix);
                FilterFile filterStrings = new FilterFile(filter.addSlashesToPath(parentPath), "string", prefix);
                FilterFile filterIntegers = new FilterFile(filter.addSlashesToPath(parentPath), "integer", prefix);  
                filterIntegers.fileContent = filter.readInputFile(filterIntegers.fileType, filterIntegers.fileContent, args[i]);
                filterStrings.fileContent = filter.readInputFile(filterStrings.fileType, filterStrings.fileContent, args[i]);
                filterFloats.fileContent = filter.readInputFile(filterFloats.fileType, filterFloats.fileContent, args[i]);

                filterIntegers.writeFile(fileRewrite, filter.isEmptyFile(filterIntegers.filePath));
                filterStrings.writeFile(fileRewrite, filter.isEmptyFile(filterStrings.filePath));
                filterFloats.writeFile(fileRewrite, filter.isEmptyFile(filterFloats.filePath));
                
                fileRewrite = false;
            }
        }
    }
}