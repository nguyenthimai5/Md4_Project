package m.PayLoad.Request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SignupRequest {
    private String userName;
    private String passWordUs;
    private String email;
    private String phone;
    private String address;
    private LocalDate createDate;
    private boolean userStatus;

    private List<String> listRoles;

    public SignupRequest(String userName, String passWordUs, String email, String phone, String address, LocalDate createDate, boolean userStatus, List<String> listRoles) {
        this.userName = userName;
        this.passWordUs = passWordUs;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.userStatus = userStatus;
        this.listRoles = listRoles;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
