package kz.foodorder.delivery.services;

import java.util.List;
import java.util.Optional;

public interface ItemService<T>{
    Boolean addItem(T item);
    List<T> getAllItem();
    T getItem(Long id);

    Boolean deleteItem(T item);
    Boolean updateItem(T item);

}
