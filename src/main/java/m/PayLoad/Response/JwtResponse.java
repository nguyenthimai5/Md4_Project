package m.PayLoad.Response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String userName;
    private String email;
    private String phone;
    private String address;
    private List<String> listRoles;

    public JwtResponse(String token, String userName, String email, String phone, String address, List<String> listRoles) {
        this.token = token;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.listRoles = listRoles;
    }

    public JwtResponse(String token, String type, String userName, String email, String phone, String address, List<String> listRoles) {
        this.token = token;
        this.type = type;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.listRoles = listRoles;
    }

    public JwtResponse(String jwt, String username, String email, String phone, List<String> listRoles) {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
