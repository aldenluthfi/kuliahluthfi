package assignments.assignment3.user.menu;

import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeSystemLoginTest {
    private EmployeeSystem employeeSystem;

    @BeforeEach
    public void setUp() {
        Employee.employeeCount = 0;
        employeeSystem = new EmployeeSystem();
    }

    @Test
    public void testAuthUserSuccess() {
        Member user = employeeSystem.authUser("DEK-0", "akuDDP");
        assertNotNull(user);
        assertEquals("DEK-0", user.getId());
    }

    @Test
    public void testAuthUserInvalidId() {
        Member user = employeeSystem.authUser("invalid_id", "akuDDP");
        assertNull(user);
    }

    @Test
    public void testAuthUserInvalidPassword() {
        employeeSystem.login(new Scanner(System.in),"DEK-0", "wrong_password");
        Member user = employeeSystem.authUser("DEK-0", "wrong_password");
        assertNull(user);
    }

    @Test
    public void testAuthUserInvalidIdAndPassword() {
        Member user = employeeSystem.authUser("invalid_id", "wrong_password");
        assertNull(user);
    }


}
