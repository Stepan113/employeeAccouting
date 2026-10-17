package org.lab.employeeaccouting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    interface PostCount {
        PostType getPost();

        Long getTotal();
    }

    @Query("""
            select e.post as post, count(e) as total
            from Employee e
            where e.reasonDismissal is null
            group by e.post
            """)
    List<PostCount> countActiveByPost();

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPhoneNumber(String number);

    Page<Employee> findByPost(PostType type, Pageable pageable);
}
