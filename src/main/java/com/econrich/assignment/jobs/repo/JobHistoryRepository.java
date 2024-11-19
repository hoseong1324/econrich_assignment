package com.econrich.assignment.jobs.repo;

import com.econrich.assignment.jobs.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {
    @Query("select jh from JobHistory jh " +
            "left join fetch jh.embeddableJobHistory.employees e " +
            "where e.employeeId = :employeeId")
    List<JobHistory> findAllByEmployeeId(int employeeId);
}
