import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
    boolean fileRewrite = true;
    String parentPath = "./";
    String prefix = "";

    void start(String[] args){
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
                        i++;
                        break;
                    case "-p":
                        prefix = args[i+1];
                        i++;
                        break;
                    default:
                        System.out.println("Неизвестный опция: " + args[i]);
                        break;
                }
                continue;
            }

            Matcher matcherTextFile = patternTextFile.matcher(args[i]);
            if (matcherTextFile.find()){
                FilterFile filterFloats = new FilterFile(parentPath, "float", prefix);
                FilterFile filterStrings = new FilterFile(parentPath, "string", prefix);
                FilterFile filterIntegers = new FilterFile(parentPath, "integer", prefix);  
                filterIntegers.readInputFile(args[i]);
                filterStrings.readInputFile(args[i]);
                filterFloats.readInputFile(args[i]);

                filterIntegers.writeFile(fileRewrite);
                filterStrings.writeFile(fileRewrite);
                filterFloats.writeFile(fileRewrite);
                
                fileRewrite = false;

                continue;
            }

            System.out.println("Неизвестный параметр: " + args[i]);
        }
    }
}