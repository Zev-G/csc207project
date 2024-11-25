package use_case.accountdelete;

import data_access.DataAccessMock;
import use_case.account.AccountInputData;

public class AccountDeleteInteractor implements AccountDeleteInputBoundary {

    private final AccountDeleteOutputBoundary outputBoundary;
    private final DataAccessMock dataAccessMock;

    public AccountDeleteInteractor(AccountDeleteOutputBoundary outputBoundary, DataAccessMock dataAccessMock) {
        this.outputBoundary = outputBoundary;
        this.dataAccessMock = dataAccessMock;
    }


    @Override
    public void deleteAccount(AccountInputData inputData) {
        boolean success = dataAccessMock.deleteAccount(inputData.getUserId());
        if (success) outputBoundary.handleSuccess();
        else outputBoundary.handleFail();
    }

}
