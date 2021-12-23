package pages;

import MySQL.MySQLAPI;
import user.UserManager;
import user.UserStateEnum;

import java.util.Scanner;

public class DepositPage implements Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private boolean work = true;
    private String userInput;

    @Override
    public void fallBack() {
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
    }

    @Override
    public void start() {
        System.out.println("Gebe einen Betrag ein!");
        userInput = scanner.nextLine().toLowerCase();
        while (work) {
            if(userInput.equals("back")) {
                work = false;
                fallBack();
                UserManager.chancePage(UserStateEnum.HOMESCREEN);
            } else {
                int amount;
                try {
                    amount = Integer.valueOf(userInput);
                    MySQLAPI.setIntegerAtDatabase(UserManager.getUser(), (MySQLAPI.getIntegerFromDatabase(UserManager.getUser(), "Money") + amount));
                    System.out.println("Du hast " + amount + "€ eingezahlt!");
                    work = false;
                } catch (NumberFormatException e) {
                    System.out.println("Gebe einen Betrag ein!");
                    userInput = scanner.nextLine().toLowerCase();
                }
            }
        }
    }
}
