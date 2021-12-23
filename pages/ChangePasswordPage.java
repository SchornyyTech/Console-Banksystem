package pages;

import MySQL.MySQLAPI;
import user.UserManager;
import user.UserStateEnum;

import java.util.Base64;
import java.util.Scanner;

public class ChangePasswordPage implements Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private String userInput;
    private boolean work = true;
    private int state = 0;

    @Override
    public void fallBack() {
        UserManager.chancePage(UserStateEnum.HOMESCREEN);
        state = 0;
    }

    @Override
    public void start() {
        System.out.println("Bitte gebe dein Passwort ein!");
        userInput = scanner.nextLine().toLowerCase();

        while (work) {
            byte[] pwd = Base64.getDecoder().decode(MySQLAPI.getStringFromDataBase(UserManager.getUser()));
            String password = new String(pwd);
            if(state == 0) {
                if(userInput.equals(password)) {
                    System.out.println("Bitte gebe dein neues Passwort ein!");
                    state = 1;
                } else {
                    System.out.println("Das Passwort ist ungültig!");
                }
                userInput = scanner.nextLine().toLowerCase();
            } else if(state == 1) {
                byte[] pwdBytes = Base64.getEncoder().encode(userInput.getBytes());
                String decodedString = new String(pwdBytes);
                UserManager.getUser().setPassword(decodedString);
                System.out.println("Dein neues Passwort ist nun: " + userInput);
                work = false;
            }
        }
    }
}
