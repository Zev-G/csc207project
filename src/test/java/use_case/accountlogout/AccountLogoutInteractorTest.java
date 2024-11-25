package use_case.accountlogout;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountLogoutInteractorTest {

    private static class AccountLogoutPresenterMock implements AccountLogoutOutputBoundary {
        @Override
        public void loggedOut() {

        }
    }

    @Test
    void logout() {
        AccountLogoutOutputBoundary outputBoundary = new AccountLogoutPresenterMock();
        AccountLogoutInteractor interactor = new AccountLogoutInteractor(outputBoundary);
        interactor.logout();
    }
}