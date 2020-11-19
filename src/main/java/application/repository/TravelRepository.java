package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Travel;
import org.springframework.data.jpa.repository.Query;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("SELECT t FROM Travel t WHERE t.id = :id")
    public Travel getId (Long id);
}
