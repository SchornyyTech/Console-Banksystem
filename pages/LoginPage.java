package pages;

import MySQL.MySQLAPI;
import user.UserManager;
import user.UserStateEnum;

import java.util.Base64;
import java.util.Scanner;

public class LoginPage implements  Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private String userInput;
    private boolean work = true;
    private int state = 0;

    @Override
    public void fallBack() {
        UserManager.getUser().setState(UserStateEnum.START);
        UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.START.getPageName()));
        PageManager.getStoredPages().get(UserStateEnum.START.getPageName()).start();
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
        state = 0;
    }


    @Override
    public void start() {
        System.out.println("Bitte gebe deinen Namen ein!");
        userInput = scanner.nextLine().toLowerCase();
        while (work) {
            if(userInput.equals("back")) {
                fallBack();
            } else {
                if(MySQLAPI.userExistsInDatabase(userInput)) {
                    state = 1;
                    System.out.println("Bitte gebe dein Passwort ein!");
                    UserManager.getUser().setName(userInput);
                    userInput = scanner.nextLine().toLowerCase();
                    if(state == 1) {
                        byte[] pwd = Base64.getDecoder().decode(MySQLAPI.getStringFromDataBase(UserManager.getUser()));
                        String password = new String(pwd);
                        if(password.equals(userInput)) {
                            UserManager.chancePage(UserStateEnum.HOMESCREEN);
                            work = false;
                        } else {
                            System.out.println("Das Passwort ist ungültig, bitte versuche es erneut!");
                            userInput = scanner.nextLine().toLowerCase();
                        }
                    }
                } else {
                    System.out.println("Der Name existiert nicht!");
                    userInput = scanner.nextLine().toLowerCase();
                }
            }
        }
    }
}
