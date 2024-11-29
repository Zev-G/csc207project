package use_case.accountdelete;

/**
 * Defines the output boundary for handling the results of account deletion actions.
 */
public interface AccountDeleteOutputBoundary {

    /**
     * Handles a successful account deletion.
     */
    void handleSuccess();

    /**
     * Handles a failed account deletion.
     */
    void handleFail();
}
