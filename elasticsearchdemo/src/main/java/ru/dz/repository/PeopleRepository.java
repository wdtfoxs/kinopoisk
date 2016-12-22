package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.entity.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
