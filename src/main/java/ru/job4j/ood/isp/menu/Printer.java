package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            int level = itemInfo.getNumber().split("\\.").length - 1;
            String indent = "-".repeat(level * 4);
            System.out.println(indent + itemInfo.getNumber() + " " + itemInfo.getName());
        }
    }
}
