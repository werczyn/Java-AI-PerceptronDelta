package klasyfikator_perceptron;

public class Obserwacje {

    private int atrybutDecyzyjny;
    private double[] atrybuty;
    private int zaklasyfikowano;

    public Obserwacje(String atrybutDecyzyjny, double[] atrybuty) {
        if (atrybutDecyzyjny.equals("Iris-setosa")){
            this.atrybutDecyzyjny = 1;
        }else{
            this.atrybutDecyzyjny = 0;
        }

        this.atrybuty = atrybuty;
    }

    public Obserwacje(double[] atrybuty){
        this.atrybuty = atrybuty;
    }

    public int getAtrybutDecyzyjny() {
        return atrybutDecyzyjny;
    }

    public double[] getAtrybuty() {
        return atrybuty;
    }

    public void setZaklasyfikowano(int zaklasyfikowano) {
        this.zaklasyfikowano = zaklasyfikowano;
    }

    public boolean czyPrawidlowoZaklasyfikowano(){
        if (zaklasyfikowano==atrybutDecyzyjny)
            return true;
        else
            return false;
    }

    public int getZaklasyfikowano() {
        return zaklasyfikowano;
    }

    public int liczbaAtrybutow(){
        //liczba atrybutow bez decyzyjnego
        return atrybuty.length;
    }

    public void normalizuj(){
        double dlugosc=0;
        for (int i = 0; i < atrybuty.length; i++) {
            dlugosc+=Math.pow(atrybuty[i],2);
        }
        dlugosc = Math.sqrt(dlugosc);
        for (int i = 0; i < atrybuty.length; i++) {
            atrybuty[i]=atrybuty[i]/dlugosc;
        }
    }
}
