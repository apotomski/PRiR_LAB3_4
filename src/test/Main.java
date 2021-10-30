package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        int N = 4096;
        int q = 100;
        int[][] tab = new int[N][N];

        Fraktal[] julia = new Fraktal[4];
        for(int i = 0; i < 4; i++) {
            julia[i] = new Fraktal(i, N, tab);
            julia[i].start();
        }

        try {
            for(int i = 0; i < 4; i++) {
                julia[i].join();
            }
        }catch(Exception e) {

        }

        BufferedImage img = Main.przygotujIMG(tab, N, q);

        ImageIO.write(img, "PNG", new File("FraktalJuli.png"));

    }

    static public BufferedImage przygotujIMG(int[][] tab, int N, int q) {
        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = tab[i][j];
                Color c;

                if (k >= q)
                    c = new Color(0, 0, 0);
                else
                    c = new Color(0, (float) k / q, 0);

                img.setRGB(i, j, c.getRGB());
            }
        }
        return img;
    }
}
