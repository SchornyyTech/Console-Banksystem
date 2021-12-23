package pages;

import user.UserManager;
import user.UserStateEnum;

import java.util.Arrays;
import java.util.Scanner;

public class HomePage implements  Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private String userInput;
    private String[] answers = {"einzahlen", "auszahlen", "info", "passwort ändern"};
    private boolean work = true;

    @Override
    public void fallBack() {
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
        work = false;
    }

    private String getAnswersToString() {
        String s = "";
        int count = 0;
        for(String s1 : answers) {
            if(count == 0) {
                s += "" + s1;
            } else {
                s += ", " + s1;
            }
            count++;
        }
        return s;
    }

    @Override
    public void start() {
        System.out.println("Hallo, " + UserManager.getUser().getName() + "!");
        System.out.println("Wähle eine Option (" + getAnswersToString() + ")");
        userInput = scanner.nextLine().toLowerCase();

        while (work) {

            switch (userInput) {
                case "einzahlen":
                    work = false;
                    UserManager.chancePage(UserStateEnum.DEPOSIT);
                    break;
                case "auszahlen":
                    work = false;
                    UserManager.chancePage(UserStateEnum.WITHDRAW);
                    break;
                case "back":
                    fallBack();
                    UserManager.chancePage(UserStateEnum.START);
                    break;
                case "info":
                    work = false;
                    UserManager.chancePage(UserStateEnum.INFO);
                    break;
                case "passwort ändern":
                    work = false;
                    UserManager.chancePage(UserStateEnum.CHANGE_PASSWORD);
                    break;
                case "exit":
                    work = false;
                    System.out.println("Auf wieder sehen!");
                    break;
                default:
                    System.out.println("Wähle eine Option (" + getAnswersToString() + ")");
                    userInput = scanner.nextLine().toLowerCase();
                    break;
            }
        }
    }
}
