package assignments.assignment3;

import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
class MainMenuTest {

    @Mock
    private LoginManager loginManager;

    @Mock
    private SystemCLI systemCLI;

    @InjectMocks
    private MainMenu mainMenu;

    @Test
    void testRun() {
        // Test data
        String inputId = "TEST-ID";
        String inputPassword = "test-password";
        String nama = "John Doe";
        String noHp = "1234567890";
        String password = "password";

        // Prepare input
        String input = "1\n" + inputId + "\n" + inputPassword + "\n" +
                "2\n" + nama + "\n" + noHp + "\n" + password + "\n" +
                "3\n" +
                "4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        // Mock setup
        Mockito.when(loginManager.getSystem(inputId)).thenReturn(systemCLI);
        Mockito.when(loginManager.register(nama, noHp, password)).thenReturn(new Member(nama, inputId, password));

        // Run the test
        mainMenu = new MainMenu(scanner, loginManager);
        mainMenu.run();

        // Verify calls
        Mockito.verify(loginManager).getSystem(inputId);
        Mockito.verify(loginManager).register(nama, noHp, password);
        Mockito.verify(systemCLI).login(Mockito.any(Scanner.class), Mockito.eq(inputId), Mockito.eq(inputPassword));
    }
}
