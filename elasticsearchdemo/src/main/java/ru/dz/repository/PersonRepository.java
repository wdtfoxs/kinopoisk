package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
}
