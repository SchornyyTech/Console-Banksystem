package user;

import pages.PageManager;

public class UserManager {

    private static User user;

    public UserManager() {
        this.user = new User(null,null);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }

    public static void chancePage(UserStateEnum nextState) {
        switch (nextState) {
            case START:
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.START.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.START.getPageName()).start();
                break;
            case LOGIN:
                UserManager.getUser().setState(UserStateEnum.LOGIN);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.LOGIN.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.LOGIN.getPageName()).start();
                break;
            case REGISTER:
                UserManager.getUser().setState(UserStateEnum.REGISTER);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.REGISTER.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.REGISTER.getPageName()).start();
                break;
            case HOMESCREEN:
                UserManager.getUser().setState(UserStateEnum.HOMESCREEN);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.HOMESCREEN.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.HOMESCREEN.getPageName()).start();
                break;
            case DEPOSIT:
                UserManager.getUser().setState(UserStateEnum.DEPOSIT);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.DEPOSIT.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.DEPOSIT.getPageName()).start();
                break;
            case WITHDRAW:
                UserManager.getUser().setState(UserStateEnum.WITHDRAW);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.WITHDRAW.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.WITHDRAW.getPageName()).start();
                break;
            case INFO:
                UserManager.getUser().setState(UserStateEnum.INFO);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.INFO.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.INFO.getPageName()).start();
                break;
            case CHANGE_PASSWORD:
                UserManager.getUser().setState(UserStateEnum.CHANGE_PASSWORD);
                UserManager.getUser().setCurrentPage(PageManager.getStoredPages().get(UserStateEnum.CHANGE_PASSWORD.getPageName()));
                PageManager.getStoredPages().get(UserStateEnum.CHANGE_PASSWORD.getPageName()).start();
                break;
            default:
                System.out.println("Es ist ein Fehler aufgetreten!");
                break;
        }
    }
}
