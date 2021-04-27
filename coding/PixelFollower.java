import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PixelFollower {
    public static void main(String args[])throws Exception {   
        //Reading the image
        File imageFile = new File("../images/input/dot.png");
        BufferedImage img = ImageIO.read(imageFile);
        BufferedImage tempImg = ImageIO.read(imageFile);

        //Reading user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lambda: ");
        int t = scanner.nextInt();
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();
        
        int pixelX = 29, pixelY = 32, flag = 0;
        for(int i = 0; i < iterations; i++){
            //Shearing (squared pxp images)
            int p = img.getHeight();
            //T(x, y) = (x + y, y) mod p
            for(int y = 0; y < p; y++){
                for(int x = 0; x < p; x++){
                    int newX = (x + t * y) % p;
                    int newY = y % p;
                    tempImg.setRGB(newX, newY, img.getRGB(x, y));
                    if((y == pixelY) && (x == pixelX) && (flag == 0)){
                        pixelY = newY;
                        pixelX = newX;
                        flag = 1;
                    }
                }
            }
            flag = 0;

            //T(x, y) = (x, x + y) mod p
            for(int y = 0; y < p; y++){
                for(int x = 0; x < p; x++){
                    int newX = x % p;
                    int newY = (x * t + y) % p;
                    img.setRGB(newX, newY, tempImg.getRGB(x, y));
                    if((y == pixelY) && (x == pixelX) && (flag == 0)){
                        pixelY = newY;
                        pixelX = newX;
                        flag = 1;
                    }
                }
            }
            flag = 0;

            //Outputting the final file
            File outputFile = new File("../images/output/dot-output.png");
            ImageIO.write(img, "png", outputFile);    
            System.out.println("Iteração: " + (i+1) + "       Posição: x = " + pixelX + "        y = " + pixelY);
            Thread.sleep(1000);
        }

        scanner.close();
    }
}