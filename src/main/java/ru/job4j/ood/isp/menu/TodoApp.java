package ru.job4j.ood.isp.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION  = () -> System.out.println("Some action");

    private void showMenu(List<String> actions) {
        System.out.println("Меню:");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index));
        }
        System.out.print("Введите номер пункта: ");
    }

    public static void main(String[] args) {
        SimpleMenu simpleMenu = new SimpleMenu();
        List<String> list =  Arrays.asList(
                "Добавить элемент в корень",
                "Добавить элемент к родителю",
                "Вывести меню в консоль",
                "Вызвать действие пункта меню",
                "Выход");
        TodoApp todoApp = new TodoApp();
        MenuPrinter menuPrinter = new Printer();
        Scanner scanner = new Scanner((System.in));
        boolean run = true;
        while (run) {
            todoApp.showMenu(list);

            String userInput = scanner.nextLine();
            int select;
            try {
                select = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число!");
                continue;
            }
            if (select < 0 || select >= list.size()) {
                System.out.println("Вы можете выбрать цифру от 1 до " + (list.size() - 1));
                continue;
            }
            if (select == 0) {
                System.out.println("Введите название пункта:");
                String itemName = scanner.nextLine();
                simpleMenu.add(null, itemName, DEFAULT_ACTION);
                continue;
            }
            if (select == 1) {
                System.out.println("Введите название родителя:");
                String parentName = scanner.nextLine();
                System.out.println("Введите название пункта:");
                String childName = scanner.nextLine();
                simpleMenu.add(parentName, childName, DEFAULT_ACTION);
                continue;
            }
            if (select == 2) {
                menuPrinter.print(simpleMenu);
                continue;
            }
            if (select == 3) {
                System.out.println("Введите название:");
                String itemName = scanner.nextLine();
                simpleMenu.select(itemName)
                        .ifPresent(item -> item.getActionDelegate().delegate());
                continue;
            }
            if (select == 4) {
                run = false;
            }
        }
    }
}
