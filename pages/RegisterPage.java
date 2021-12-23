package pages;

import MySQL.MySQL;
import MySQL.MySQLAPI;
import user.User;
import user.UserManager;
import user.UserStateEnum;

import java.util.Base64;
import java.util.Scanner;

public class RegisterPage implements Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private boolean work = true;
    private String userInput;
    private int stage = 0;

    @Override
    public void fallBack() {
        UserManager.getUser().setState(UserStateEnum.START);
        UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.START.getPageName()));
        PageManager.getStoredPages().get(UserStateEnum.START.getPageName()).start();
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
        stage = 0;
    }

    @Override
    public void start() {
        System.out.println("Bitte gebe einen Namen ein!");
        userInput = scanner.nextLine().toLowerCase();
        while (work) {
            if(userInput.equals("back")) {
                fallBack();
            }
            if(MySQLAPI.userExistsInDatabase(userInput)) {
                System.out.println("Der Name existiert bereits!");
                userInput = scanner.nextLine().toLowerCase();
            } else {
                if(stage == 0) {
                    UserManager.getUser().setName(userInput);
                    System.out.println("Dein name ist nun: " + userInput);
                    System.out.println("Bitte gebe dein Passwort ein!");
                    stage = 1;
                    userInput = scanner.nextLine().toLowerCase();
                } else if(stage == 1) {
                    byte[] pwdBytes = Base64.getEncoder().encode(userInput.getBytes());
                    String decodedString = new String(pwdBytes);
                    UserManager.getUser().setPassword(decodedString);
                    System.out.println("Dein Passwort ist: " + decodedString);
                    work = false;
                    UserManager.getUser().setState(UserStateEnum.HOMESCREEN);
                    MySQLAPI.addPlayerToDatabase(UserManager.getUser());
                    stage = 0;
                    UserManager.chancePage(UserStateEnum.HOMESCREEN);
                }
            }
        }

    }
}
