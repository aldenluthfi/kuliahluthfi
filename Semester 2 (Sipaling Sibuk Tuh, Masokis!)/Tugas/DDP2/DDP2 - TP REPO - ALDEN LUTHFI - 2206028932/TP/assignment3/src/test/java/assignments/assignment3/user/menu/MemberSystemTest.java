package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class MemberSystemTest {

    @BeforeEach
    void setUp() {
        Nota.totalNota = 0;
        Employee.employeeCount = 0;
        NotaManager.notaList = new Nota[0];
        NotaManager.cal.set(1998, Calendar.DECEMBER, 28);
    }

    @Test
    void testAddMember() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);
        assertTrue(memberSystem.isMemberExist("1"));
    }

    @Test
    void testCreateNotaWithoutServices() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);

        // Mock user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine())
                .thenReturn("1")
                .thenReturn("Reguler") // Paket Reguler
                .thenReturn("5") // 5 Kg
                .thenReturn("x") // No SetrikaService
                .thenReturn("x") // No AntarService
                .thenReturn("3"); // logout

        memberSystem.login(scanner, "1", "password");
        Nota nota = member.getNotaList()[0];
        assertEquals(1, member.getNotaList().length);
        assertEquals("Reguler", nota.getPaket());
        assertEquals("28/12/1998", nota.getTanggal());
        assertEquals(5, nota.getBerat());
        assertEquals(1, nota.getServices().length);
    }

    @Test
    void testCreateNotaWithSetrikaService() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);

        // Mock user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine())
                .thenReturn("1")
                .thenReturn("Reguler") // Paket Reguler
                .thenReturn("1") // 1 Kg
                .thenReturn("")  // SetrikaService
                .thenReturn("x") // No AntarService
                .thenReturn("3"); // No AntarService

        memberSystem.login(scanner, "1", "password");
        Nota nota = member.getNotaList()[0];
        assertEquals(1, member.getNotaList().length);
        assertEquals("Reguler", nota.getPaket());
        assertEquals(2, nota.getBerat());
        assertEquals(2, nota.getServices().length);
        assertTrue(nota.getServices()[1] instanceof SetrikaService);
        assertEquals(1, NotaManager.notaList.length);
        assertEquals("28/12/1998", nota.getTanggal());

    }

    @Test
    void testCreateNotaWithAntarService() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);

        // Mock user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine())
                .thenReturn("1")
                .thenReturn("Reguler") // Paket Reguler
                .thenReturn("5") // 5 Kg
                .thenReturn("x") // No SetrikaService
                .thenReturn("")  // AntarService
                .thenReturn("3");

        memberSystem.login(scanner, "1", "password");
        Nota nota = member.getNotaList()[0];
        assertEquals(1, member.getNotaList().length);
        assertEquals("Reguler", nota.getPaket());
        assertEquals(5, nota.getBerat());
        assertEquals(2, nota.getServices().length);
        assertTrue(nota.getServices()[1] instanceof AntarService);
        assertEquals(1, NotaManager.notaList.length);
        assertEquals("28/12/1998", nota.getTanggal());

    }

    @Test
    void testCreateNotaWithBothServices() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);

        // Mock user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine())
                .thenReturn("1")
                .thenReturn("Reguler") // Paket Reguler
                .thenReturn("5") // 5 Kg
                .thenReturn("")  // SetrikaService
                .thenReturn("")  // AntarService
                .thenReturn("3");  // AntarService

        memberSystem.login(scanner, "1", "password");
        Nota nota = member.getNotaList()[0];
        assertEquals(1, member.getNotaList().length);
        assertEquals("Reguler", nota.getPaket());
        assertEquals(5, nota.getBerat());
        assertTrue(nota.getServices()[1] instanceof SetrikaService);
        assertTrue(nota.getServices()[2] instanceof AntarService);
        assertEquals(1, NotaManager.notaList.length);
        assertEquals("28/12/1998", nota.getTanggal());
    }

    @Test
    void testShowDetailNota() {
        MemberSystem memberSystem = new MemberSystem();
        Member member = new Member("ikan", "1", "password");
        memberSystem.addMember(member);
        Nota nota = new Nota(member, 5, "Reguler", "09/04/2023");
        member.addNota(nota);

        // Mock user input
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine())
                .thenReturn("2") // Choose to show detail nota
                .thenReturn("3"); // Choose to logout

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        memberSystem.login(scanner, "1", "password");
        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("[ID Nota = 0]"));
        assertTrue(output.contains("ID    : 1"));
        assertTrue(output.contains("Paket : reguler"));
        assertTrue(output.contains("Tanggal Terima  : 09/04/2023"));
    }
}
