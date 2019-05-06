package pl.polsl.repairmanagementbackend.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Transactional
    EmployeeEntity save(EmployeeEntity Employee);


    @Transactional
    List<EmployeeEntity> findAll();

    @Transactional
    Optional<EmployeeEntity> findById(Integer id);

    @Transactional
    Optional<EmployeeEntity> findByUsername(String username);
}