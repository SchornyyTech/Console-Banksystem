package pages;

import user.UserManager;
import user.UserStateEnum;

import java.util.HashMap;

public class PageManager {

    private static HashMap<String, Page> storedPages;

    public PageManager() {
        storedPages = new HashMap<>();
        registerPages();
        UserManager.chancePage(UserStateEnum.START);
    }

    public static HashMap<String, Page> getStoredPages() {
        return storedPages;
    }

    private void registerPages() {
        StartPage startPage = new StartPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        HomePage homePage = new HomePage();
        DepositPage depositPage = new DepositPage();
        WithdrawPage withdrawPage = new WithdrawPage();
        infoPage infoPage = new infoPage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        getStoredPages().put(UserStateEnum.START.getPageName(), startPage);
        getStoredPages().put(UserStateEnum.LOGIN.getPageName(), loginPage);
        getStoredPages().put(UserStateEnum.REGISTER.getPageName(), registerPage);
        getStoredPages().put(UserStateEnum.HOMESCREEN.getPageName(), homePage);
        getStoredPages().put(UserStateEnum.DEPOSIT.getPageName(), depositPage);
        getStoredPages().put(UserStateEnum.WITHDRAW.getPageName(), withdrawPage);
        getStoredPages().put(UserStateEnum.INFO.getPageName(), infoPage);
        getStoredPages().put(UserStateEnum.CHANGE_PASSWORD.getPageName(), changePasswordPage);
    }
}
