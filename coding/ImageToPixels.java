package coding;
import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageToPixels {
   public static void main(String args[])throws Exception {
      FileWriter writer = new FileWriter("data.txt");
      
      //Reading the image
      File file= new File("cat-input.jpg");
      BufferedImage img = ImageIO.read(file);

      for (int y = 0; y < img.getHeight(); y++) {
         for (int x = 0; x < img.getWidth(); x++) {
            //Retrieving contents of a pixel
            int pixel = img.getRGB(x,y);

            //Creating a Color object from pixel value
            Color color = new Color(pixel, true);

            //Retrieving the RGB values
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            writer.append(red+" ");
            writer.append(green+" ");
            writer.append(blue+"\n");
            writer.flush();
         }
      }
      writer.close();
   }
}