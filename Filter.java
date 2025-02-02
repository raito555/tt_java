//import javax.naming.spi.DirObjectFactory;
import java.io.File;
//import java.lang.classfile.instruction.SwitchCase;

public class Filter {
    File floatTextFile = new File(".", "floats.txt");
    File integerTextFile = new File(".", "integers.txt");
    File stringTextFile = new File(".", "strings.txt");
    
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

        }

        // ДОБАВИТЬ ПАРАМЕТР УКАЗЫВАЮЩИЙ КАКОЙ ФАЙЛ СОЗДАТЬ!!!!!
        public void createFiles(String type, boolean rewrite){
            try {
                switch (type) {
                    case ("float"):
                        System.out.println("float");
                        if (!floatTextFile.exists()){
                            floatTextFile.createNewFile();
                        } else if (rewrite){
                            floatTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("integer"):
                        System.out.println("integer");
                        if (!integerTextFile.exists()){
                            integerTextFile.createNewFile();
                        } else if (rewrite){
                            integerTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("string"):
                        System.out.println("string");
                        if (!stringTextFile.exists()){
                            stringTextFile.createNewFile();
                        } else if (rewrite){
                            stringTextFile.createNewFile();
                            System.out.println("rewrited");
                        }
                        break;
                    case ("createNewAll"):
                    {
                        stringTextFile.createNewFile();
                        integerTextFile.createNewFile();
                        floatTextFile.createNewFile();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
}
