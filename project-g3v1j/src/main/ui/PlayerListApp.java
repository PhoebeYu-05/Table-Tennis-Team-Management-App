package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

// References: ListDemo at https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Main application class that combines the ListDisplay with a background image
public class PlayerListApp extends JFrame implements ListSelectionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField playerName;

    private static final String ADD_STRING = "Add";
    private static final String REMOVE_STRING = "Remove";

    @SuppressWarnings("methodlength")
    public PlayerListApp() {
        // Set up the main window
        setTitle("Player List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Load the background image using ResourceManager
        ImageIcon backgroundImage 
                = ResourceManager.loadImage("ui/CartoonPlayers.jpg"); 

        JLabel label = new JLabel();
        label.setIcon(backgroundImage);

        // Create the list model and list
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        // Create text field and buttons
        playerName = new JTextField(10);
        JButton addButton = new JButton(ADD_STRING);
        addButton.addActionListener(new AddListener(addButton));
        removeButton = new JButton(REMOVE_STRING);
        removeButton.addActionListener(new RemoveListener());
        removeButton.setEnabled(false); // Initially disabled

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton("Load");
        loadButton.addActionListener(new LoadListener());

        // Layout for buttons and text field
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(removeButton);
        buttonPane.add(playerName);
        buttonPane.add(addButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);


        // Create a JLabel for the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(listScrollPane, BorderLayout.EAST);
        backgroundLabel.add(buttonPane, BorderLayout.SOUTH);
        backgroundLabel.add(label, BorderLayout.WEST);

        //上一行前面
        // Add the background label to the frame
        getContentPane().add(backgroundLabel);
    }

    // Represents a class that removes player from a list
    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            // Enable remove button logic
            if (listModel.getSize() == 0) {
                removeButton.setEnabled(false);
            } else {
                // Adjust selection
                if (index == listModel.getSize()) {
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // Represents a class that adds player to a list
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: performs the action of adding player into the list if not already in
        //          otherwise, do nothing
        public void actionPerformed(ActionEvent e) {
            String name = playerName.getText();
            if (name.isEmpty() || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                playerName.requestFocusInWindow();
                playerName.selectAll();
                return;
            }

            // Select the player
            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            listModel.insertElementAt(playerName.getText(), index);
            playerName.setText("");
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: returns true if the player is in the list
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // EFFECTS:  enabling the button's function to insert player
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: enable the function of remove player
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: if text field not empty, enable the button
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: enable the button 
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: returns true if the text field is empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() == 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: enable the removeButton if there is an item selected in the list,
    //          otherwise disable it
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            removeButton.setEnabled(list.getSelectedIndex() >= 0);
        }
    }

    // Listener for saving the player list to a file
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("playerlist.txt"))) {
                for (int i = 0; i < listModel.size(); i++) {
                    writer.write(listModel.getElementAt(i));
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Player list saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving player list: " + ex.getMessage());
            }
        }
    }

    // Listener for loading the player list from a file
    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try (BufferedReader reader = new BufferedReader(new FileReader("playerlist.txt"))) {
                listModel.clear(); // Clear current list
                String line;
                while ((line = reader.readLine()) != null) {
                    listModel.addElement(line);
                }
                JOptionPane.showMessageDialog(null, "Player list loaded successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading player list: " + ex.getMessage());
            }
        }
    }

    // EFFECTS: create and display a GUI
    private static void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {    // lambda expression: parameter + function body
            PlayerListApp app = new PlayerListApp();
            app.setVisible(true);
        });
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
