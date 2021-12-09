package ua.lviv.iot.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "coach")
public class Coach {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "price")
    private double price;

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, surname: %s, phone_number: %s, price: %s", id, name, surname, phoneNumber, price);
    }
}
