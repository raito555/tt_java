import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
    boolean fileRewrite = true;

    void filter(String[] args){
        Filter filter = new Filter();

        //String filePath = "./";
        
        //FilterFile fileStrings = new FilterFile(filePath, "string");
        //FilterFile fileIntegers = new FilterFile(filePath, "integer");
        //FilterFile fileFloats = new FilterFile(filePath, "float");

        //filter.readFile(fileStrings);


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

                filterIntegers.createFile(fileRewrite);
                filterStrings.createFile(fileRewrite);
                filterFloats.createFile(fileRewrite);
                fileRewrite = false;
                
                /*
                    scanner.close();
                    filterIntegers.createFile();
                    filterFloats.createFile();
                    filterStrings.createFile();
                    fileRewrite = false;
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                     */
            }
        }
    }
}