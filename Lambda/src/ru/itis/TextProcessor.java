package ru.itis;

/**
 * 28.02.2018
 * TextProcessor
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class TextProcessor {

    private String lines[];

    public TextProcessor(String[] lines) {
        this.lines = lines;
    }

    public void process(LineProcessRule rule ) {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = rule.process(lines[i]);
        }
    }

    public void show() {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
