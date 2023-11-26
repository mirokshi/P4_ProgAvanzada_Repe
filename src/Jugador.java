public class Jugador implements Comparable<Jugador>{

    public enum Posicio {
        Base,Escorta,Aler,AlerPivot,Pivot
    }

    Posicio posicio;
    int puntuacio;

    public Jugador(int pos, int punt){
        puntuacio=punt;
        for (Posicio p: Posicio.values()) {
            if (pos==p.ordinal()) posicio = p;
        }
    }

    @Override
    public int compareTo(Jugador jugador) {
        if(this.posicio == jugador.posicio){
            return Integer.compare(this.puntuacio, jugador.puntuacio);
        }

        return Integer.compare(this.posicio.ordinal(), jugador.posicio.ordinal());
    }

    @Override
    public String toString() {
        return posicio + " " + puntuacio;
    }
}
