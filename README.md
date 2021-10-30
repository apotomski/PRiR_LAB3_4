# PRiR_LAB3_4
PRiR lab3 projekt 4. Stworzenie fraktala Julli. program tworzy plik png z fraktalem Julli obliczenia przygotowujące tablicę z kolorami są wykonowane na wątkach.

Opis kodu

klasa Fraktal

  Konstruktor ustawia zmienne 
  
      public Fraktal(int i, int N, int[][] tab) {
          this.start = (N / 4) * i;
          this.stop = (N / 4) * (i + 1);
          this.N = N;
          this.X = 2.5 / N;
          this.Y = 2.5 / N;
          this.tab = tab;
      }
      
 metoda run przygotowuje swoją część tablicy z wartością dla koloru zielonego
  
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
    
 klasa Zespolona potrzebna do obliczeń 
 
 Konstruktor
    public Zespolona(double r, double u){
        this.realna = r;
        this.urojona = u;
    }
    
  Funkcje wykonujące operacje na liczbie zespolonej
    
      public double mod(){
          return Math.sqrt(realna * realna + urojona * urojona);
      }

      public Zespolona dodaj(Zespolona inna){
          return new Zespolona(this.realna + inna.realna, this.urojona + inna.urojona);
      }

      public Zespolona podniesDoKwadratu(){
          double r = (this.realna * this.realna) - (this.urojona * this.urojona);
          double u = (this.realna * this.urojona) + (this.urojona * this.realna);

          return new Zespolona(r, u);
      }
      
   Main
   
 main tworzy obiekty klasy Fraktal wywołuje ich pracę na wątkach następnie gdy wszystkie skończą liczyć tworzy obraz i go zapisuje
   
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
          
    funkcja przygotowująca BufferedImage na podstawie tablicy którą wcześniej przygotowały obiekty klasy Fraktal
    
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
