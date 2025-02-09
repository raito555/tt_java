import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
    String fileParentPath = "./";
    Boolean isFullStat;

    void start(String[] args){
        Pattern patternOption = Pattern.compile("\\-{1}.");
        Pattern patternTextFile = Pattern.compile(".+\\.txt");


        Statistic statisticInteger = new Statistic("integer");
        Statistic statisticFloat = new Statistic("float");
        Statistic statisticString = new Statistic("string");

        Statistic.Integers statIntegers = statisticInteger.new Integers();
        Statistic.Floats statFloats = statisticFloat.new Floats();
        Statistic.Strings statStrings = statisticString.new Strings();

        FilterFile filterIntegers = new FilterFile(fileParentPath, "integer");  
        FilterFile filterFloats = new FilterFile(fileParentPath, "float");
        FilterFile filterStrings = new FilterFile(fileParentPath, "string");
        
        for (int i = 0; i < args.length; i++){
            
            Matcher matcherOptionPath = patternOption.matcher(args[i]);

            if (matcherOptionPath.find()){
                switch (args[i]) {
                    case "-a":
                        filterIntegers.fileRewrite = false;
                        filterFloats.fileRewrite = false;
                        filterStrings.fileRewrite = false;
                        break;
                    case "-o":
                        filterIntegers.fileParentPath = FilterFile.addSlashesToPath(args[i+1]);
                        filterFloats.fileParentPath = FilterFile.addSlashesToPath(args[i+1]);
                        filterStrings.fileParentPath = FilterFile.addSlashesToPath(args[i+1]);
                        i++;
                        break;
                    case "-p":
                        filterIntegers.fileNamePrefix = args[i+1];
                        filterFloats.fileNamePrefix = args[i+1];
                        filterStrings.fileNamePrefix = args[i+1];
                        i++;
                        break;
                    case "-s":
                        isFullStat = false;
                        break;
                    case "-f":
                        isFullStat = true;
                        break;
                    default:
                        System.out.println("Неизвестный опция: " + args[i]);
                        break;
                }
                continue;
            }

            Matcher matcherTextFile = patternTextFile.matcher(args[i]);

            if (matcherTextFile.find()){
                filterIntegers.readInputFile(args[i]);
                filterStrings.readInputFile(args[i]);
                filterFloats.readInputFile(args[i]);
                continue;
            }
            System.out.println("Неизвестный параметр: " + args[i]);
        }

        filterIntegers.writeFile();
        filterStrings.writeFile();
        filterFloats.writeFile();

        if (isFullStat != null){
            statisticInteger.addContent(filterIntegers.fileContent);
            statisticString.addContent(filterStrings.fileContent);
            statisticFloat.addContent(filterFloats.fileContent);
            statIntegers.printStat(isFullStat);
            statFloats.printStat(isFullStat);
            statStrings.printStat(isFullStat);
        }
    }
}