package interface_adapter.image;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ImagePageViewModel {

    private String selectedImagePath = "";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public void setSelectedImagePath(String selectedImagePath) {
        String oldPath = this.selectedImagePath;
        this.selectedImagePath = selectedImagePath;
        support.firePropertyChange("selectedImagePath", oldPath, selectedImagePath);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
