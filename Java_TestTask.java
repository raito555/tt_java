import java.io.*;

public class Java_TestTask {
    public static void main(String[] args) {

        // для каждого из аргумента параметра запуска выполняем
        for (String s: args){
            try(FileReader reader = new FileReader(s)){
                // посимвольное читаем и выводим в консоль
                int symbol; 
                String textFile = "";
                while((symbol = reader.read()) != -1){
                    //System.out.print((char)symbol);
                    textFile += (char)symbol;
                }
                
                // Разделение на строки
                String[] linesOfTextFile = textFile.split("\\n");

                for(String line : linesOfTextFile){
                    System.out.println(line);
                }
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }


        }
    }
}