package pages;

import MySQL.MySQLAPI;
import user.UserManager;
import user.UserStateEnum;

import java.util.Scanner;

public class infoPage implements Page{

    @Override
    public void fallBack() {
        StartPage startPage = (StartPage) PageManager.getStoredPages().get(UserStateEnum.START.getPageName());
        startPage.setRightAnswer(false);
    }

    @Override
    public void start() {
        System.out.println("Dein Kontostand beträgt: "  + MySQLAPI.getIntegerFromDatabase(UserManager.getUser(), "Money") + "€");

    }
}
