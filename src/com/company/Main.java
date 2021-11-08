package com.company;

import javax.swing.text.html.Option;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
;

public class Main {

    public static void main(String[] args) {
        Random rn = new Random();

        String[] names = new String[]{"Гошан", "Илюха", "Игорек", "Димас", "Женек", "Тоха"};
        String[] surnames = new String[]{"Иванов", "Петров", "Сидоров", "Андреев", "Фамилия"};
        String[] companies = new String[]{"Отдел Google", "Отдел Meta", "Отдел Apple", "Отдел РПЦ", "Отдел Yandex"};
        String[] positions = new String[]{"БигБосс", "Отец", "Менеджер", "Мужик", "Разраб"};
        List<Worker> workerList = new ArrayList<Worker>();

        for (int i = 0; i < 20; i++) {
            char x;
            if (rn.nextBoolean()) x = 'm';
            else x = 'w';
            Worker worker = new Worker(
                    names[rn.nextInt(names.length)],
                    surnames[rn.nextInt(surnames.length)],
                    ThreadLocalRandom.current().nextInt(18,80),
                    x,
                    companies[rn.nextInt(companies.length)],
                    positions[rn.nextInt(positions.length)],
                    ThreadLocalRandom.current().nextInt(25000,80000),
                    ThreadLocalRandom.current().nextInt(1,20)
            );

            if (rn.nextBoolean()) worker.setCat(new Cat());
            if (rn.nextBoolean()) worker.setPrime(ThreadLocalRandom.current().nextInt(10000, 40000));

            workerList.add(worker);
        }
        System.out.println("Начнем эксперементы над StreamApi\n");


        // a. Вывести работника с максимальной зп среди всех
        Stream<Worker> aStream = workerList.stream();
        Worker aWorker = aStream.reduce((a,b) -> a.getSalary()  > b.getSalary() ? a : b).get();
        System.out.println("a) Самым богатый стал " + aWorker.getName() + ": " + aWorker.getSalary() + " гривен на счету!\n");


        // b. Вывести работника с минимальной зп среди всех
        Stream<Worker> bStream = workerList.stream();
        Worker bWorker = bStream.reduce((a,b) -> a.getSalary() < b.getSalary() ? a : b).get();
        System.out.println("b) Самым бедным стал " + bWorker.getName() + ": " + bWorker.getSalary() + " тугриков за месяц (всего-то).\n");


        // c. Сортировать работников по возрасту, выбрать только тех, у
        // кого он меньше 50. Вывести на экран тех, у кого есть кот
        Stream<Worker> cStream = workerList.stream();
        List<Worker> cWorker = cStream.filter(x -> x.getAge() < 50).collect(Collectors.toList());

        List<Worker> res = cWorker.stream().filter(x -> x.getCat()!= null ).collect(Collectors.toList());
        System.out.println("c) Возраст <50 и есть кот");
        res.stream().forEach(s -> System.out.println(s.getName()+"(возраст: "+s.getAge()+"). Кот " + s.getCat().getName()));


        System.out.println();


        // d. Тоже самое, что в (c), но убрать из выборки всех, у кого меньше 50
        Stream<Worker> dStream = workerList.stream();
        List<Worker> dWorker = dStream.filter(x -> x.getAge() > 50).collect(Collectors.toList());

        List<Worker> dres = dWorker.stream().filter(x -> x.getCat()!= null ).collect(Collectors.toList());
        System.out.println("d) Возраст >50 и есть кот");
        dres.stream().forEach(s -> System.out.println(s.getName()+"(возраст: "+s.getAge()+"). Кот " + s.getCat().getName()));


        System.out.println();


        // e. Произвести выборку работников по одному отделу и для
        // всех изменить премию умножив её значение (если она больше 0) на 2.
        // Выборку собрать в коллекцию List и вывести на экран
        Stream<Worker> eStream = workerList.stream();
        String department = companies[rn.nextInt(companies.length)];
        System.out.println("e) Отдел для выборки: " + department);
        List<Worker> eWorker = eStream.filter(x -> x.getPrime() != 0 && x.getNameOfDepartment().equals(department)).collect(Collectors.toList());
        eWorker.stream().forEach(s -> s.setPrime(s.getPrime()*2));
        eWorker.stream().forEach(s -> System.out.println(s.getName() + ": " + s.getNameOfDepartment() + ". Премия: " + s.getPrime()));


        System.out.println();


        // f. Найти работника со значением суммы зарплаты + премии больше или равно 100к, вывод функции .get() произвести в
        // объект класса Optional<Character>. Если такой объект существует, то вывести на экран его и добавить после
        // суммы зарплаты+премии текст “наносек”. Если такого объекта нет, вывести на экран “Компания нищебродов”);
        Stream<Worker> fStream = workerList.stream();
        try {
            Optional<Worker> fWorker = Optional.of(fStream.filter(s -> (s.getSalary() + s.getPrime()) >= 100000).findFirst().get());
            int result = fWorker.get().getSalary() + fWorker.get().getPrime();
            System.out.println("f) " + fWorker.get().getName() + ": прибыль " + result + "$ в наносек");
        } catch (Exception e) {
            System.out.println("f) Компания нищебродов");
        }


        System.out.println();


        // g. Посчитать для каждого отдела количество работников.
        // Вывести на экран в виде таблички.
        System.out.println("g) Имя отдела\t  |   Кол-во сотрудников");
        for (var item :  companies){
            Stream<Worker> gWorker = workerList.stream();
            int x1 = (int)gWorker.filter(x -> x.getNameOfDepartment() == item).count();
            System.out.println("\t"+item + "\t |   \t\t"+ x1);
        }
    }
}
