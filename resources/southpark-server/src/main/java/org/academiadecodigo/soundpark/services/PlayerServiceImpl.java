package org.academiadecodigo.soundpark.services;

import org.academiadecodigo.soundpark.persistence.dao.PlayerDao;
import org.academiadecodigo.soundpark.persistence.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerDao playerDao;


    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player get(Integer id) {
        return playerDao.findById(id);
    }

    @Transactional
    @Override
    public Player save(Player player) {
        return playerDao.saveOrUpdate(player);
    }


    @Override
    public Integer getScore(Integer id) {

        Player player = playerDao.findById(id);

        return player.getScore();
    }
}
