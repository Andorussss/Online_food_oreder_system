package kz.foodorder.delivery.services;

import kz.foodorder.delivery.entities.Role;
import kz.foodorder.delivery.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements ItemService<Role>{
    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    @Override
    public Boolean addItem(Role role){
        try {
            roleRepository.save(role);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean deleteItem(Role role){
        try {
            roleRepository.delete(role);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean updateItem(Role role){
        try {
            roleRepository.save(role);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Role> getAllItem(){
        return roleRepository.findAll();
    }
    @Override
    public Role getItem(Long id){
        return roleRepository.getById(id);
    }
}
