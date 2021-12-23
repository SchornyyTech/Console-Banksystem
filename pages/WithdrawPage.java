package pages;

import MySQL.MySQLAPI;
import user.User;
import user.UserManager;
import user.UserStateEnum;

import java.util.Locale;
import java.util.Scanner;

public class WithdrawPage implements Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private String userInput;
    private boolean work = true;

    @Override
    public void fallBack() {
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
    }

    @Override
    public void start() {
        System.out.println("Gebe ein Betrag ein!");
        userInput = scanner.nextLine().toLowerCase();
        while (work) {
            if(userInput.equals("back")) {
                work = false;
                fallBack();
                UserManager.chancePage(UserStateEnum.HOMESCREEN);
            } else {
                int amount = 0;
                try {
                    amount = Integer.valueOf(userInput);
                }catch (NumberFormatException e) {
                    System.out.println("Gebe einen Betrag ein!");
                    userInput = scanner.nextLine().toLowerCase();
                }
                
                if(amount > MySQLAPI.getIntegerFromDatabase(UserManager.getUser(), "Money")) {
                    System.out.println("Dein Kontostand beträgt: " + MySQLAPI.getIntegerFromDatabase(UserManager.getUser(), "Money") + "€");
                    userInput = scanner.nextLine().toLowerCase();
                } else {
                    int nextamount = MySQLAPI.getIntegerFromDatabase(UserManager.getUser(), "Money") - amount;
                    MySQLAPI.setIntegerAtDatabase(UserManager.getUser(), nextamount);
                    System.out.println("Dein Kontostand beträgt nun: " + nextamount + "€");
                    work = false;
                }
            }
        }
    }
}
