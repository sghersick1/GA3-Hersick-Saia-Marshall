package edu.loyola.cs485.controller;

import edu.loyola.cs485.model.dao.PlaylistDao;
import edu.loyola.cs485.model.entity.Playlist;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class PlaylistService {

    public Playlist createPlaylist(String name, String strTimestamp) throws Exception{
        // Cast timestamp to sql.Timestamp object
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(sdf.parse(strTimestamp).getTime());

        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setCreationTimestamp(timestamp);

        PlaylistDao dao = new PlaylistDao();
        dao.create(playlist);

        return playlist;
    }

    public Playlist readPlaylist(int ID) throws Exception{
        PlaylistDao dao = new PlaylistDao();
        return dao.read(ID);
    }

    public List<Playlist> listPlaylist() throws Exception{
        PlaylistDao dao = new PlaylistDao();
        return dao.list();
    }

    public void updatePlaylist(int id, String name, String strTimestamp) throws Exception{
        // Cast timestamp to sql.Timestamp object
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(sdf.parse(strTimestamp).getTime());

        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setCreationTimestamp(timestamp);

        PlaylistDao dao = new PlaylistDao();
        dao.update(playlist);
    }

    public void deletePlaylist(int ID) throws Exception{
        PlaylistDao dao = new PlaylistDao();
        dao.delete(ID);
    }
}
