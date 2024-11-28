package use_case.accountdelete;

import data_access.DataAccess;
import use_case.account.AccountInputData;

public class AccountDeleteInteractor implements AccountDeleteInputBoundary {

    private final AccountDeleteOutputBoundary outputBoundary;
    private final DataAccess dataAccess;

    public AccountDeleteInteractor(AccountDeleteOutputBoundary outputBoundary, DataAccess dataAccessMock) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccessMock;
    }


    @Override
    public void deleteAccount(AccountInputData inputData) {
        boolean success = dataAccess.deleteAccount(inputData.getUserId());
        if (success) outputBoundary.handleSuccess();
        else outputBoundary.handleFail();
    }

}
