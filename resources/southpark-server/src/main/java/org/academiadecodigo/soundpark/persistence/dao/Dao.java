package org.academiadecodigo.soundpark.persistence.dao;

import org.academiadecodigo.soundpark.persistence.model.Model;
import org.academiadecodigo.soundpark.persistence.model.Player;

public interface Dao<T extends Model> {

    T findById(Integer id);

    T saveOrUpdate(T modelType);

}
