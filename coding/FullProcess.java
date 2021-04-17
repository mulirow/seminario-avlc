package coding;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FullProcess {
    public static void main(String args[])throws Exception {   
        //Reading the image
        File imageFile = new File("dot-input.jpg");
        BufferedImage img = ImageIO.read(imageFile);
        BufferedImage tempImg = ImageIO.read(imageFile);

        //Reading user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lambda: ");
        int t = scanner.nextInt();
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();

        for(int i = 0; i < iterations; i++){
            //Shearing (squared pxp images)
            int p = img.getHeight();
            //T(x, y) = (x + y, y) mod p
            for(int y = 0; y < p; y++){
                for(int x = 0; x < p; x++){
                    int newX = (x + t * y) % p;
                    int newY = y % p;
                    tempImg.setRGB(newX, newY, img.getRGB(x, y));
                }
            }

            //T(x, y) = (x, x + y) mod p
            for(int y = 0; y < p; y++){
                for(int x = 0; x < p; x++){
                    int newX = x % p;
                    int newY = (x * t + y) % p;
                    img.setRGB(newX, newY, tempImg.getRGB(x, y));
                }
            }

            //Outputting the final files
            File outputFile = new File("dot-output" + i + ".jpg");
            ImageIO.write(img, "jpg", outputFile);    
            Thread.sleep(1000);
        }

        scanner.close();
    }
}