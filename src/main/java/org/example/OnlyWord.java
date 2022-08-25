package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.Main.generateText;
public class OnlyWord extends Thread {
    AtomicInteger three;
    AtomicInteger four;
    AtomicInteger five;

    OnlyWord() {
        three = new AtomicInteger();
        four = new AtomicInteger();
        five = new AtomicInteger();
    }

    public void run() {
        Random random = new Random();
        String[] texts = new String[1_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            String n = texts[i];
            StringBuilder s = new StringBuilder(n);
            int count = 0;
            for (int j = 0; j < s.length() - 1; j++) {
                if (s.charAt(j) == s.charAt(j + 1)) {
                    count = count + 1;
                    if ((count + 1) == s.length()) {
                        if (n.length() == 3) {
                            three.addAndGet(1);
                        } else if (n.length() == 4) {
                            four.addAndGet(1);
                        } else {
                            five.addAndGet(1);
                        }
                    }
                }
            }
        }
    }
    @Override
    public String toString() {
        return "Кол-во красивых слов, состоящее из одной и той же буквы, \n" +
                "Длина 3: " + three + '\n' +
                "Длина 4: " + four + '\n' +
                "Длина 5: " + five + '\n' +
                '\n';
    }
}
