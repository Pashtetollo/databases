package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.entity.Exersice;

@Repository
public interface ExersiceRepository extends JpaRepository<Exersice, Integer> {
}
