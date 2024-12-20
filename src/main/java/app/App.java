package app;

import view.components.AppViewManager;

public class App {

    public static void main(String[] args) {
        AppViewManager app = new AppBuilder()
                .setupFirebase()
                .setupGame()
                .setupAccount()
//                .setupLeaderboard()
                .setupMGame()
                .setupStats()
                .setupImage()
                .setupSignUp()
                .setupLogIn()
                .setupPages()
                .build();

        app.showPage();
    }
}