import java.util.ArrayList;


public class FriendsList {
    // create ArrayList object to store objects of Friend class in it
  // Arraylist ~ it stores the objects 
	// collection classes hotey hey woh object store kartey hey
    private ArrayList<Friend> friendsList = new ArrayList<Friend>();
    
    public FriendsList() {
    }

    // method to add Friend object to list
    public void addFriend(Friend friend) {
	
	friendsList.add(friend);
	
    }
    
    // method to return Friend object from list
    public Friend getFriend(String name) {
	
	for (int i = 0; i < friendsList.size(); i++ ) {
	   if (friendsList.get(i).getFullName().equalsIgnoreCase(name)) {
	       return friendsList.get(i);
	   }
	}
	return null;
    }
    
    // method to return full list of friends
    public ArrayList<Friend> getFriendsList() {
	return friendsList;
    }
}
