package pages;

import user.User;
import user.UserManager;
import user.UserStateEnum;

import java.util.Scanner;

public class StartPage implements Page{

    private Scanner scanner = UserManager.getUser().getScanner();
    private String userInput;
    private String[] answers = {"einlogen", "registrieren"};
    private boolean rightAnswer = false;

    @Override
    public void fallBack() {

    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public void start() {
        System.out.println("Einlogen oder Registrieren?");
        userInput = scanner.nextLine().toLowerCase();
        while (!rightAnswer) {
            String a = null;
            for(String s : answers) {
                if(s.equals(userInput)) {
                    rightAnswer = true;
                    a = userInput;
                    System.out.println("Du wirst weiter geleitet zu: " + userInput);
                }
            }

            if(a == null) {
                System.out.println("Bitte versuche es erneut!");
                System.out.println("Einlogen oder Registrieren?");
                userInput = scanner.nextLine().toLowerCase();
            }else if(a.equals(answers[0])) {
                UserManager.chancePage(UserStateEnum.LOGIN);
            } else if(a.equals(answers[1])) {
                UserManager.chancePage(UserStateEnum.REGISTER);
            }
            a = null;
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getUserInput() {
        return userInput;
    }
}
