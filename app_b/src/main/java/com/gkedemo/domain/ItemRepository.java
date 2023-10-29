package com.gkedemo.domain;

import com.gkedemo.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ItemRepository extends CrudRepository<Item, UUID> {

    List<Item> findByOwnerId(final UUID ownerId);

}
