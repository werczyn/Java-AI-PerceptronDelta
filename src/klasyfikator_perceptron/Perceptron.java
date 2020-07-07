package klasyfikator_perceptron;

import java.util.List;

public class Perceptron {

    private double [] wagi;
    private double prog;

    public Perceptron(int liczbaAtrybutow, double prog) {
        this.wagi = new double[liczbaAtrybutow];

        for (int i=0; i<wagi.length; i++){
            wagi[i]=Math.random();
        }

        this.prog = prog;
    }

    public void klasyfikuj(List<Obserwacje> obserwacje){
        for (Obserwacje obserwacja : obserwacje) {
            klasyfikuj(obserwacja);
        }
    }

    public void klasyfikuj(Obserwacje obserwacja){
        double[] atrybuty = obserwacja.getAtrybuty();
        double net = 0;
        //liczenie wartosci net
        for (int i = 0; i < atrybuty.length; i++) {
            net += atrybuty[i]*wagi[i];
        }
        //obliczanie wyjscia
        if (net >= prog){
            obserwacja.setZaklasyfikowano(1);
        }else{
            obserwacja.setZaklasyfikowano(0);
        }
    }


    public void uczSie(List<Obserwacje> obserwacje) {
        klasyfikuj(obserwacje);
        for (Obserwacje obserwacja : obserwacje) {
            if (!obserwacja.czyPrawidlowoZaklasyfikowano()){
                int mnoznik = obserwacja.getAtrybutDecyzyjny() - obserwacja.getZaklasyfikowano();
                for (int i = 0; i < obserwacja.getAtrybuty().length; i++) {
                    wagi[i]=wagi[i]+(obserwacja.getAtrybuty()[i]*mnoznik);
                }
            }
        }
        int[] tablicaDecyzji = new int[obserwacje.size()];
        int[] tablicaZaklasyfikowanych = new int[obserwacje.size()];

        for (int i = 0; i < tablicaDecyzji.length; i++) {
            tablicaDecyzji[i] = obserwacje.get(i).getAtrybutDecyzyjny();
            tablicaZaklasyfikowanych[i] = obserwacje.get(i).getZaklasyfikowano();
        }

        for (int i = 0; i < tablicaDecyzji.length; i++) {
            if (tablicaDecyzji[i] != tablicaZaklasyfikowanych[i]){
                uczSie(obserwacje);
            }
        }

    }

    public void showWektorWag(){
        for (double v : wagi) {
            System.out.println(v);
        }
    }
}
