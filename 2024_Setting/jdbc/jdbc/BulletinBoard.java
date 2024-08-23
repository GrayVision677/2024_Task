package jdbc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BulletinBoard extends JFrame {
    private DefaultListModel<String> postListModel;
    private JList<String> postList;
    private JTextField postField;
    private JButton addButton;
    private JButton deleteButton;

    public BulletinBoard() {
        setTitle("Bulletin Board");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);
        JScrollPane scrollPane = new JScrollPane(postList);

        postField = new JTextField(20);
        addButton = new JButton("Add Post");
        deleteButton = new JButton("Delete Post");

        JPanel inputPanel = new JPanel();
        inputPanel.add(postField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = postField.getText();
                if (!postText.isEmpty()) {
                    postListModel.addElement(postText);
                    postField.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = postList.getSelectedIndex();
                if (selectedIndex != -1) {
                    postListModel.remove(selectedIndex);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
    	new BulletinBoard();
	}
}