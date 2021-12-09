package ua.lviv.iot.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;

@Data
@Table(name = "programs")
public class Program {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Override
    public String toString() {
        return String.format("id: %s, name: %s", id, name);
    }
}
