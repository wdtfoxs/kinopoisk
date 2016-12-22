package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
}
