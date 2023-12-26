package kz.foodorder.delivery.services;

import kz.foodorder.delivery.entities.User;
import kz.foodorder.delivery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService implements ItemService<User> {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public Boolean addItem(User user){
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean deleteItem(User user){
        try {
            userRepository.delete(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean updateItem(User user){
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public List<User> getAllItem(){
        return userRepository.findAll();
    }
    @Override
    public User getItem(Long id){

        return userRepository.getById(id);
    }
    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
