package ru.itis;

public class Main {

    public static void main(String[] args) {
	    String lines[] = {"Сидиков", "Марсель", "Рафаэлевич", "Второй",
        "Непобедимый"};

	    TextProcessor processor = new TextProcessor(lines);

	    UpperCaseRule rule = new UpperCaseRule();

	    processor.process(rule);

	    processor.show();

	    // анонимный класс
	    LineProcessRule lowerCaseRule = new LineProcessRule() {
            @Override
            public String process(String line) {
                char lineAsCharArray[] = line.toCharArray();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isUpperCase(lineAsCharArray[i])) {
                        // делаем большой
                        builder.append(Character.toLowerCase(lineAsCharArray[i]));
                    } else {
                        builder.append(lineAsCharArray[i]);
                    }
                }
                return builder.toString();
            }
        };


        processor.show();

        LineProcessRule ruleStar = line -> {
            return line.replace('и', '*');
        };

        processor.process(ruleStar);
        processor.show();

        processor.process(line -> line.replace('а', '@'));
	    processor.process(lowerCaseRule);
	    processor.show();
    }
}
