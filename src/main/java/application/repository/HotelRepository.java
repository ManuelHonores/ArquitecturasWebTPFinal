package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long>{
    
}