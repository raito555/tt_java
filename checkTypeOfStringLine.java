//import javax.naming.spi.DirObjectFactory;

public class checkTypeOfStringLine {
        public String checkType(String line){
                try {
                    if(line.toLowerCase().contains("e") || line.toLowerCase().contains(".")){
                        Float.parseFloat(line);
                        System.out.println("float");
                        return "Float";
                    }else{
                        Integer.parseInt(line);
                        System.out.println("integer");
                        return "Integer";
                    }
                } catch (Exception e) {
                    System.out.println("string");
                    //System.out.println("ошибка");
                    return "String"; 
                }
        }
}
