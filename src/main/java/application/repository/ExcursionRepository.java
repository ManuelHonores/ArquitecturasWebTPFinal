package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Excursion;

/**
 * Repository de la entidad Excursion
 * @author Grupo 10
 * @version v1.0
 */

public interface ExcursionRepository extends JpaRepository<Excursion,Long>{
    
}
