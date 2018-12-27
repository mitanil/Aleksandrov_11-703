package ru.itis;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMapImpl<>();
        map.put("Marsel", "Sidikov");
        map.put("Dasha", "Shagieva");
        map.put("Anita", "Kurova");
        map.put("Irina", "Skripka");
        map.put("Arina", "Kotik");

        System.out.println(map.get("Marsel"));
        System.out.println(map.get("Anita"));
        System.out.println(map.get("Irina"));
        System.out.println(map.get("Arina"));
        System.out.println(map.get("Dasha"));
        System.out.println(map.get("Somebudy"));
    }
}