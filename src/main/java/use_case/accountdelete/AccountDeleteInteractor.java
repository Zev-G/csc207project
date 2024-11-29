package use_case.accountdelete;

import use_case.dataAccessInterface.UserDataAccess;
import use_case.account.AccountInputData;

public class AccountDeleteInteractor implements AccountDeleteInputBoundary {

    private final AccountDeleteOutputBoundary outputBoundary;
    private final UserDataAccess dataAccess;

    public AccountDeleteInteractor(AccountDeleteOutputBoundary outputBoundary, UserDataAccess dataAccessMock) {
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
