public class Jugador implements Comparable<E>{

    @Override
    public int compareTo(E o) {
        return 0;
    }

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
    public String toString() {
        return pos + " " + puntuacio;
    }
}
