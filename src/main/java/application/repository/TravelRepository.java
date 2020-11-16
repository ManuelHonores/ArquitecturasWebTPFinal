package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Travel;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    
}
