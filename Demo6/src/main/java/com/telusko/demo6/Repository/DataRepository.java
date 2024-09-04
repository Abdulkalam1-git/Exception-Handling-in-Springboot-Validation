package com.telusko.demo6.Repository;

import com.telusko.demo6.Model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, String> {
    @Query("SELECT d FROM Data d WHERE LOWER(d.name) = LOWER(:name)")
    public Optional<Data> findByName(@Param("name") String name);

    @Query("SELECT d FROM Data d WHERE LOWER(d.email )= LOWER(:email)")
    public Optional<Data> findByEmail(@Param("email") String email);

    public Optional<Data> findById(String id);


}
