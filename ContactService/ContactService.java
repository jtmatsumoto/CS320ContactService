import java.util.ArrayList;
// Implementation of the ContactService class, which has knowledge of
// a collection of contacts and organizes them by ID
public class ContactService {
    // Note - we use a simple ArrayList for our contacts, so lookup
    // is linear time. If performance must be better, we could use a map from
    // ID to contacts for constant time lookup
    private ArrayList<Contact> contactList;

    /**
     * Simple constructor for ContactService to initialize an empty ArrayList
     * of Contact objects.
     */
    public ContactService() {
        contactList = new ArrayList<Contact>();
    }

    /**
     * A simple constructor that creates the list and inserts an input contact into it.
     * @param contact
     */
    public ContactService(Contact contact) {
        contactList = new ArrayList<Contact>();
        contactList.add(contact);
    }
    /**
     * Function to allow one to insert a new contact with all of the required fields
     * @param contact - the new contact to place in the list
     * @return - true if new contact inserted, false if repeat found
     */
    public boolean insertContact(Contact contact) {
        // First, walk through our list and verify that the given inputId doesn't exist
        if(retrieveContact(contact.getId()) != null) {
            return false;
        }

        // We did not find the ID anywhere, so just insert at the end of our arrayList
        contactList.add(contact);
        return true;
    }

    /**
     * Function to remove a given Contact with the matching id from our list
     * @param id - The id to look up and remove
     * @return - true if contact removed, false otherwise
     */
    public boolean removeContact(String id) {
        Contact retrieved = retrieveContact(id);
        if (retrieved != null) {
            // We can remove this contact
            contactList.remove(retrieved);
            return true;
        }

        // Did not find a matching id to remove
        return false;
    }

    /**
     * Helper to update a contact's firstName
     * @param id - id to lookup
     * @param firstName - firstName to use
     * @return - true if success, false otherwise
     */
    public boolean updateFirstName(String id, String firstName) {
        Contact retrieved = retrieveContact(id);
        if(retrieved != null) {
            retrieved.setFirstName(firstName);
            return true;
        }

        return false;
    }

    /**
     * Helper to update a contact's lastName
     * @param id - id to lookup
     * @param lastName - lastName to use
     * @return - true if success, false otherwise
     */
    public boolean updateLastName(String id, String lastName) {
        Contact retrieved = retrieveContact(id);
        if(retrieved != null) {
            retrieved.setLastName(lastName);
            return true;
        }

        return false;
    }

    /**
     * Helper to update a contact's phone number
     * @param id - id to lookup
     * @param phoneNumber - phoneNumber to use
     * @return - true if success, false otherwise
     */
    public boolean updatePhoneNumber(String id, String phoneNumber) {
        Contact retrieved = retrieveContact(id);
        if(retrieved != null) {
            retrieved.setPhoneNumber(phoneNumber);
            return true;
        }

        return false;
    }

    /**
     * Helper to update a contact's address
     * @param id - id to lookup
     * @param address - address to use
     * @return - true if success, false otherwise
     */
    public boolean updateAddress(String id, String address) {
        Contact retrieved = retrieveContact(id);
        if(retrieved != null) {
            retrieved.setAddress(address);
            return true;
        }

        return false;
    }

    /**
     * Helper function to retrieve a Contact from our list.
     * @param id - The id of our specified contact
     * @return - The Contact with the matching id, null otherwise
     */
    public Contact retrieveContact(String id) {
        for(Contact contact : contactList) {
            if(id.equals(contact.getId())) {
                return contact;
            }
        }

        return null;
    }
}
