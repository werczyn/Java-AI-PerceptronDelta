package klasyfikator_perceptron;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sciezkaTrening = "data\\iris_training.txt";
        Przetwornik trening = new Przetwornik(sciezkaTrening);
        List<Obserwacje> treningoweObserwacje = trening.getListaObserwacji();

        String sciezkaTest = "data\\iris_test.txt";
        Przetwornik test = new Przetwornik(sciezkaTest);
        List<Obserwacje> testoweObserwacje = test.getListaObserwacji();

        int liczbaAtrybutow = testoweObserwacje.get(0).liczbaAtrybutow();

        Perceptron perceptron = new Perceptron(liczbaAtrybutow,0);
        perceptron.uczSie(treningoweObserwacje);

        perceptron.klasyfikuj(testoweObserwacje);

        //Wyswietlanie procenta prawidlowo zaklasyfikowanych:
        int liczbaUdanych = 0;
        for (Obserwacje obserwacje : testoweObserwacje) {
            if (obserwacje.czyPrawidlowoZaklasyfikowano()){
                liczbaUdanych++;
            }
        }
        double procent = liczbaUdanych*100;
        System.out.println("Liczba zaklasyfikowanych prawidlowo: "+liczbaUdanych+ "/" + testoweObserwacje.size());
        System.out.format("Procent zaklasyfikowanych prawidlowo: %.2f%%%n",procent/testoweObserwacje.size());

        //------------------------------------------------------------

        //-------------------------------------------------------

        System.out.println("Wpisz: \n\tWprowadz: aby wprowadzic wektor atrybutow\n\tEXIT: aby zakonczyc dzialanie programu");
        Scanner scanner = new Scanner(System.in);
        boolean dziala=true;
        while(dziala){
            String napis = scanner.nextLine();
            if (napis.toLowerCase().equals("wprowadz")){
                double[] atrybuty = new double[liczbaAtrybutow];
                for (int i=0; i<liczbaAtrybutow; i++){
                    System.out.println("Podaj atrybut numer "+(i+1)+": ");
                    try {
                        atrybuty[i] = scanner.nextDouble();
                    }catch (InputMismatchException ex) {
                        System.err.println("Podany argument nie jest liczba lub ma nieprawidlowy format");
                        scanner.close();
                        System.exit(-2);
                    }
                    System.out.println("Pobrano :"+ atrybuty[i]);
                }
                Obserwacje nowaObserwacja = new Obserwacje(atrybuty);
                perceptron.klasyfikuj(nowaObserwacja);
                if (nowaObserwacja.getZaklasyfikowano()==1){
                    System.out.println("----->"+"Iris-setosa"+"<-----");
                }else {
                    System.out.println("----->"+"Iris virginica"+" lub "+"Iris versicolor"+"<-----");
                }

            }else if (napis.toLowerCase().equals("exit")){
                dziala = false;
                System.out.println("Zamykanie programu....");
            }else{
                System.out.println("Wpisz: \n\tWprowadz: aby wprowadzic wektor atrybutow\n\tEXIT: aby zakonczyc dzialanie programu");
            }
        }

        //------------------------------------------------------------------

        scanner.close();

    }
}
