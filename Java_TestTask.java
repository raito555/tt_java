import java.io.*;

public class Java_TestTask {
    public static void main(String[] args) {

        // для каждого из аргумента параметра запуска выполняем
        for (String s: args){
            try(FileReader reader = new FileReader(s)){
                // посимвольное читаем и выводим в консоль
                int symbol; 
                while((symbol = reader.read()) != -1){
                    System.out.print((char)symbol);
                }
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            //System.out.println(s);
        }
    }
}