package pl.polsl.repairmanagementbackend.address;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {


    @Transactional
    public AddressEntity save(AddressEntity address);


    @Transactional
    List<AddressEntity> findAll();

    @Transactional
    AddressEntity findById(int id);

}