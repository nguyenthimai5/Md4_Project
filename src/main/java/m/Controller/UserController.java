package m.Controller;

import m.Jwt.JwtTokenProvider;
import m.Model.Entity.ERole;
import m.Model.Entity.Roles;
import m.Model.Entity.UserMana;
import m.Model.Service.RoleService;
import m.Model.Service.UserService;
import m.PayLoad.Request.LoginRequest;
import m.PayLoad.Request.SignupRequest;
import m.PayLoad.Response.JwtResponse;
import m.PayLoad.Response.MessageResponse;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/m/auth/UserController")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService<UserMana, Integer> userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userService.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error:Tên đăng nhập đã tồn tại!"));
        }
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error:Email đăng nhập đã tồn tại"));
        }
        UserMana userMana = new UserMana();
        userMana.setUserName(signupRequest.getUserName());
        userMana.setPassWordUs(passwordEncoder.encode(signupRequest.getPassWordUs()));
        userMana.setEmail(signupRequest.getEmail());
        userMana.setPhone(signupRequest.getPhone());
        userMana.setAddress(signupRequest.getAddress());
        userMana.setUserStatus(true);
        userMana.setCreateDate(LocalDate.now());
        List<String> strRoles = signupRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (strRoles == null) {
            Roles userRoles = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(
                    () -> new RuntimeException("Error:Không tìm thấy quyền!"));
            listRoles.add(userRoles);
        } else {
            strRoles.forEach(role -> {
                        switch (role) {
                            case "admin":
                                Roles adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error:Không tìm thấy quyền!"));
                                listRoles.add(adminRole);
                            case "moderator":
                                Roles modRole = roleService.findByRoleName(ERole.ROLE_MODERATOR)
                                        .orElseThrow(() -> new RuntimeException("Error:Không tìm thấy quyền!"));
                                listRoles.add(modRole);
                            case "user":
                                Roles userRole = roleService.findByRoleName(ERole.ROLE_USER)
                                        .orElseThrow(() -> new RuntimeException("Error:Không tìm thấy quyền!"));
                                listRoles.add(userRole);
                        }
                    }
            );
        }
        userMana.setListRoles(listRoles);
        userService.saveOrUpdate(userMana);
        return ResponseEntity.ok(new MessageResponse("Đăng ký thành công!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWordUs())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
        if (customUserDetail.isUserStatus()) {
            String jwt = tokenProvider.generateToken(customUserDetail);
            List<String> listRoles = customUserDetail.getAuthorities().stream()
                    .map(item -> item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, customUserDetail.getUsername(), customUserDetail.getEmail(),
                    customUserDetail.getPhone(), customUserDetail.getAddress(), listRoles));
        } else {
            return ResponseEntity.ok("Tài khoản của bạn đang bị khoá vì có những hành vi ảnh hưởng đến cộng đồng!");
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<UserMana> getAllUserMana() {
        return userService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public UserMana createUser(@RequestBody UserMana userMana) {
        return userService.saveOrUpdate(userMana);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public UserMana updateUser(@PathVariable("userId") int userId, @RequestBody UserMana userMana) {
        UserMana userManaUp = userService.findById(userId);
        userManaUp.setAddress(userMana.getAddress());
        userManaUp.setUserName(userMana.getUserName());
        userManaUp.setEmail(userMana.getEmail());
        userManaUp.setCreateDate(LocalDate.now());
        userManaUp.setPhone(userMana.getPhone());
        userManaUp.setUserStatus(true);
        return userService.saveOrUpdate(userManaUp);
    }

    @DeleteMapping("/blockAccount/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> blockModorUser(@PathVariable("userId") int userId) {
        UserMana userManaUp = userService.findById(userId);
        if (userManaUp.getListRoles().size() == 3) {
            return ResponseEntity.ok("Không thể khoá tài khoản này");
        } else {
            userManaUp.setAddress(userManaUp.getAddress());
            userManaUp.setUserName(userManaUp.getUserName());
            userManaUp.setPassWordUs(userManaUp.getPassWordUs());
            userManaUp.setEmail(userManaUp.getEmail());
            userManaUp.setCreateDate(LocalDate.now());
            userManaUp.setPhone(userManaUp.getPhone());
            userManaUp.setUserStatus(false);
            return ResponseEntity.ok(userService.saveOrUpdate(userManaUp));
        }

    }

    @PostMapping("/ChangePassword")
    public ResponseEntity<?> ChangePasswordUs(@RequestParam("passWordUs") String passWordUs, @RequestParam("newPassWord") String newPassWord) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserMana users = userService.findByUserName(usersChangePass.getUsername());
        UserMana userOld=userService.findById(users.getUserId());
        String passOld=passwordEncoder.encode(passWordUs);
        if (users.getPassWordUs() == passOld) {
            userOld.setPassWordUs(passwordEncoder.encode(newPassWord));
            userService.saveOrUpdate(userOld);
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        } else {
            return ResponseEntity.ok("Sai mật khẩu!");
        }
    }
}
