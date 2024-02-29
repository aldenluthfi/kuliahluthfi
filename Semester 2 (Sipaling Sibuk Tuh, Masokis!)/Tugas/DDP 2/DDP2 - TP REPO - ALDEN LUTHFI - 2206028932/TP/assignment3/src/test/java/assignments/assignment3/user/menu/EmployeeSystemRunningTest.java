package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmployeeSystemRunningTest {
    private EmployeeSystem employeeSystem;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        Nota.totalNota = 0;
        Employee.employeeCount = 0;
        employeeSystem = new EmployeeSystem();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        NotaManager.notaList = new Nota[0];
    }

    @Test
    void testRunCuciSatu() {
        Nota nota = new Nota(new Member("Joe Hawley", "1", "password"), 1,"Reguler" ,"");
        NotaManager.addNota(nota);

        String input = "1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        employeeSystem.run(scanner, new Employee("Ivan Nababan", "akuDDP"));

        String expectedOutput = """
                Login as : IVAN-4
                Selamat datang Ivan Nababan!

                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Stand back! Ivan Nababan beginning to nyuci!
                Nota 0 : Sedang mencuci...

                Login as : IVAN-4
                Selamat datang Ivan Nababan!

                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Logging out...
                """.replaceAll("\\s+","");
        String actual = outContent.toString().replaceAll("\\s+","");
        assertEquals(expectedOutput, actual);
        assertTrue(actual.contains("Logging out...".replaceAll("\\s+","")));
        assertTrue(nota.getServices()[0].isDone());
    }

    @Test
    void testRunCuciDua() {
        Nota nota1 = new Nota(new Member("Joe Hawley", "1", "password"), 1, "Reguler","");
        Nota nota2 = new Nota(new Member("Jep Aldehid", "1", "password"), 2,"Reguler","");
        nota2.addService(new SetrikaService());
        NotaManager.addNota(nota1);
        NotaManager.addNota(nota2);
        nota2.kerjakan();

        String input = "1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        employeeSystem.run(scanner, new Employee("Dek Depe", "akuDDP"));

        String expectedOutput = """
                
                Login as : DEK-4
                Selamat datang Dek Depe!

                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Stand back! Dek Depe beginning to nyuci!
                Nota 0 : Sedang mencuci...
                Nota 1 : Sedang menyetrika...

                Login as : DEK-4
                Selamat datang Dek Depe!

                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Logging out...
                """.replaceAll("\\s+","");
        String actual = outContent.toString().replaceAll("\\s+","");
        assertEquals(expectedOutput, actual);
        assertEquals(2,NotaManager.notaList.length);
        assertTrue(nota2.getServices()[0].isDone());
    }

    @Test
    void testRunCuciTiga() {
        Nota nota1 = new Nota(new Member("Joe Hawley", "1", "password"), 1, "Reguler" ,"");
        Nota nota2 = new Nota(new Member("Jep Aldehid", "1", "password"), 2,"Reguler" ,"");
        Nota nota3 = new Nota(new Member("SVD", "3", "ppp"), 3, "Reguler" ,"");
        nota2.addService(new SetrikaService());
        nota3.addService(new AntarService());
        NotaManager.addNota(nota1);
        NotaManager.addNota(nota2);
        NotaManager.addNota(nota3);
        nota2.kerjakan();
        nota2.kerjakan();
        nota3.kerjakan();

        String input = "1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        employeeSystem.run(scanner, new Employee("Mas Rusdi", "akuDDP"));

        String expectedOutput = """
                
                Login as : MAS-4
                Selamat datang Mas Rusdi!
                
                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Stand back! Mas Rusdi beginning to nyuci!
                Nota 0 : Sedang mencuci...
                Nota 1 : Sudah selesai.
                Nota 2 : Sedang mengantar...
                
                Login as : MAS-4
                Selamat datang Mas Rusdi!
                
                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Logging out...
                """.replaceAll("\\s+","");
        String actual = outContent.toString().replaceAll("\\s+","");
        assertEquals(expectedOutput, actual);
        assertTrue(nota2.getServices()[0].isDone());
        assertTrue(nota2.isDone());
    }

    @Test
    void testRunDisplay() {
        Nota nota1 = new Nota(new Member("Joe Hawley", "1", "password"), 1, "Reguler" ,"");
        Nota nota2 = new Nota(new Member("Jep Aldehid", "1", "password"), 2,"Reguler" ,"");
        Nota nota3 = new Nota(new Member("SVD", "3", "ppp"), 3, "Reguler" ,"");
        nota2.addService(new SetrikaService());
        nota3.addService(new AntarService());
        NotaManager.addNota(nota1);
        NotaManager.addNota(nota2);
        NotaManager.addNota(nota3);
        nota2.kerjakan();
        nota2.kerjakan();
        nota3.kerjakan();

        String input = "2\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        employeeSystem.run(scanner, new Employee("Mas Rusdi", "akuDDP"));

        String expectedOutput = """
                
                Login as : MAS-4
                Selamat datang Mas Rusdi!
                
                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Nota 0 : Belum selesai.
                Nota 1 : Sudah selesai.
                Nota 2 : Belum selesai.
                
                Login as : MAS-4
                Selamat datang Mas Rusdi!
                
                1. It's nyuci time
                2. Display List Nota
                3. Logout
                Apa yang ingin Anda lakukan hari ini? Logging out...
                """.replaceAll("\\s+","");
        String actual = outContent.toString().replaceAll("\\s+","");
        assertEquals(expectedOutput, actual);
        assertTrue(nota2.getServices()[1].isDone());
        assertTrue(nota2.isDone());
    }
}
