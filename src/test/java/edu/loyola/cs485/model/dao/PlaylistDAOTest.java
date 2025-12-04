package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Playlist;
import edu.loyola.cs485.model.dao.PlaylistDAO;
import org.junit.jupiter.api.*;

import java.util.List;
import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;


public class PlaylistDAOTest {

    @Test
    public void testFake(){
        assertAll(
            () -> assertEquals(1,1)
        );
    }

    @Test
    public void testCreatePlaylist() throws Exception{
        PlaylistDAO dao = new PlaylistDAO();
        dao.setTestDatabase();

        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist.setCreationTimestamp( new Timestamp(System.currentTimeMillis()) );

        dao.create(playlist); // Method Under Test

        //clean up
        dao.delete( playlist.getID() );

        assertAll(
                () -> assertNotNull( playlist.getID() )
        );
    }

    @Test
    public void testReadPlaylist() throws Exception{
        PlaylistDAO dao = new PlaylistDAO();
        dao.setTestDatabase();

        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist.setEmail("test@email.com");

        dao.create(playlist);
        Playlist found = dao.read(playlist.getID()); //Function under test

        //clean up
        dao.delete( playlist.getID() );

        assertAll(
                () -> assertEquals(found.getID(), playlist.getID()),
                () -> assertEquals(found.getName(), playlist.getName()),
                () -> assertEquals(found.getEmail(), playlist.getEmail()),
                () -> assertEquals(found.getDob(), playlist.getDob())
        );

    }

    @Test
    public void testReadPlaylistDoesNotExist() throws Exception{
        PlaylistDAO dao = new PlaylistDAO();
        dao.setTestDatabase();

        Playlist found = dao.read(10);
        assertAll(
                () -> assertNull( found.getID() ),
                () -> assertNull( found.getName() )
        );
    }

    @Test
    public void testListPlaylist() throws Exception{
        PlaylistDAO dao = new PlaylistDAO();
        dao.setTestDatabase();

        List<Playlist> lst = dao.list();
        assertAll(
                () -> assertEquals(0, lst.size())
        );

    }
}