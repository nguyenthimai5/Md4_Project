package m.Security;

import m.Model.Entity.UserMana;
import m.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMana userMana=userRepository.findByUserName(username);
        if (userMana==null){
            throw new UsernameNotFoundException("User not found");
        }
        return CustomUserDetails.mapUserToUserDetails(userMana);
    }
}
