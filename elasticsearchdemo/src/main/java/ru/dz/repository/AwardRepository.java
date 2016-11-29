package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.entity.Award;

public interface AwardRepository  extends JpaRepository<Award,Long>{
}
