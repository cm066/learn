package com.cm.learn23;

import java.util.*;

public class ComparableAndComparator {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 20));
        students.add(new Student(3, 18));
        students.add(new Student(2, 24));
        students.add(new Student(4, 19));
        students.add(new Student(5, 26));
        System.out.println(students);
        Collections.sort(students);
        System.out.println(students);
        new String();
        new TreeSet<>();
//        List<House> houses = new ArrayList();
//        House h1 = new House(95.0, 12000);
//        House h2 = new House(110.0, 12160);
//        House h3 = new House(80.0, 16300);
//        House h4 = new House(150.3, 10690);
//        houses.add(h1);
//        houses.add(h2);
//        houses.add(h3);
//        houses.add(h4);
//        System.out.println(houses);
//        Collections.sort(houses, new ComparatorDetail());
//        System.out.println(houses);
    }

    static class ComparatorDetail implements Comparator<House> {
        @Override
        public int compare(House o1, House o2) {
            if (o1.getPrice() < o2.getPrice())
                return -1;
            else if (o1.getPrice() > o2.getPrice())
                return 1;
            return 0;
        }
    }
}

