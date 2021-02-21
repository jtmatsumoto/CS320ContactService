// Implementation of the Contact Class
public class Contact {
    private final String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    /**
     * Basic constructor for the Contact. All require fields are necessary
     * in order to construct.
     * @param inputId - ID specified, we check that it's <= 10 characters
     * @param inputFirstName - firstName specified, check it's <= 10 characters
     * @param inputLastName - lastName specified, check it's <= 10 characters
     * @param inputPhoneNumber - phoneNumber, check it's == 10 characters
     * @param inputAddress - address, check it's <= 30 characters
     */
    public Contact(
        String inputId,
        String inputFirstName,
        String inputLastName,
        String inputPhoneNumber,
        String inputAddress
    ) {
        id = safeAssign(inputId, 10, "id");
        firstName = safeAssign(inputFirstName, 10, "firstName");
        lastName = safeAssign(inputLastName, 10, "lastName");
        phoneNumber = assignPN(inputPhoneNumber);
        address = safeAssign(inputAddress, 30, "address");
    }

    // Below are all of our getter functions
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    // Below are our setter functions. Notice there is no setter for id.
    public void setFirstName(String inputFirstName) {
        firstName = safeAssign(inputFirstName, 10, "firstName");
    }

    public void setLastName(String inputLastName) {
        lastName = safeAssign(inputLastName, 10, "lastName");
    }

    public void setPhoneNumber(String inputPhoneNumber) {
        phoneNumber = assignPN(inputPhoneNumber);
    }

    public void setAddress(String inputAddress) {
        address = safeAssign(inputAddress, 30, "address");
    }
    /**
     * Helper function that performs assertions for us.
     * @param input - The input string to check
     * @param limit - Require that the input string has length <= limit and not null
     * @param message - Part of the assertion error message
     * @return - Returns input if ok, throws AssertionError otherwise.
     */
    private String safeAssign(String input, int limit, String message) {
        assert (input != null && input.length() <= limit): message + ": size limit exceeded";
        return input;
    }

    /**
     * Helper function to assign the phone number
     * @param phoneNumber - phoneNumber to check
     * @return - phoneNumber if the length is equal to 10, throws AssertionError otherwise
     */
    private String assignPN(String phoneNumber) {
        assert (phoneNumber != null && phoneNumber.length() == 10) : "phoneNumber: size is NOT 10";
        assert allDigits(phoneNumber) : "phoneNumber contains non-numeric character";
        return phoneNumber;
    }

    /**
     * Return if s contains only digits. We'll just used built-in parseInt
     * for checking
     * @param s - the input string to check (phone number)
     * @return - True if s contains only digits, false otherwise
     */
    private boolean allDigits(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
