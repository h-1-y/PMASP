package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import h1y.my.portfolio.entity.Job;

@Repository
public interface JobJpaRepository extends JpaRepository<Job, Long> {

	@Query("select count(*) from Job j where j.name = :name")
	int findJobNameCheck(@Param("name") String name);
	
}
