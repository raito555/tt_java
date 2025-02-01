//import javax.naming.spi.DirObjectFactory;

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
}
