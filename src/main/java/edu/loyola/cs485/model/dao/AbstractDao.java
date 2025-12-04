package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.AbstractEntity;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<E extends AbstractEntity>{
    Dotenv dotenv = Dotenv.load();

    private String ConUrl = dotenv.get("DB_URL");
    private String Port = dotenv.get("DB_PORT");
    private String Database = dotenv.get("DB_NAME");
    private String Username = dotenv.get("DB_USERNAME");
    private String Password = dotenv.get("DB_PASSWORD");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    public void setTestDatabase(){
        this.Database = "music_db_test";
    }

    // Abstract Methods for each CRUD operation
    public abstract void create(E entity) throws SQLException;
    public abstract E read(int ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(int ID) throws SQLException;
    public abstract List<E> list() throws SQLException;
}
