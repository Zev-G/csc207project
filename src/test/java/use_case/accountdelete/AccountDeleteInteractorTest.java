package use_case.accountdelete;

import data_access.DataAccessMock;
import org.junit.jupiter.api.Test;
import use_case.account.AccountInputData;

import static org.junit.jupiter.api.Assertions.*;

class AccountDeleteInteractorTest {

    private static class AccountDeleteMockPresenter implements AccountDeleteOutputBoundary {

        private Boolean success = null;

        @Override
        public void handleSuccess() {
            success = true;
        }

        @Override
        public void handleFail() {
            success = false;
        }

        public Boolean getSuccess() {
            return success;
        }
    }

    @Test
    void deleteAccount() {
        DataAccessMock mockDatabase = new DataAccessMock();
        AccountDeleteMockPresenter presenter = new AccountDeleteMockPresenter();
        AccountDeleteInteractor interactor = new AccountDeleteInteractor(presenter, mockDatabase);

        AccountInputData inputDataChanged = new AccountInputData(true, "Zev", "godfreyzev@gmail.com", "1234", 1);
        interactor.deleteAccount(inputDataChanged);
        // Should have removed the username in the database
        assertNull(mockDatabase.getUser(1));
        // Should have been a success
        assertEquals(true, presenter.getSuccess());

        interactor.deleteAccount(inputDataChanged);
        // Should have failed
        assertEquals(false, presenter.getSuccess());

    }
}