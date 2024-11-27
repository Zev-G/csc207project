package interface_adapter.multiplayer;

import interface_adapter.ViewModel;

public class MultiplayerViewModel extends ViewModel<String> {

    public MultiplayerViewModel() {
        super("multiplayer");
        this.setState("connect");
    }
}