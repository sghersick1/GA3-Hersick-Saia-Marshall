
package edu.loyola.cs485.view;

import edu.loyola.cs485.controller.PlaylistService;
import edu.loyola.cs485.model.entity.Playlist;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ClientCrudDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton deleteButton;
    private JButton updateButton;
    private JList lstPlaylistUI;


    public ClientCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        populateUI();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newClick();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClick();
            }
        });
    }

    private void newClick() {
        // add your code here
        ClientInfoDialog dialog = new ClientInfoDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        
        populateUI();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void deleteClick() {
        try{
            PlaylistService service = new PlaylistService();
            Playlist p = (Playlist) lstPlaylistUI.getSelectedValue();
            if (p != null) {
                service.deletePlaylist(p.getId());
                lstPlaylistUI.clearSelection();

                // Repopulate the JList to get new data
                populateUI(); // fetch everything again from the DB
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void populateUI() {
        try {
            PlaylistService service = new PlaylistService();
            List<Playlist> lstdata = service.listPlaylist();

            lstPlaylistUI.setListData( lstdata.toArray() );

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void updateClick() {
        try{
            PlaylistService service = new PlaylistService();
            Playlist p = (Playlist) lstPlaylistUI.getSelectedValue();
            if (p != null) {
                ClientInfoDialog dialog = new ClientInfoDialog(p);
                dialog.pack();
                dialog.setVisible(true);

                lstPlaylistUI.clearSelection();

                // Repopulate the JList to get new data
                populateUI(); // fetch everything again from the DB
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClientCrudDialog dialog = new ClientCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
