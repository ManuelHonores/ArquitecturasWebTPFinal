package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Hotel;

/**
 * Repository de la entidad Hotel
 * @author Grupo 10
 * @version v1.0
 */

public interface HotelRepository extends JpaRepository<Hotel,Long>{
    
}