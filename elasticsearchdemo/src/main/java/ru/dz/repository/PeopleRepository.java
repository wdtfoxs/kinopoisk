package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dz.entity.People;

public interface PeopleRepository extends CrudRepository<People,Long> {

}
