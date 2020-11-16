package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion,Long>{
    
}
