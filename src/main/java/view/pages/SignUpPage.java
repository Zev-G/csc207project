package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import view.View;
import view.ViewConstants;
import view.app.App;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpPage extends Page implements View<AccountState> {

    private final AccountViewModel viewModel;

    private final DLabel pageTitle = new DLabel("Sign Up");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel usernameLabel = new DLabel("Username");
    private final DLabel emailLabel = new DLabel("Email");
    private final DLabel passwordLabel = new DLabel("Password");
    private final JTextField usernameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JButton signUpButton = new RoundedButton("Sign Up");
    private final JButton cancelButton = new RoundedButton("Cancel");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();
}