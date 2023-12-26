package kz.foodorder.delivery.services;

import kz.foodorder.delivery.entities.Dish;
import kz.foodorder.delivery.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DishService implements ItemService<Dish> {
    @Autowired
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository){
        this.dishRepository=dishRepository;
    }
    @Override
    public Boolean addItem(Dish dish){
        try {
            dishRepository.save(dish);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean deleteItem(Dish dish){
        try {
            dishRepository.delete(dish);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean updateItem(Dish dish){
        try {
            dishRepository.save(dish);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Dish> getAllItem(){
        return dishRepository.findAll();
    }
    @Override
    public Dish getItem(Long id){
        return dishRepository.getById(id);
    }
}
