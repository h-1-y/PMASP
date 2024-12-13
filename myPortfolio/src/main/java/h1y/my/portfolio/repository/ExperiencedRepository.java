package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import h1y.my.portfolio.entity.Experienced;

@Repository
public interface ExperiencedRepository extends JpaRepository<Experienced, Long> {

	
	
}
