import java.util.Scanner;

public class Main {

    public static Scanner console = new Scanner(System.in);
    public static AcbEnll<Jugador> arbre = new AcbEnll<>();
    public static AcbEnll<Jugador> arbreClon = new AcbEnll<>();

    public static void main(String[] args) throws ArbreException {

        while (true) {

            System.out.println("Opcions:");

            System.out.println("1.- Inserir Jugador");
            System.out.println("2.- Eliminar Jugador");
            System.out.println("3.- Visualitzar un dels dos arbres");
            System.out.println("4.- Clonar");
            System.out.println("5.- Acabar");

            System.out.println("Tria una opció [1,5]");

            int opcio = console.nextInt();

            switch (opcio) {
                case 1:
                    inserirJugador();
                    break;
                case 2:
                    if (arbre.cardinalitat() != 0) {
                        eliminarJugador();
                    } else {
                        System.out.println("Error. arbre sense jugadors.");
                    }
                    break;
                case 3:
                    if (arbre.cardinalitat() != 0 || arbreClon.cardinalitat() != 0) {
                        visualitzaArbres();
                    } else {
                        System.out.println("Error. arbre sense jugadors.");
                    }
                    break;
                case 4:
                    if (arbre.cardinalitat() != 0) {
                        clonar();
                    } else {
                        System.out.println("Error. arbre sense jugadors.");
                    }

                    break;
                case 5:
                    acabar();
                    break;
            }
        }
    }

    private static void inserirJugador(){
        System.out.println("Indica la posició del jugador que vols inserir");

        int posicio=0;
        while(controlConsole(1,5,posicio)) {
            System.out.println("1.- Base");
            System.out.println("2.- Escorta");
            System.out.println("3.- Aler");
            System.out.println("4.- AlerPivot");
            System.out.println("5.- Pivot");
            System.out.println("Indica la seva posició [1,5]");
            posicio = console.nextInt();
        }
        int puntuacio=-1;
        while (controlConsole(0,1000, puntuacio)){
            System.out.println("Indica la seva puntuació [0,1000]");
            puntuacio = console.nextInt();
        }

        try {
            arbre.inserir(new Jugador(posicio - 1,puntuacio));
        } catch (ArbreException e) {
            System.out.println(e.toString());
        }
    }

    private static void eliminarJugador(){
        System.out.println("Indica la posició del jugador que vols eliminar");

        int posicio=0;
        while(controlConsole(1,5,posicio)) {
            System.out.println("1.- Base");
            System.out.println("2.- Escorta");
            System.out.println("3.- Aler");
            System.out.println("4.- AlerPivot");
            System.out.println("5.- Pivot");
            System.out.println("Indica la seva posició [1,5]");
            posicio = console.nextInt();
        }
        int puntuacio=-1;
        while (controlConsole(0,1000, puntuacio)){
            System.out.println("Indica la seva puntuació [0,1000]");
            puntuacio = console.nextInt();
        }

        Jugador jugador = new Jugador(posicio-1, puntuacio);

        try {

            arbre.esborrar(jugador);
            System.out.println("S'ha eliminat el jugador " + jugador.toString());

        }catch (ArbreException e){
            System.out.println(e.toString());
        }
    }

    private static void visualitzaArbres() throws ArbreException {
        int arbreMostrar=0;
        while (controlConsole(1,2,arbreMostrar)) {
            System.out.println("Indica en quin arbre vols mostrar");
            System.out.println("1.- Actual");
            System.out.println("2.- Clonat");
            System.out.println("Trieu l'arbre [1,2]");
            arbreMostrar = console.nextInt();
        }

        int ordreMostrar=0;

        while (controlConsole(1,2,ordreMostrar)) {
            System.out.println("Indica en quin ordre vols mostrar els jugadors");
            System.out.println("1.- Ascendent");
            System.out.println("2.- Descendent");
            System.out.println("Trieu l'ordre [1,2]");
            ordreMostrar = console.nextInt();
        }

        if(arbreMostrar == 1){
            if (arbre.cardinalitat() != 0){

                arbre.iniRecorregut(ordreMostrar == 1);
                while (!arbre.finalRecorregut()) {
                        System.out.println(((Jugador)arbre.segRecorregut()).toString());
                }
                System.out.println("\n");

            }else{
                System.out.println("No hi ha jugadors en el arbre. \n\n");
            }

        }else if(arbreMostrar == 2){
            if(arbreClon.cardinalitat() != 0){

                arbreClon.iniRecorregut(ordreMostrar == 1);
                while (!arbreClon.finalRecorregut()) {
                    System.out.println(((Jugador) arbreClon.segRecorregut()).toString());
                }
                System.out.println("\n");

            }
            else {
                System.out.println("No hi ha jugadors en el arbre clonat. \n\n");
            }
        }

    }

    private static void clonar() {
        arbreClon = (AcbEnll<Jugador>) arbre.clone();
    }

    private static void acabar() {
        System.exit(0);
    }

    private static boolean controlConsole(int min, int max, int pos){
        return (pos < min || pos > max);
    }
}