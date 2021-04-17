package coding;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class PixelsToImage {
    public static void main(String args[]) throws Exception{
        File textFile = new File("data.txt");
        Scanner scanner = new Scanner(textFile);

        File imageFile = new File("cat-input.jpg");
        BufferedImage img = ImageIO.read(imageFile);

        int red = 0;
        int green = 0;
        int blue = 0;

        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                if(scanner.hasNextInt()){
                    red = scanner.nextInt();
                    green = scanner.nextInt();
                    blue = scanner.nextInt();
                    Color color = new Color(red, green, blue);
                    img.setRGB(x, y, color.getRGB());
                }
            }
        }

        File outputFile = new File("cat-output.jpg");
        ImageIO.write(img, "jpg", outputFile);

        scanner.close();
    }
}