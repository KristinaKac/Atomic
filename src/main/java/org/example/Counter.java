package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.Main.generateText;

class Counter extends Thread {
    AtomicInteger three;
    AtomicInteger four;
    AtomicInteger five;

    Counter() {
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
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(0) == s.charAt(s.length() - 1)) {
                    s.deleteCharAt(0);
                    s.deleteCharAt(s.length() - 1);
                    if (s.length() == 0 || s.length() == 1) {
                        if (n.length() == 3) {
                            three.addAndGet(1);
                        } else if (n.length() == 4){
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
        return "Кол-во красивых слов-палиндромов, \n" +
                "Длина 3: " + three + '\n' +
                "Длина 4: " + four + '\n' +
                "Длина 5: " + five + '\n' +
                '\n';
    }
}
