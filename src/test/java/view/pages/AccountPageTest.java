package view.pages;

import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import org.junit.jupiter.api.Test;
import view.components.AppViewManager;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountPageTest {

    @Test
    void testAccountPageDisplaysAccountState() throws NoSuchFieldException, IllegalAccessException {
        // Mock AppViewManager and AccountViewModel
        AppViewManager mockApp = mock(AppViewManager.class);
        AccountViewModel accountViewModel = new AccountViewModel();
        when(mockApp.getAccountViewModel()).thenReturn(accountViewModel);

        // Set up AccountPage
        AccountPage accountPage = new AccountPage(mockApp);

        // Set AccountState
        AccountState state = new AccountState(true, "testUser", "testEmail@example.com", "testPassword", "user123");
        accountViewModel.setState(state);

        // Access private fields using reflection
        Field usernameField = AccountPage.class.getDeclaredField("usernameField");
        Field emailField = AccountPage.class.getDeclaredField("emailField");
        usernameField.setAccessible(true);
        emailField.setAccessible(true);

        // Verify UI fields
        JTextField usernameTextField = (JTextField) usernameField.get(accountPage);
        JTextField emailTextField = (JTextField) emailField.get(accountPage);

        assertEquals("testUser", usernameTextField.getText());
        assertEquals("testEmail@example.com", emailTextField.getText());
    }
}
