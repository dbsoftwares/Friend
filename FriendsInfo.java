import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FriendsInfo extends JFrame implements ActionListener {

    private JPanel namePanel;
    private JLabel firstNameLabel;
    private JTextField firstNameText;
    private JLabel lastNameLabel;
    private JTextField lastNameText;
    private JPanel phonePanel;
    private JLabel phoneLabel;
    private JTextField phoneText;
    private JPanel addressPanel;
    private JLabel addressLabel;
    private JTextField addressText;
    private JPanel savePanel;
    private JButton saveButton;
    private JButton clearButton;
    private JPanel searchPanel;
    private JLabel searchFriendLabel;
    private JTextField friendNameText;
    private JButton searchButton;
    private JButton addButton;
    private FriendsList friendsList;
    private Friend friend;

    public FriendsInfo() throws IOException {
  createUI();
	initFriendsList();
    }
    private void createUI() {
	// gridLayout help taken from http://www.java2s.com/Tutorial/Java/0240__Swing/SampleGridLayoutApplication.htm
	setLayout(new GridLayout(0, 1)); 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
	setVisible(true);
	setLocationRelativeTo(null);
	// WindowAdapter and addWindowListener taken from youtube videos
	  addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent event) {
	    	saveButton.doClick();
	    }
	}); 
	// create a panel
	namePanel = new JPanel();
	namePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
	add(namePanel);
	// add first name label to panel
	firstNameLabel = new JLabel("First Name :");
	namePanel.add(firstNameLabel);  

	// add first name text box to panel
	firstNameText = new JTextField(10);
	namePanel.add(firstNameText);

	// add last name label to panel
	lastNameLabel = new JLabel("Last Name :");
	namePanel.add(lastNameLabel);

	// add last name text box to panel
	lastNameText = new JTextField(10);
	namePanel.add(lastNameText);
 
	// create panel to hold phone number
	phonePanel = new JPanel();
	phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	add(phonePanel);

	// add phone label to panel2
	phoneLabel = new JLabel("  Phone No :");
	phonePanel.add(phoneLabel);

	// add phone textbox to panel2
	phoneText = new JTextField(15);
	phonePanel.add(phoneText);

	// add new panel to hold address text box and label
	addressPanel = new JPanel();
	addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	add(addressPanel);

	// add address label to panel3
	addressLabel = new JLabel("    Address :");
	addressPanel.add(addressLabel);

	// add address textbox to panel3
	addressText = new JTextField(27);
	addressPanel.add(addressText);

	// create panel to hold save button
	savePanel = new JPanel();
	savePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	add(savePanel);

	// create button to add info
	addButton = new JButton("Add Friend");
	addButton.setBackground(Color.pink);
	// create action listener for this button
	addButton.addActionListener(this);
	savePanel.add(addButton);

	// create button to clear info from text boxes
	clearButton = new JButton("Clear Fields");
	clearButton.setBackground(Color.pink);
	// create action listener for this button
	clearButton.addActionListener(this);
	savePanel.add(clearButton);

	// create save button and add it to panel4
	saveButton = new JButton("Save To File");
	saveButton.setBackground(Color.pink);

	// create action listener for this button
	saveButton.addActionListener(this);
	savePanel.add(saveButton);

	// create panel to add search friend box and button
	searchPanel = new JPanel();
	searchPanel.setLayout(new FlowLayout());
	add(searchPanel);

	// add friend name label to panel5
	searchFriendLabel = new JLabel("Enter Friend's full name :");
	searchPanel.add(searchFriendLabel);

	// add text box to enter friend name to search
	friendNameText = new JTextField(15);
	searchPanel.add(friendNameText);

	// add search button to panel5
	searchButton = new JButton("Search");
	searchButton.setBackground(Color.pink);
	// create action listener for this button
	searchButton.addActionListener(this);
	searchPanel.add(searchButton);

	// pack all components into frame
	pack();
    }

    private void initFriendsList() throws IOException {
    	// create object of friendsList class
    	friendsList = new FriendsList();
    	fillFriendsListFromFile();
    }

    // method to read from file and fill friendlist 
    private void fillFriendsListFromFile() throws IOException {
	try {
	  
	    FileReader fr = new FileReader("friends_record.txt");
	    BufferedReader br = new BufferedReader(fr);
	    String info = br.readLine();
	    while (info != null) {
		String[] friendInfo = info.split(":");
		String firstName = friendInfo[0];
		String lastName = friendInfo[1];
		String phoneNumber = friendInfo[2];
		String address = friendInfo[3];
		Friend friendData = new Friend(firstName, lastName, phoneNumber, address);
		friendsList.addFriend(friendData);
		info = br.readLine();
	    }
	    br.close();
	    
	} catch (FileNotFoundException e) {
	// showMessageDialog read from internet
	 JOptionPane.showMessageDialog(null, e.getMessage());
	   
	}
	
    }
   // method for searchButton action 
    public  void searchButton_action() {
	String friendName = friendNameText.getText(); 
	friend = friendsList.getFriend(friendName);
	if (friend != null) {
	    String friendString = friend.toString();
	    String[] friendInfo = friendString.split(":");
	    firstNameText.setText(friendInfo[0]);
	    lastNameText.setText(friendInfo[1]);
	    phoneText.setText(friendInfo[2]);
	    addressText.setText(friendInfo[3]);
	} else {
	    // show message if friends name is not in list
	    JOptionPane.showMessageDialog(null,
		    "Sorry! Your friend is not in the list");
	}

    }

     // method for clear button action 
    public void clearButton_action() {
	// set every text box to blank to clear them
	firstNameText.setText("");
	lastNameText.setText("");
	phoneText.setText("");
	addressText.setText("");

    }

    // method for addButton action
    public void addButton_action() {
	String firstNameString = firstNameText.getText();
	String lastNameString = lastNameText.getText();
	String phoneString = phoneText.getText();
	String addressString = addressText.getText();
	Friend data = new Friend(firstNameString, lastNameString,phoneString, addressString);
	
	friendsList.addFriend(data);

	// set text boxes blank after adding friend to list
		clearButton_action();
    }

    // method for saveButton action
    public void saveButton_action() {
    	saveToFile(friendsList.getFriendsList());
    }
    // Takes a friend ArrayList as a parameter
    private void saveToFile(ArrayList<Friend> friendsList) {
    	// check if friends list is not null
    	if (friendsList != null) {
    	    try {
    		// create file object
    		File file = new File("friends_record.txt");

    		// if file doesn't exists, then create it
    		if (!file.exists()) {
    		    file.createNewFile();
    		}
    		FileWriter fw = new FileWriter(file);
    		BufferedWriter bw = new BufferedWriter(fw);
    		for (int i = 0; i < friendsList.size(); i++) {

		    bw.write(friendsList.get(i)+"\n");
		}
    		bw.close();
			//read from internet
    		JOptionPane.showMessageDialog(null,
    			"File Written Successfully.");

    	    } catch (IOException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	    }
    	}

        }


    public static void main(String[] args) throws IOException {
	new FriendsInfo();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton){
			searchButton_action();
		}else
			if(e.getSource() == clearButton){
				clearButton_action();
			}
			else
				if(e.getSource() == saveButton){
					saveButton_action();
				}
				else
					if(e.getSource() == addButton){
						addButton_action();
					}
	}

}
