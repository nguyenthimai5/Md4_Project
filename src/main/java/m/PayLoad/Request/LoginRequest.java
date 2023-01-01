package m.PayLoad.Request;

public class LoginRequest {
    private String userName;
    private String passWordUs;

    public LoginRequest(String userName, String passWordUs) {
        this.userName = userName;
        this.passWordUs = passWordUs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWordUs() {
        return passWordUs;
    }

    public void setPassWordUs(String passWordUs) {
        this.passWordUs = passWordUs;
    }
}
