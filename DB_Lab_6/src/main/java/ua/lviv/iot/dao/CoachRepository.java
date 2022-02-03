package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.entity.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
