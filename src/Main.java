/**
 * @author Wojciech Natkaniec
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String path = System.getProperty("user.home") + "/tab.txt";
        List<Integer> elements = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String row;
             Pattern pattern = Pattern.compile("(?<element>-?\\w+)\\s*");
            while ((row = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(row);
                while (matcher.find()) {
                    try {
                        Integer element = Integer.valueOf(matcher.group("element"));
                        elements.add(element);
                    } catch (NumberFormatException e) {
                        System.out.println("***");
                        System.exit(1);
                    }
                }
            }
            if (elements.isEmpty()) {
                System.out.println("***");
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("***");
            System.exit(1);
        }
        printResult(elements);
    }

    public static int getMax(List<Integer> integers) {
        List<Integer> copy = new ArrayList<>(integers);
        copy.sort(Comparator.reverseOrder());
        return copy.get(0);
    }

    public static List<Integer> getIndexes(List<Integer> integers, int max) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) == max) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static void printResult(List<Integer> elements) {
        elements.forEach(element -> System.out.print(element + " "));
        System.out.println();
        System.out.println(getMax(elements));
        List<Integer> indexes = getIndexes(elements, getMax(elements));
        indexes.forEach(element -> System.out.print(element + " "));
    }
}
