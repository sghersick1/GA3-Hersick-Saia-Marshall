package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Playlist;

import javax.swing.tree.RowMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDao extends AbstractDao<Playlist> {
   @Override
    public void create(Playlist entity) throws SQLException{
       Connection con = getConnection();
       String sqlScript = "INSERT INTO playlist(name, date_created)" +
               "VALUES(?, ?)";

       PreparedStatement pst = con.prepareStatement(sqlScript, PreparedStatement.RETURN_GENERATED_KEYS);
       pst.setString(1, entity.getName());
       pst.setObject(2, entity.getCreationTimestamp());
       pst.executeUpdate();

       ResultSet rs = pst.getGeneratedKeys();
       if(rs.next()){
           entity.setId(rs.getInt(1));
       }

       con.close();
   }

   @Override
    public Playlist read(int ID) throws SQLException{
       Playlist playlist = new Playlist();
       Connection con = getConnection();

       String sqlScript = "SELECT * from playlist WHERE id_playlist = ?";
       PreparedStatement pst = con.prepareStatement(sqlScript);
       pst.setInt(1, ID);
       ResultSet rs = pst.executeQuery();
       if(rs.next()){
           mapRow(rs, playlist);
       }

       con.close();
       return playlist;
   }

   @Override
    public void update(Playlist entity) throws SQLException{
       Connection con = getConnection();
       String sqlScript = "UPDATE playlist " +
               "SET name = ?, date_created = ? " +
               "WHERE id_playlist = ?";

       PreparedStatement pst = con.prepareStatement(sqlScript);
       pst.setString(1, entity.getName());
       pst.setObject(2, entity.getCreationTimestamp());
       pst.setInt(3, entity.getId());
       pst.executeUpdate();
   }
   @Override
    public void delete(int ID) throws SQLException{
       Connection con = getConnection();
       String sqlScript = "DELETE FROM playlist where id_playlist = ?";
       PreparedStatement pst = con.prepareStatement(sqlScript);
       pst.setInt(1, ID);
       pst.executeUpdate();
       con.close();
   }

   @Override
    public List<Playlist> list() throws SQLException{
       List<Playlist> list = new ArrayList<>();
       Connection con = getConnection();

       String sqlScript = "SELECT * from playlist";
       ResultSet rs = con.prepareStatement(sqlScript).executeQuery();
       while(rs.next()){
           Playlist playlist = new Playlist();
           mapRow(rs, playlist);
           list.add(playlist) ;
       }

       con.close();
       return list;
   }

    /**
     * Helper function for mapping db row to playlist object
     * @param rs
     * @param playlist
     * @throws SQLException
     */
   private void mapRow(ResultSet rs, Playlist playlist) throws SQLException{
       playlist.setId(rs.getInt("id_playlist"));
       playlist.setName(rs.getString("name"));
       playlist.setCreationTimestamp(rs.getObject("date_created", Timestamp.class));
   }
}
