package kz.foodorder.delivery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dishes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",length = 1000)
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "url")
    private String imageUrl;

}
