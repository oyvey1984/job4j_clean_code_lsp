package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    void whenPrintMenuThenOutputMatches() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);


        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            Printer printer = new Printer();
            printer.print(menu);
        } finally {
            System.setOut(originalOut);
        }

        String printed = outputStream.toString().replace("\r\n", "\n").trim();

        String expected = String.join("\n",
                "1. Сходить в магазин",
                "----1.1. Купить продукты",
                "--------1.1.1. Купить хлеб"
        );

        assertThat(printed).isEqualTo(expected);
    }
}
