package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan,Long>{
    
}
