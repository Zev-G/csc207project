package use_case.accountconfirm;

/**
 * Defines the output boundary for handling the results of account confirmation actions.
 */
public interface AccountConfirmOutputBoundary {

    /**
     * Handles a successful account confirmation action.
     */
    void handleSuccess();

    /**
     * Handles a failed account confirmation action.
     */
    void handleFail();
}
