package assignments.assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginManagerTest {
    private LoginManager loginManager;
    private EmployeeSystem employeeSystem;
    private MemberSystem memberSystem;

    @BeforeEach
    void setUp() {
        Employee.employeeCount = 0;
        memberSystem = new MemberSystem();
        employeeSystem = new EmployeeSystem();
        loginManager = new LoginManager(employeeSystem, memberSystem);
    }

    @Test
    void testGetSystemForMember() {
        String memberId = "3";
        memberSystem.addMember(new Member("diskus", memberId, "password"));

        assertEquals(memberSystem, loginManager.getSystem(memberId));
    }

    @Test
    void testGetSystemForEmployee() {
        String employeeId = "DEK-0";

        assertEquals(employeeSystem, loginManager.getSystem(employeeId));
    }

    @Test
    void testGetSystemForInvalidId() {
        assertNull(loginManager.getSystem("INVALID_ID"));
    }

    @Test
    void testRegisterMember() {
        String name = "diskus";
        String noHp = "081234567890";
        String password = "password";

        Member member = loginManager.register(name, noHp, password);

        assertNotNull(member);
        assertEquals(NotaGenerator.generateId(name, noHp), member.getId());
    }

    @Test
    void testRegisterExistingMember() {
        String name = "diskus";
        String noHp = "081234567890";
        String password = "password";

        loginManager.register(name, noHp, password);

        assertNull(loginManager.register(name, noHp, password));
    }
}