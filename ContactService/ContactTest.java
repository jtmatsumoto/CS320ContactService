// runner imports
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// unit test imports
import org.junit.Test;
import static org.junit.Assert.assertEquals;
// imports for expected exceptions
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ContactTest {
    /**
     * Our main function will just run the Test functions defined in the ContactTest
     * class (bundle runner and tests in same class)
     * @param Args - Usual argument to main, although not used
     */
    public static void main(String[] Args) {
        Result result = JUnitCore.runClasses(ContactTest.class);

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
    protected final String validID = "123456789";
    protected final String validFirstName = "Jillian";
    protected final String validLastName = "Smith";
    protected final String validPhoneNumber = "1112223333";
    protected final String validAddress = "11 Gateway Drive";

    // The invalid inputs that we'll use (too long)
    protected final String badID = "1234567890a";
    protected final String badFirstName = "A_Long_FirstName";
    protected final String badLastName = "A_Long_LastName";
    protected final String badPhoneNumberLength = "456";
    protected final String badPhoneNumberInput = "111222333a";
    protected final String badAddress = "This is way too long of an address because it exceeds 30 characters";

    // The valid inputs that we can set to. Notice that they are exactly the limits
    // that are specified.
    protected final String secondFirstName = "Johnnathan";
    protected final String secondLastName = "Richardson";
    protected final String secondPhoneNumber = "4445556666";
    protected final String secondAddress = "123456789012345678901234567890";

    protected final Contact goodContact = new Contact(
            validID,
            validFirstName,
            validLastName,
            validPhoneNumber,
            validAddress
    );

    protected Contact modifiedContact = goodContact;

    // Rule for our expected exception
    @Rule
    public ExpectedException rule = ExpectedException.none();

    // Verify that the contact object we created has the specified parameters
    @Test
    public void verifyContact() {
        // Just assert that our goodContact meets all of our fields
        System.out.println("Checking Contact constructor");
        assertEquals(goodContact.getId(), validID);
        assertEquals(goodContact.getFirstName(), validFirstName);
        assertEquals(goodContact.getLastName(), validLastName);
        assertEquals(goodContact.getPhoneNumber(), validPhoneNumber);
        assertEquals(goodContact.getAddress(), validAddress);
    }

    // Verify that setting the ID to an invalid one raises an AssertionError
    @Test
    public void verifyBadId() {
        System.out.println("verifying id...");
        rule.expect(AssertionError.class);
        new Contact(badID, validFirstName, validLastName, validPhoneNumber, validAddress);
    }

    // Verify that setting/getting firstName is ok
    @Test
    public void verifyFirstName() {
        System.out.println("verifying firstName...");
        // Correct assignment check
        modifiedContact.setFirstName(secondFirstName);
        assertEquals(modifiedContact.getFirstName(), secondFirstName);

        // Raising of exception
        rule.expect(AssertionError.class);
        modifiedContact.setFirstName(badFirstName);
    }

    // Verify setting/getting lastName is ok
    @Test
    public void verifyLastName() {
        System.out.println("verifying lastName...");
        // Correct assignment check
        modifiedContact.setLastName(secondLastName);
        assertEquals(modifiedContact.getLastName(), secondLastName);

        // Raising of excpetion
        rule.expect(AssertionError.class);
        modifiedContact.setLastName(badLastName);
    }

    // Verify setting/getting phoneNumber is ok
    @Test
    public void verifyPhoneNumber() {
        System.out.println("verifying phoneNumber...");
        // Correct assignment check
        modifiedContact.setPhoneNumber(secondPhoneNumber);
        assertEquals(modifiedContact.getPhoneNumber(), secondPhoneNumber);

        // Check exceptional behavior
        rule.expect(AssertionError.class);
        modifiedContact.setPhoneNumber(badPhoneNumberInput);
        modifiedContact.setPhoneNumber(badPhoneNumberLength);
    }

    @Test
    public void verifyAddress() {
        System.out.println("verifying address...");
        // Check correct behavior
        modifiedContact.setAddress(secondAddress);
        assertEquals(modifiedContact.getAddress(), secondAddress);

        // Check exceptional behavior
        rule.expect(AssertionError.class);
        modifiedContact.setAddress(badAddress);
    }
}
