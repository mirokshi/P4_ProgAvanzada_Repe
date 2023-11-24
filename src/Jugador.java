public class Jugador implements Comparable<Jugador> {

    public enum Posicio {
        Base,Escorta,Aler,AlerPivot,Pivot
    }

    Posicio pos;
    int puntuacio;


    public Jugador(int p, int punt){
        puntuacio=punt;
        for (Posicio ps: Posicio.values()) {
            if (p==ps.ordinal()) pos = ps;
        }
    }

    @Override
    public int compareTo(Jugador jugador){
        if (pos==jugador.pos){
            return Integer.compare(puntuacio, jugador.puntuacio);
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return pos + " " + puntuacio;
    }
}
