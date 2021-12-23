import MySQL.MySQL;
import pages.Page;
import pages.PageManager;
import user.User;
import user.UserManager;
import user.UserStateEnum;

public class ConsoleBankSystem {

    private static PageManager pageManager;
    private static UserManager userManager;

    public static void main(String[] args) {
        MySQL.connect();
        userManager = new UserManager();
        pageManager = new PageManager();
    }
}
