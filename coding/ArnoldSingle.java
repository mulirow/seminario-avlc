import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ArnoldSingle {
    public static void main(String args[])throws Exception {   
        //Reading the image
        File imageFile = new File("images/input/cat.png");
        BufferedImage img = ImageIO.read(imageFile);
        BufferedImage tempImg = ImageIO.read(imageFile);
        BufferedImage tempImg2 = ImageIO.read(imageFile);

        //Reading user input pt. 1
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lambda 1 for the horizontal shearing: ");
        int t = scanner.nextInt();
        System.out.print("Enter lambda 2 for the vertical shearing: ");
        int t2 = scanner.nextInt();
        
        //Predicting the amount of iterations
        int iterations = 0, flag = 0;

        while(flag != (img.getHeight() * img.getWidth())){
            flag = 0;
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
                    int newY = (x * t2 + y) % p;
                    img.setRGB(newX, newY, tempImg.getRGB(x, y));
                }
            }

            for(int y = 0; y < p; y++){
                for(int x = 0; x < p; x++){
                    if(img.getRGB(x, y) == tempImg2.getRGB(x, y)){
                        flag++;
                    }
                }
            } 
            iterations++;
        }

        System.out.print("Prediction: it will take " + iterations + " iterations for the image to return to its original state.\n");

        //Reading user input pt. 2
        System.out.print("Enter the number of iterations: ");
        int userIterations = scanner.nextInt();

        for(int i = 0; i < userIterations; i++){
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
                    int newY = (x * t2 + y) % p;
                    img.setRGB(newX, newY, tempImg.getRGB(x, y));
                }
            }

            //Outputting the final files
            File outputFile = new File("images/output/cat-output.png");
            ImageIO.write(img, "png", outputFile);    
            //Thread.sleep(1000);
        }

        scanner.close();
    }
}