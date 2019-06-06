package com.demo.test;

import com.demo.service.AccountService;
import com.jfinal.aop.Aop;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class TestMain {

    static AccountService service = Aop.get(AccountService.class);

    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bdc");
        list.add("cbc");
        list.add("cbc");
        list.add("abc");
        for(String str:list){
            System.out.println(str);
        }*/
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("abc","A0"));
        dogs.add(new Dog("bdc","A1"));
        dogs.add(new Dog("cbc","A2"));
        dogs.add(new Dog("cac","A3"));
        dogs.add(new Dog("cdc","A4"));
        dogs.add(new Dog("abd","A5"));
        dogs.add(new Dog("dda","A6"));
//        dogs.add(new Dog("cbc","A3"));
//        dogs.add(new Dog("abc","A4"));

        for (Dog dog:dogs) {
            System.out.println(dog.getId()+"--"+dog.getName());
        }
        System.out.println("=================================");
        /*List<Dog> dogList = quChong(dogs);
        for (Dog dog:dogList) {
            System.out.println(dog.getId()+"--"+dog.getName());
        }*/

        List<String> target = new ArrayList<>();
        target.add("abc");
        target.add("cbc");
        target.add("dda");
        target.add("bdc");
        target.add("cac");
        target.add("cdc");
        target.add("abd");

        for (String str:target) {
            for (int i = 0; i < dogs.size(); i++) {
                if (dogs.get(i).getId().equals(str)) {
                    dogs.remove(i);
                    i--;
                }
            }
        }

        if (dogs.size() > 0) {
            for (Dog dog:dogs) {
                System.out.println(dog.getId()+"--"+dog.getName());
            }
        } else {
            System.out.println("删光了。。。。");
        }



    }

    public static List<Dog> quChong(List<Dog> list) {
        Set<Dog> set = new TreeSet<Dog>(new Comparator<Dog>() {
            @Override
            public int compare(Dog a, Dog b) {
                // 字符串则按照asicc码升序排列
                return a.getId().compareTo(b.getId());
            }
        });

        set.addAll(list);
        return new ArrayList<Dog>(set);
    }






    public static void verifyUnique(List<String> list) {

        //将list放入set中对其去重
        Set<String> set = new HashSet<>(list);
        System.out.println("---------------------------------------");

        //获得list与set的差集
        Collection rs = CollectionUtils.disjunction(list,set);
        //将collection转换为list
        List<String> list1 = new ArrayList<>(rs);
        for(String str:list1){
            System.out.println(str);
        }

    }









}
