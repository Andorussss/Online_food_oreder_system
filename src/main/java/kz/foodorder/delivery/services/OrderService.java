package kz.foodorder.delivery.services;

import kz.foodorder.delivery.entities.Order;
import kz.foodorder.delivery.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ItemService<Order> {
    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    @Override
    public Boolean addItem(Order order){
        try {
            orderRepository.save(order);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean deleteItem(Order order){
        try {
            orderRepository.delete(order);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public Boolean updateItem(Order order){
        try {
            orderRepository.save(order);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Order> getAllItem(){
        return orderRepository.findAll();
    }
    @Override
    public Order getItem(Long id){

        return orderRepository.getById(id);
    }

    public List<Order> getByUser_UserId(Long id){

        return orderRepository.findAllByUser_UserId(id);
    }
    public List<Order> getByDish_id(Long id){

        return orderRepository.findAllByDish_Id(id);
    }
}
