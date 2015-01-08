package com.niksoft.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.metamodel.EntityType;
import javax.sql.DataSource;

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
    protected  List<?> execute(String commandText, IMapped_Values imv) throws NamingException, SQLException{
 	   InitialContext ic = new InitialContext();
 		try {
 			String dsName = "java:jboss/datasources/DemoDSsqlite";
 			DataSource ds = (javax.sql.DataSource) ic.lookup(dsName);
 			Connection connection = ds.getConnection();
 			try {
 				Statement statement = connection.createStatement();
 				try {
 					ResultSet resultSet = statement.executeQuery(commandText);
 					try{
 						return imv.maptovalues(resultSet);
 					} finally {
 						resultSet.close();
 					}
 				} finally {
 					statement.close();
 				}

 			} finally {
 				connection.close();
 			}
 		} finally {
 			ic.close();
 			ic = null;
 		}
    }
    interface IMapped_Values{
 	   List<?> maptovalues(ResultSet rs) throws SQLException;
    }
}