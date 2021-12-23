package user;

public enum UserStateEnum {

    START("start"),
    REGISTER("register"),
    LOGIN("login"),
    HOMESCREEN("homescreen"),
    CHANGE_PASSWORD("change password"),
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    INFO("info");

    private String pageName;

    UserStateEnum(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }
}
