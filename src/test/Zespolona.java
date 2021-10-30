package test;

public class Zespolona {
    private double realna;
    private double urojona;

    public Zespolona(double r, double u){
        this.realna = r;
        this.urojona = u;
    }

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

}
