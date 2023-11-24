import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AcbEnll<E extends Comparable<E>> implements Acb,Cloneable {

    private class NodeA implements Cloneable {
        E inf;
        NodeA esq, dret;

        NodeA() {
            inf = null;
            esq = null;
            dret = null;
        }

        NodeA(E i, NodeA e, NodeA d) {
            inf = i;
            esq = e;
            dret = d;
        }


        @Override
        public Object clone() {
            NodeA copia = null;
            try {
                copia = (NodeA) (super.clone());
                if(esq != null) copia.esq = (NodeA) esq.clone();
                if(dret != null) copia.dret = (NodeA) dret.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
            return copia;
        }

        private boolean hiEs(Comparable e) {

            if (e.compareTo(inf) < 0) {
                if (esq == null) return false;
                return esq.hiEs(e);
            } else if (e.compareTo(inf) > 0) {
                if (dret == null) return false;
                return dret.hiEs(e);
            } else {
                return true;
            }
        }


        private void inserir(Comparable e) throws ArbreException {
            if (e.compareTo(inf) < 0) {
                if (esq != null) {
                    esq.inserir(e);
                } else {
                    esq = new NodeA((E) e, null, null);
                }
            } else if (e.compareTo(inf) > 0) {
                if (dret != null) {
                    dret.inserir(e);
                } else {
                    dret = new NodeA((E) e, null, null);
                }
            } else {
                throw new ArbreException("l'element ja hi es");
            }
        }

        private NodeA esborrar(Comparable e) throws ArbreException {
            if (inf.compareTo((E) e) > 0) {
                if (esq != null) {
                    esq = esq.esborrar(e);
                    return this;
                } else {
                    throw new ArbreException("no hi és");
                }
            } else if (inf.compareTo((E) e) < 0) {
                if (dret != null) {
                    dret = dret.esborrar(e);
                    return this;
                } else {
                    throw new ArbreException("no hi és");
                }
            } else {
                if (esq != null && dret != null) {
                    inf = (E) dret.buscarMinim();
                    dret = dret.esborrar(inf);
                    return this;
                } else if (esq == null && dret == null) {
                    return null;
                } else if (esq == null) {
                    return dret;
                } else {
                    return esq;
                }
            }
        }

        private Comparable buscarMinim() {
            if (esq == null) return inf;
            NodeA aux = esq;
            while (aux.esq != null) {
                aux = aux.esq;
            }
            return aux.inf;
        }

        private int recompte() {
            int contador = 1;
            if (esq != null) {
                contador += esq.recompte();
            }

            if (dret != null) {
                contador += dret.recompte();
            }
            return contador;
        }
    }

    private NodeA arrel;
    private Queue<E> cua;

    @Override
    public Object clone() {
        AcbEnll copia = null;
        try {
            copia = (AcbEnll) (super.clone());
            if (arrel != null) {
                copia.arrel = (NodeA) (arrel.clone());
            }
        } catch (CloneNotSupportedException e) {
            return null;
        }
        return copia;
    }

    public int cardinalitat() {
        if (arrel == null) return 0;
        return arrel.recompte();
    }

    public void iniRecorregut(boolean sentit) {
        if (arrel.inf != null) {
            inordreR(arrel);
        }
        if (!sentit) {
            //cambiar sentido
            Stack<E> copia = new Stack<>();
            while (!cua.isEmpty()) {
                copia.push(cua.element());
                cua.remove();
            }
            while (!copia.isEmpty()) {
                cua.add(copia.pop());
            }
        }
    }

    private void inordreR(NodeA arrel) {
        if (arrel.esq != null) {
            inordreR(arrel.esq);
        }
        try {
            if (cua != null) {
                cua.add(arrel.inf);
            } else {
                cua = new LinkedList<>();
                cua.add(arrel.inf);
            }
        } catch (IllegalStateException e) {
            System.out.println("error inordreR: " + e.getMessage());
        }
        if (arrel.dret != null) {
            inordreR(arrel.dret);
        }
    }

    public boolean finalRecorregut() {
        try {
            return cua.isEmpty();
        }catch (Exception e){
            //no clar
            System.out.println("No s'ha invocat a inRecorregut");
            return true;
        }
    }

    public E segRecorregut() throws ArbreException {
        E resposta = null;
        if(cua == null){
            throw new ArbreException("Error cardinalitat");
        }
        resposta = cua.element();
        cua.remove();
        return resposta;
    }


    @Override
    public Comparable arrel() throws ArbreException {
        return (Comparable) arrel;
    }

    @Override
    public Acb fillEsquerre() {
        if (arrel != null) {
            Acb acb = new AcbEnll();
            ((AcbEnll) acb).arrel = arrel.esq;
            return acb;
        } else {
            return null;
        }
    }

    @Override
    public Acb fillDret() {
        if (arrel != null) {
            Acb acb = new AcbEnll();
            ((AcbEnll) acb).arrel = arrel.dret;
            return acb;
        } else {
            return null;
        }
    }

    @Override
    public boolean abBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        arrel = null;
    }

    @Override
    public void inserir(Comparable comparable) throws ArbreException {
        if (arrel == null) arrel = new NodeA((E) comparable, null, null);
        else arrel.inserir(comparable);
        cua = null;
    }

    @Override
    public void esborrar(Comparable comparable) throws ArbreException {
        if (arrel == null) throw new ArbreException("l'arbre és buit");
        arrel.esborrar(comparable);
        cua = null;
    }

    @Override
    public boolean membre(Comparable comparable) {
        if (arrel == null) return false;
        return arrel.hiEs(comparable);
    }
}
