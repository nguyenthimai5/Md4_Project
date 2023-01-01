package m.Model.ServiceImp;

import m.Model.Entity.UserMana;
import m.Model.Repository.UserRepository;
import m.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService<UserMana,Integer> {
@Autowired
private UserRepository userRepository;
    @Override
    public UserMana findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserMana saveOrUpdate(UserMana userMana) {
        return userRepository.save(userMana);
    }

    @Override
    public List getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserMana saveAndUpdate(UserMana userMana) {
        return userRepository.save(userMana);
    }

    @Override
    public UserMana findById(Integer id) {
        return userRepository.findById(id).get();
    }


}
