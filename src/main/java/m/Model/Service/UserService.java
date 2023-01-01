package m.Model.Service;

import m.Model.Entity.UserMana;

import java.util.List;

public interface UserService<T,V>{
    UserMana findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    UserMana saveOrUpdate(UserMana userMana);
    List<T> getAll();
    T saveAndUpdate(T t);
    T findById(V id);
}
