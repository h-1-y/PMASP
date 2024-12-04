package h1y.my.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import h1y.my.portfolio.entity.Job;

public interface JobJpaRepository extends JpaRepository<Job, Long> {

}
