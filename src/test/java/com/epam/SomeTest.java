package com.epam;

import java.io.*;
import java.util.*;
import java.io.*;

public class SomeTest {
//    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    private static ConnectionPool pool = new ConnectionPool(resourceBundle);

    public static void main(String[] args) {
        String s = "a";
        String s1 = "a";

        System.out.println(s==s1);
//        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
//        Scanner scanner = new Scanner(System.in);
//        String location = scanner.nextLine();
//
//        try {
//            byte[] encoded = Files.readAllBytes(Paths.get(location));
//            String text = new String(encoded, "UTF-8");
//            System.out.println(text);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HashSet<String> words = new HashSet<String>(100);
// использовать коллекции LinkedHashSet или TreeSet
//        long callTime = System.nanoTime();
//        try {
//            BufferedReader in =
//                    new BufferedReader(
//                            new FileReader("sample"));
//            String line = "";
//            while ((line = in.readLine()) != null) {
//                StringTokenizer tokenizer =
//                        new StringTokenizer(line,
//                                " (){}[]<>#*!?.,:;-\'\"/");
//
//                while (tokenizer.hasMoreTokens()) {
//                    String word = tokenizer.nextToken();
//                    words.add(word.toLowerCase());
//                }
//            }
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//        Iterator<String> it = words.iterator();
//        while (it.hasNext())
//            System.out.println(it.next());
//        long totalTime = System.nanoTime() - callTime;
//        System.out.println("различных слов: " + words.size()
//                + ", " + totalTime + " наносекунд");

    }
}
