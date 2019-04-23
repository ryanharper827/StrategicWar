package strategicwar;

import javafx.scene.image.Image;

import java.util.HashMap;


/**
 * Image pool that loads images as they are needed and gives them to a client.
 */
public class ImagePool {
    HashMap<String, Image> imageMap;
    private static ImagePool instance = new ImagePool();
    public static ImagePool getInstance(){return instance;}

    /**
     * Create a new image pool
     */
    private ImagePool()
    {
        this.imageMap = new HashMap<>();
    }

    /**
     * Get an Image based on its URL
     * @param url the URL of the image
     * @return Image
     */
    public Image getImage(String url)
    {
        Image image;
        if(this.imageMap.containsKey(url))
        {
            image =  this.imageMap.get(url);
        }
        else
        {
            image = new Image(url);
            this.imageMap.put(url, image);
            System.out.println("Loaded image from: " + url);
        }
        return image;
    }

}
