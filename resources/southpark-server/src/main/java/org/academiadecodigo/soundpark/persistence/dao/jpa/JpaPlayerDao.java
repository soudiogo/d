package org.academiadecodigo.soundpark.persistence.dao.jpa;

import org.academiadecodigo.soundpark.persistence.dao.Dao;
import org.academiadecodigo.soundpark.persistence.dao.PlayerDao;
import org.academiadecodigo.soundpark.persistence.model.Player;
import org.springframework.stereotype.Repository;


@Repository
public class JpaPlayerDao extends GenericDao<Player> implements PlayerDao {


    public JpaPlayerDao() {
        super(Player.class);
    }
}
