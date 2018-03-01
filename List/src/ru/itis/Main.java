package ru.itis;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.add(113);
        list1.add(124);
        list1.add(135);

        list2.add(15);
        list2.add(115);
        list2.add(125);
        list2.add(152);


//        System.out.println(list);
//        list.addToBegin("Теперь это начало!");
//        list.reverse();
//        System.out.println(list);


        LinkedList<Integer> list = LinkedList.merge(list1, list2);

        for(Integer temp : list)
            System.out.println(temp);
        int i = 0;
    }
}
