package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.Playlist;
import edu.loyola.cs485.model.dao.PlaylistDAO;
import org.junit.jupiter.api.*;

import java.util.List;

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
        ClientDAO dao = new ClientDAO();
        dao.setTestDatabase();

        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@email.com");

        dao.create(client); // Method Under Test

        //clean up
        dao.delete( client.getID() );

        assertAll(
                () -> assertNotNull( client.getID() )
        );
    }

    @Test
    public void testReadPlaylist() throws Exception{
        ClientDAO dao = new ClientDAO();
        dao.setTestDatabase();

        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@email.com");

        dao.create(client);
        Client found = dao.read(client.getID()); //Function under test

        //clean up
        dao.delete( client.getID() );

        assertAll(
                () -> assertEquals(found.getID(), client.getID()),
                () -> assertEquals(found.getName(), client.getName()),
                () -> assertEquals(found.getEmail(), client.getEmail()),
                () -> assertEquals(found.getDob(), client.getDob())
        );

    }

    @Test
    public void testReadClientDoesNotExist() throws Exception{
        ClientDAO dao = new ClientDAO();
        dao.setTestDatabase();

        Client found = dao.read(10);
        assertAll(
                () -> assertNull( found.getID() ),
                () -> assertNull( found.getName() )
        );
    }

    @Test
    public void testListClient() throws Exception{
        ClientDAO dao = new ClientDAO();
        dao.setTestDatabase();

        List<Client> lst = dao.list();
        assertAll(
                () -> assertEquals(0, lst.size())
        );

    }
}