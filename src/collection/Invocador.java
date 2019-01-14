package collection;

import java.util.ArrayList;
import java.util.List;

public class Invocador {
    
	private String nomeInv;
    private String serverInv;
    private long nivelInv;
    private String idInv;
    private String idConta;
    private long idIconeInv;
    
    private List<Maestria> maestrias = new ArrayList<>();
    
    private List<Partida> partidas = new ArrayList<>();
    
    public List<Partida> getPartidas(){
    	return partidas;
    }
    
    public List<Maestria> getMaestrias() {
        return maestrias;
    }

    public void addPartidas(Partida partida) {
    	this.partidas.add(partida);
    }
    
    public void addMaestrias(Maestria maestria) {
        this.maestrias.add(maestria);
    }

    public String getNomeInv() {
        return nomeInv;
    }

    public void setNomeInv(String nomeInv) {
        this.nomeInv = nomeInv;
    }

    public String getServerInv() {
        return serverInv;
    }

    public void setServerInv(String serverInv) {
        this.serverInv = serverInv;
    }

    public long getNivelInv() {
        return nivelInv;
    }

    public void setNivelInv(long nivelInv) {
        this.nivelInv = nivelInv;
    }

    public String getIdInv() {
        return idInv;
    }

    public void setIdInv(String idInv) {
        this.idInv = idInv;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public long getIdIconeInv() {
        return idIconeInv;
    }

    public void setIdIconeInv(long idIconeInv) {
        this.idIconeInv = idIconeInv;
    }
}
