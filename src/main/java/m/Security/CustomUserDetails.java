package m.Security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m.Model.Entity.UserMana;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private int userId;
    private String userName;
    @JsonIgnore
    private String passWordUs;
    private String email;
    private String phone;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate=LocalDate.now();
    private boolean userStatus;
    private String address;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public  static CustomUserDetails mapUserToUserDetails(UserMana userMana){
        List<GrantedAuthority> authorityList=userMana.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                .collect(Collectors.toList());
        return new CustomUserDetails(
                userMana.getUserId(),
                userMana.getUserName(),
                userMana.getPassWordUs(),
                userMana.getEmail(),
                userMana.getPhone(),
                userMana.getCreateDate(),
                userMana.isUserStatus(),
                userMana.getAddress(),
                authorityList
                );
    }

    @Override
    public String getPassword() {
        return this.passWordUs;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
