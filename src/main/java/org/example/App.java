package org.example;

import com.sun.tools.javac.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException {

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("10m.txt");

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                List<Integer> numbers = reader.lines()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

                System.out.println("Максимальне число в файлі: " + numbers.stream().mapToInt(Integer::intValue).max().orElseThrow());
                System.out.println("Мінімальне число в файлі: " + numbers.stream().mapToInt(Integer::intValue).min().orElseThrow());
                System.out.println("Найбільша послідовність чисел, яка збільшується: "+findMaxIncreasingSequence(numbers));
                System.out.println("Найбільшу послідовність чисел, яка зменьшується : "+findMaxDecreasingSequence(numbers));
                System.out.println("Середнє арифметичне значення: " + calculateAverage(numbers));
                System.out.println("Медіана: " + calculateMedian(numbers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл не найден в ресурсах");
        }
    }






    public static int findMaxIncreasingSequence(List<Integer> numbers) {
            int maxSequenceLength = 0;
            int currentSequenceLength = 1;


            for (int i = 1; i < numbers.size(); i++) {

            if (numbers.get(i) > numbers.get(i - 1)) {
                currentSequenceLength++;
            } else {

                maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
                currentSequenceLength = 1;
            }
        }


        maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);

        return maxSequenceLength;
    }
    public static int findMaxDecreasingSequence(List<Integer> numbers) {
        int maxLength = 1;
        int currentLength = 1;


        for (int i = 1; i < numbers.size(); i++) {

            if (numbers.get(i) < numbers.get(i - 1)) {
                currentLength++;
            } else {


                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }


        maxLength = Math.max(maxLength, currentLength);

        return maxLength;
    }
    public static double calculateAverage(List<Integer> numbers) {

        if (numbers.isEmpty()) {
            return 0.0;
        }


        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }


        return (double) sum / numbers.size();
    }
    public static double calculateMedian(List<Integer> numbers) {

        if (numbers.isEmpty()) {
            return 0.0;
        }


        Collections.sort(numbers);

        int size = numbers.size();

        if (size % 2 != 0) {
            return numbers.get(size / 2);
        } else {

            int middleIndex = size / 2;
            return (double) (numbers.get(middleIndex - 1) + numbers.get(middleIndex)) / 2;
        }
    }
}
