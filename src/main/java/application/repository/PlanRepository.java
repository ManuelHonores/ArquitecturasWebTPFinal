package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Plan;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long>{
    @Query("SELECT p FROM Plan p WHERE p.travel.id = :id")
    public List<Plan> getPlanByTravelId(long id);
}
