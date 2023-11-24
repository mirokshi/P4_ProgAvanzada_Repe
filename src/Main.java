import java.util.Scanner;

public class Main {

    public static Scanner console = new Scanner(System.in);
    public static AcbEnll<Jugador> arbre = new AcbEnll<>();

    public static void main(String[] args) {

        System.out.println("Opcions:");
        System.out.println("1.- Inserir Jugador");
        System.out.println("2.- Eliminar Jugador");
        System.out.println("3.- Visualitzar un dels dos arbres");
        System.out.println("4.- Clonar");
        System.out.println("5.- Acabar");
        System.out.println("Tria una opció [1,5]");

        int opcio = console.nextInt();

        switch (opcio){
            case 1:
                 inserirJugador();
                break;
            case 2:
                eliminarJugador();
                break;
            case 3:
                visualitzaArbres();
                break;
            case 4:
                clonar();
                break;
            case 5:
                acabar();
                break;
        }
    }

    private static void inserirJugador(){

    }

    private static void eliminarJugador() {
        System.out.println("Indica el jugador que vols eliminar");
        int jugador = console.nextInt();

        arbre.esborrar();
        System.out.println("S'ha eliminat el jugador " + carta.toString());
    }

    private static void visualitzaArbres() {
    }

    private static void clonar() {
    }

    private static void acabar() {
    }
}