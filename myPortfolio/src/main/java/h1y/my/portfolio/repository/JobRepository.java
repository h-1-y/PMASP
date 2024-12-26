package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import h1y.my.portfolio.dto.JobResponseDto;
import h1y.my.portfolio.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {

	@Query("select count(*) from Job j where j.name = :name")
	int findJobNameCheck(@Param("name") String name);
	
	@Query("select new h1y.my.portfolio.dto.JobResponseDto(j.name) from Job j where j.id = :id")
	JobResponseDto getJob(@Param("id") Long id);
	
}
