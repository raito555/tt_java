import java.util.Scanner;

public class Statistic {
    int elementCount = 0;
    String fileType;
    String fileContent;

    public Statistic(String fileType){
        this.fileType = fileType;
    }

    public void addContent(String fileContent){
        if (fileContent == null || fileContent.isEmpty()){
            return;
        }

        if (this.fileContent != null){
            this.fileContent = this.fileContent + System.lineSeparator() + fileContent;
        } else {
            this.fileContent = fileContent;
        }
    }

    public class Integers{
        Integer elementMax;
        Integer elementMin;
        int elementsSum;
        int elementsAvg;
        int element;

        public void printStat(boolean isFullStat){
            Scanner scannerInt = new Scanner(fileContent);

            while (scannerInt.hasNextLine()){
                try {
                    String line = scannerInt.nextLine();
                    element = Integer.parseInt(line);

                    if (elementMax == null)
                        elementMax = element;
                    else if (elementMax < element)
                        elementMax = element;

                    if (elementMin == null)
                        elementMin = element;
                    else if (elementMin > element) 
                        elementMin = element;

                    elementsSum = elementsSum + element;
                    
                } catch (Exception e) {
                    System.out.println("printStat Integers exception: " + e);
                }
                elementCount++;
            }
            elementsAvg = elementsSum / elementCount;

            System.out.println("Статистика записанных значений по " + fileType + ":");
            System.out.println("\t Количество элементов: " + elementCount);

            if (isFullStat){
                System.out.println("\t Максимальное значение : " + elementMax);
                System.out.println("\t Минимальное значение : " + elementMin);
                System.out.println("\t Сумма значений : " + elementsSum);
                System.out.println("\t Среднее значение : " + elementsAvg);
            }

            scannerInt.close();
        }
    }

    public class Floats{
        Float elementMax;
        Float elementMin;
        Float elementSum;
        Float elementAvg;
        float elementsSum;
        float elementsAvg;

        public void printStat(boolean isFullStat){
            Scanner scannerFloat = new Scanner(Statistic.this.fileContent);

            while (scannerFloat.hasNextLine()){
                try {
                    String line = scannerFloat.nextLine();
                    float element = Float.parseFloat(line);

                    if (elementMax == null)
                        elementMax = element;
                    else if (elementMax < element)
                        elementMax = element;

                    if (elementMin == null)
                        elementMin = element;
                    else if (elementMin > element) 
                        elementMin = element;

                    elementsSum = elementsSum + element;
                    
                } catch (Exception e) {
                    System.out.println("printStat Floats exception: " + e);
                }
                elementCount++;
            }
            elementsAvg = elementsSum / elementCount;

            System.out.println("Статистика записанных значений по " + fileType + ":");
            System.out.println("\t Количество элементов: " + elementCount);
            
            if (isFullStat){
                System.out.println("\t Максимальное значение : " + elementMax);
                System.out.println("\t Минимальное значение : " + elementMin);
                System.out.println("\t Сумма значений : " + elementsSum);
                System.out.println("\t Среднее значение : " + elementsAvg);
            }
            
            scannerFloat.close();
        }
    }

    public class Strings{
        Integer lengthShortest;
        Integer lengtLongest;

        public void printStat(boolean isFullStat){
            Scanner scannerStrings = new Scanner(Statistic.this.fileContent);
            while (scannerStrings.hasNextLine()){
                try {
                    String line = scannerStrings.nextLine();

                    if (lengtLongest == null)
                        lengtLongest = line.length();
                    else if (lengtLongest < line.length())
                        lengtLongest = line.length();

                    if (lengthShortest == null)
                        lengthShortest = line.length();
                    else if (lengthShortest > line.length())
                        lengthShortest = line.length();
                    
                } catch (Exception e) {
                    System.out.println("printStat Strings exception: " + e);
                }
                elementCount++;
            }

            System.out.println("Статистика записанных значений по " + fileType + ":");
            System.out.println("\t Количество элементов: " + elementCount);
            if (isFullStat){
                System.out.println("\t Размер самой длинной строки : " + lengtLongest);
                System.out.println("\t Размер самой короткой строки : " + lengthShortest);
            }
            scannerStrings.close();
        }
    }
}
