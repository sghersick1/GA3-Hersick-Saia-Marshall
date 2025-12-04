package edu.loyola.cs485;

import edu.loyola.cs485.controller.PlaylistService;
import edu.loyola.cs485.model.entity.Playlist;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        testDao();
    }

    public static void testDao(){
        PlaylistService ps = new PlaylistService();

        try{
            // Create
            ps.createPlaylist("Flower Boy", "08/07/2005 07:41:35");

            // Read by ID
            System.out.println(ps.readPlaylist(1));

            // Read all
            List<Playlist> list = ps.listPlaylist();
            printList(list);
            Playlist newPlaylist = list.getLast();

            // Update
            ps.updatePlaylist(newPlaylist.getId(), "IGOR", "010/12/2005 07:41:35");

            // PRINT AGAIN -> updated list
            printList(ps.listPlaylist());

            // Delete last playlist
            ps.deletePlaylist(newPlaylist.getId());

            // PRINT AGAIN -> list with deletion
            printList(ps.listPlaylist());

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public static void printList(List<Playlist> list){
        System.out.println();
        list.forEach(System.out::println);
    }
}