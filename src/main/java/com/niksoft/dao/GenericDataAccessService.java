package com.niksoft.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.metamodel.EntityType;

public abstract class GenericDataAccessService<EntityType, PrimaryKeyType extends Serializable> {

    // Constructor

    public void save(EntityType newEntity) {
        // TODO Auto-generated method stub
    }


    public void update(EntityType entity) {
        // TODO Auto-generated method stub

    }

 
    public EntityType find(PrimaryKeyType primaryKey) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<EntityType> findByProperty(String property) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<EntityType> findAll() {
        // TODO Auto-generated method stub
        return null;
    }


    public void delete(PrimaryKeyType primaryKey) {
        // TODO Auto-generated method stub

    }

    public void delete(EntityType entity) {
        // TODO Auto-generated method stub

    }
}