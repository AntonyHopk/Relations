package org.example.relations.repositories;

import org.example.relations.entity.Hobby;
import org.example.relations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    Optional<Hobby> findByName(String name);

}
