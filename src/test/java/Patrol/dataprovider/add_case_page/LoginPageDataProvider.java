package Patrol.dataprovider.add_case_page;

import org.testng.annotations.DataProvider;

public class LoginPageDataProvider {

    @DataProvider(name = "loginTestData")
    public Object[][] getLoginData() {
        return new Object[][] {
            // Positive Scenario: Valid email and password
            {"krishna.jaiswal@legitquest.com", "12345678", true},
            // Negative Scenarios
            {"", "ValidPass123", false},                      // Empty email
            {"validuser@example.com", "", false},            // Empty password
            {"invaliduser@example.com", "InvalidPass", false}, // Invalid email and password
            {"validuser@example.com", "WrongPass123", false},  // Valid email, wrong password
            {"invalidemail", "ValidPass123", false},          // Invalid email format
            {"   ", "ValidPass123", false}                   // Email with whitespace
        };
    }
}
