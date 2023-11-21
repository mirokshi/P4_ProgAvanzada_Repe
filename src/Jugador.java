public class Jugador implements Comparable<Jugador> {

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
            if (puntuacio==jugador.puntuacio){
                return 0;
            }else if (puntuacio<jugador.puntuacio){
                return -1;
            }else{
                return 1;
            }
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return pos + " " + puntuacio;
    }
}
