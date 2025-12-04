package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Playlist;
import edu.loyola.cs485.model.dao.PlaylistDao;
import org.junit.jupiter.api.*;

import java.util.List;
import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;


public class PlaylistDaoTest {

    @Test
    public void testFake(){
        assertAll(
            () -> assertEquals(1,1)
        );
    }

    @Test
    public void testCreatePlaylist() throws Exception{
        PlaylistDao dao = new PlaylistDao();
        dao.setTestDatabase();

        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist.setCreationTimestamp( new Timestamp(System.currentTimeMillis()) );

        dao.create(playlist); // Method Under Test

        //clean up
        dao.delete( playlist.getId() );

        assertAll(
                () -> assertNotNull( playlist.getId() )
        );
    }

    @Test
    public void testReadPlaylist() throws Exception{
        PlaylistDao dao = new PlaylistDao();
        dao.setTestDatabase();

        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist.setCreationTimestamp( new Timestamp(System.currentTimeMillis()) );

        dao.create(playlist);
        Playlist found = dao.read(playlist.getId()); //Function under test

        //clean up
        dao.delete( playlist.getId() );

        assertAll(
                () -> assertEquals(found.getId(), playlist.getId()),
                () -> assertEquals(found.getName(), playlist.getName()),
                () -> assertEquals(found.getCreationTimestamp(), playlist.getCreationTimestamp())
        );

    }

    @Test
    public void testReadPlaylistDoesNotExist() throws Exception{
        PlaylistDao dao = new PlaylistDao();
        dao.setTestDatabase();

        Playlist found = dao.read(10);
        assertAll(
                () -> assertNull( found.getId() ),
                () -> assertNull( found.getName() )
        );
    }

    @Test
    public void testListPlaylist() throws Exception{
        PlaylistDao dao = new PlaylistDao();
        dao.setTestDatabase();

        List<Playlist> lst = dao.list();
        assertAll(
                () -> assertEquals(0, lst.size())
        );

    }
}
