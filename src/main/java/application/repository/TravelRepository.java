package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Travel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("SELECT t FROM Travel t WHERE t.id = :id")
    public Travel getId (Long id);

    @Query("SELECT t FROM Travel t WHERE t.user.id = :id")
    public List<Travel> getTravelUserId(Long id);

}
