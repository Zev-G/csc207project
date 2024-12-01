package use_case.accountconfirm;

import data_access.DataAccessMock;
import entity.User;
import interface_adapter.accountconfirm.AccountConfirmController;
import interface_adapter.accountconfirm.AccountConfirmPresenter;
import org.junit.jupiter.api.Test;
import use_case.account.AccountInputData;

import static org.junit.jupiter.api.Assertions.*;

class AccountConfirmInteractorTest {

    private static class AccountConfirmMockPresenter implements AccountConfirmOutputBoundary {

        private Boolean success = null;

        @Override
        public void handleSuccess() {
            this.success = true;
        }

        @Override
        public void handleFail() {
            this.success = false;
        }

        public Boolean getSuccess() {
            return success;
        }
    }

    @Test
    void pressed() {
        DataAccessMock mockDatabase = new DataAccessMock();
        AccountConfirmMockPresenter presenter = new AccountConfirmMockPresenter();
        AccountConfirmInteractor interactor = new AccountConfirmInteractor(mockDatabase, presenter);

        AccountInputData inputDataChanged = new AccountInputData(true, "Z", "g@gmail.com", "1234", "1");
        interactor.pressed(inputDataChanged);

        try {
            // Resolve the CompletableFuture and assert the user details
            User updatedUser = mockDatabase.getUser("1").get();
            assertEquals("Z", updatedUser.getName());
            assertEquals("g@gmail.com", updatedUser.getEmail());
            // Should have been a success
            assertEquals(true, presenter.getSuccess());
        } catch (Exception e) {
            fail("Exception occurred while resolving CompletableFuture: " + e.getMessage());
        }

        AccountInputData fakeUser = new AccountInputData(true, "asdf", "asdf", "asdf", "2");
        interactor.pressed(fakeUser);
        assertEquals(false, presenter.getSuccess());
    }
}