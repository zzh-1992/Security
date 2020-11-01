package com.grapefruit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args) {

        List<Stu> list = new ArrayList();

        list.add(new Stu("zzz",111));
        list.add(new Stu("zzz",222));
        list.add(new Stu("ggg",111));

        //filter test
        ArrayList<Stu> collect = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.uniqueId))), ArrayList::new)
        );

        collect.stream().forEach(o->System.out.println(o));

    }
}
