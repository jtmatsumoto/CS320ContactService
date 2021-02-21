// runner imports
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// unit test imports
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// imports for expected exceptions
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ContactServiceTest {
    /**
     * Our main function will just run the Test functions defined in the ContactTest
     * class (bundle runner and tests in same class)
     * @param Args - Usual argument to main, although not used
     */
    public static void main(String[] Args) {
        Result result = JUnitCore.runClasses(ContactServiceTest.class);

        for(Failure failure :result.getFailures()) {
            System.out.println(failure);
        }

        if(result.wasSuccessful()) {
            System.out.println("All tests ran as expected!");
        } else {
            System.out.println("Some tests failed - see the above for details.");
        }
    }

    // The valid inputs that we'll use
    protected final String id1 = "123456789";
    protected final String firstName1 = "Jillian";
    protected final String lastName1 = "Smith";
    protected final String phoneNumber1 = "1112223333";
    protected final String address1 = "11 Gateway Drive";
    protected final Contact contact1 = new Contact(id1, firstName1, lastName1, phoneNumber1, address1);

    protected final String id2 = "9876543210";
    protected final String firstName2 = "Johnnathan";
    protected final String lastName2 = "Richardson";
    protected final String phoneNumber2 = "4445556666";
    protected final String address2 = "123456789012345678901234567890";
    protected final Contact contact2 = new Contact(id2, firstName2, lastName2, phoneNumber2, address2);

    protected final String id3 = "24680";
    protected final String firstName3 = "Matthew";
    protected final String lastName3 = "Payne";
    protected final String phoneNumber3 = "7778889999";
    protected final String address3 = "1234 Big Street";
    protected final Contact contact3 = new Contact(id3, firstName3, lastName3, phoneNumber3, address3);

    protected ContactService service = new ContactService(contact1);

    @Rule
    public ExpectedException rule = ExpectedException.none();
    // Try inserting contact contact3 into the list and assert that it's in it,
    // then remove it.
    @Test
    public void verifyInsertionAndRemoval() {
        System.out.println("Checking we can insert a new contact and remove it...");
        assertTrue(service.insertContact(contact3));
        assertTrue(service.removeContact(contact3.getId()));
        assertEquals(service.retrieveContact(contact3.getId()), null);
    }

    // Try inserting contact contact2 into the list and check if we can modify it.
    @Test
    public void verifyInsertionAndChange() {
        System.out.println("Checking we can insert a new contact and modify it...");
        assertTrue(service.insertContact(contact2));

        // Now try modifying it
        System.out.println("Checking we can update firstName...");
        assertTrue(service.updateFirstName(contact2.getId(), firstName3));
        assertEquals(service.retrieveContact(contact2.getId()).getFirstName(), firstName3);

        System.out.println("Checking we can update lastName...");
        assertTrue(service.updateLastName(contact2.getId(), lastName3));
        assertEquals(service.retrieveContact(contact2.getId()).getLastName(), lastName3);

        System.out.println("Checking we can update phoneNumber...");
        assertTrue(service.updatePhoneNumber(contact2.getId(), phoneNumber3));
        assertEquals(service.retrieveContact(contact2.getId()).getPhoneNumber(), phoneNumber3);

        System.out.println("Checking we can update address...");
        assertTrue(service.updateAddress(contact2.getId(), address3));
        assertEquals(service.retrieveContact(contact2.getId()).getAddress(), address3);
    }

    // Verify that our constructor did the correct thing (should be able to locate contact1)
    @Test
    public void verifyContactService() {
        System.out.println("Checking we're able to retrieve the contact inserted via constructor...");
        Contact found = service.retrieveContact(contact1.getId());
        assertEquals(found, contact1);
    }

    // We'll try to change a field to an invalid value. Verify that an issue occurs
    @Test
    public void verifyBadChange() {
        System.out.println("Checking that attempting to provide a null argument will throw exception...");
        rule.expect(AssertionError.class);
        service.updateFirstName(contact1.getId(), null);
    }
}
