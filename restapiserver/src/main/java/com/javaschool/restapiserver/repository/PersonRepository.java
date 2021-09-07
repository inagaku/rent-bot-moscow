package com.javaschool.restapiserver.repository;

import com.javaschool.restapiserver.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Override
    List<Person> findAll();


    @Query(value = "SELECT * FROM public.persons p" +
                        "INNER JOIN public.criteries c" +
                        "ON p.crit_id = c.id " +
                    "WHERE ?1 BETWEEN c.start_value AND c.end_value " +
                        "AND c.subway LIKE \"%?2%\" ", nativeQuery = true)
    List<Person> findByCriteries(long price, String subway);

    @Override
    Optional<Person> findById(Integer integer);
}
