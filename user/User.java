package user;

import pages.Page;
import pages.PageManager;

import java.util.Scanner;

public class User {

    private String name, password;
    private float money;
    private UserStateEnum state;
    private Page currentPage;
    private Scanner scanner = new Scanner(System.in);

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.state = UserStateEnum.START;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public UserStateEnum getState() {
        return state;
    }

    public void setState(UserStateEnum state) {
        this.state = state;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

}
