package com.kitabisa.app.soccer.player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query(value = "select * from player", nativeQuery = true)
    List<Player> getPlayers();
}
