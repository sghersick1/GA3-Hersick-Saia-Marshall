package edu.loyola.cs485.view;

import edu.loyola.cs485.model.entity.Playlist;
import edu.loyola.cs485.controller.PlaylistService;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.*;

public class ClientInfoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtName;
    private Playlist existingPlaylist;

    public ClientInfoDialog(Playlist existingPlaylist) {
        this.existingPlaylist = existingPlaylist;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        if (this.existingPlaylist != null) {
            txtName.setText(this.existingPlaylist.getName());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
    }

    private void onOK() {
        // add your code here
        PlaylistService service = new PlaylistService();
        String name = txtName.getText();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String strTimestamp = sdf.format(new Date());

            if(this.existingPlaylist != null) {
                String existingTimestamp = sdf.format(this.existingPlaylist.getCreationTimestamp());
                service.updatePlaylist(this.existingPlaylist.getId(), name, existingTimestamp);
            } else {
                service.createPlaylist(name, strTimestamp);
            }
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ClientInfoDialog dialog = new ClientInfoDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
