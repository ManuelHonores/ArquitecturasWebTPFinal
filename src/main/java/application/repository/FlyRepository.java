package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Fly;

public interface FlyRepository extends JpaRepository<Fly,Long>{
    
}
