package org.academiadecodigo.soundpark.services;

import org.academiadecodigo.soundpark.persistence.model.Player;

public interface PlayerService {


    Player get(Integer id);

    Player save(Player player);

    Integer getScore(Integer id);

}
