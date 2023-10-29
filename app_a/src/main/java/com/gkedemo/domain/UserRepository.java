package com.gkedemo.domain;

import com.gkedemo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserRepository extends CrudRepository<User, UUID> {

}
