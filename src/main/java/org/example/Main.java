package org.example;

import java.util.Random;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        OnlyWord o = new OnlyWord();
        Growth g = new Growth();

        Thread first = new Thread(c, "First");
        Thread second = new Thread(o, "Second");
        Thread third = new Thread(g, "Third");

        first.start();
        second.start();
        third.start();

        first.join();
        second.join();
        third.join();

        System.out.println(g);
        System.out.println(o);
        System.out.println(c);
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}