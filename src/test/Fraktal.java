package test;

public class Fraktal  extends Thread{
    int start;
    int stop;
    int N;
    double X;
    double Y;
    int[][] tab;

    public Fraktal(int i, int N, int[][] tab) {
        this.start = (N / 4) * i;
        this.stop = (N / 4) * (i + 1);
        this.N = N;
        this.X = 2.5 / N;
        this.Y = 2.5 / N;
        this.tab = tab;
    }

    public void run() {
        for (int i = this.start; i < this.stop; i++) {
            for (int j = 0; j < this.N; j++) {
                double rzeczywista = (i * this.Y) - 1.25;
                double urojona = (j * this.X) - 1.25;

                int k = 0;

                Zespolona z0 = new Zespolona(rzeczywista, urojona);
                Zespolona z1 = new Zespolona(-0.123, 0.745);

                while(z0.mod() < 2.0 && k < 100) {
                    k++;
                    z0 = z1.dodaj(z0.podniesDoKwadratu());
                }
                this.tab[i][j] = k;
            }
        }
    }

}
