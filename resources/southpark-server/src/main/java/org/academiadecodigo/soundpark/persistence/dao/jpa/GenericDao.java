package org.academiadecodigo.soundpark.persistence.dao.jpa;

import org.academiadecodigo.soundpark.persistence.dao.Dao;
import org.academiadecodigo.soundpark.persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDao<T extends Model> implements Dao<T>{

    protected Class<T> modelType;

    @PersistenceContext
    protected EntityManager em;

    public GenericDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @see Dao#findById(Integer)
     */
    @Override
    public T findById(Integer id) {
        return em.find(modelType, id);
    }

    /**
     * @see Dao#saveOrUpdate(Model)
     * @param modelObject
     */
    @Override
    public T saveOrUpdate(T modelObject){return em.merge(modelObject);}
}
