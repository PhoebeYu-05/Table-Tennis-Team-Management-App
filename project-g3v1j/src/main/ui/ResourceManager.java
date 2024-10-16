package ui;

import javax.swing.ImageIcon;
import java.net.URL;

// Represents a resource manager (help load image)
public class ResourceManager {

    public static ImageIcon loadImage(String path) {
        URL imgURL = ResourceManager.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}