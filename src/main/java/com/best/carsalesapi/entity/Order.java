package com.best.carsalesapi.entity;

import com.best.carsalesapi.entity.model.CarOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "order_entity")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserEntity buyer;

    @OneToOne
    private UserEntity seller;

    @OneToMany
    private Collection<Car> orderedItems;

    @Builder.Default
    private CarOrderStatus orderStatus = CarOrderStatus.PENDING;

    private Date orderDate;

    private Double hammerPrice;
}
