package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Playlist;

import java.sql.SQLException;
import java.util.List;

public class PlaylistDao extends AbstractDao<Playlist> {
   @Override
    public void create(Playlist entity) throws SQLException{};
   @Override
    public Playlist read(int ID) throws SQLException{return null;};
   @Override
    public void update(Playlist entity) throws SQLException{};
   @Override
    public void delete(int ID) throws SQLException{};
   @Override
    public List<Playlist> list() throws SQLException{return null;};
}
