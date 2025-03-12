package org.example;

import org.example.enums.MenuElements;
import static org.example.utils.MenuUtil.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                    showMenuItems();
                    System.out.print("Please select an option: ");
                    int option = scanner.nextInt();
                    MenuElements menu = getElementByValue(option);
                    menu.getStrategy().execute();
            } catch (RuntimeException _){
            }
        }
    }
}