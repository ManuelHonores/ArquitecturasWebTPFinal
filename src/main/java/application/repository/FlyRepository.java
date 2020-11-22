package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Fly;

/**
 * Repository de la entidad Fly
 * @author Grupo 10
 * @version v1.0
 */

public interface FlyRepository extends JpaRepository<Fly,Long>{
    
}
