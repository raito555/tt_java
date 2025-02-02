import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FilterContent {

    void filter(String[] fileNames){
        Filter filter = new Filter();
        
        for (String s: fileNames){

            try(FileReader reader = new FileReader(s)){
                Scanner scanner = new Scanner(reader);

                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    // вызываем метод создания файла, в нее передаем результат метода checkType, 
                    // например если в файле не встретится integer, то файл не будет создаваться
                    filter.createFiles(filter.checkType(line), true);
                }  
                scanner.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}