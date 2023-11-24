package com.challenge_5.challenge_5.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge_5.challenge_5.entity.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, UUID>, JpaSpecificationExecutor<User> {

}
