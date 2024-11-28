package data_access;
import use_case.signup.SignUpInputData;

public interface SignUpDataAccess {
    void createUser(SignUpInputData data);
}
