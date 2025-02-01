//import javax.naming.spi.DirObjectFactory;
import java.io.File;

public class Filter {
        public String checkType(String line){
                try {
                    if(line.toLowerCase().contains("e") || line.toLowerCase().contains(".")){
                        Float.parseFloat(line);
                        //System.out.println("float");
                        return "float";
                    }else{
                        Integer.parseInt(line);
                        //System.out.println("integer");
                        return "integer";
                    }
                } catch (Exception e) {
                    //System.out.println(line);
                    //System.out.println("ошибка");
                    return "string"; 
                }
        }
        public void filter(){
            //String currentPath = new File(".").
            //File directory = new File(".");
            File floatTextFile = new File(".", "floats.txt");
            File integerTextFile = new File(".", "integers.txt");
            File stringTextFile = new File(".", "strings.txt");
            try {
                floatTextFile.createNewFile();
                integerTextFile.createNewFile();
                stringTextFile.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
}
