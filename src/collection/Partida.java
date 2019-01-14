package collection;

public class Partida {
	private Long idPartida; // confere
	private Long idChampPartida; // confere
	private String nomeChampPartida; // confere
	private Boolean statusPartida; // confere
	private Long lvlChampPartida; // confere
	private Long kills; // confere
	private Long assists; // confere
	private Long deaths; // confere
	private Long tempoPartida; // confere
	private Long pSpellPartida; // confere
	private Long sSpellPartida; // confere
	
	public Boolean getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(Boolean statusPartida) {
		this.statusPartida = statusPartida;
	}
	public Long getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}
	public Long getIdChampPartida() {
		return idChampPartida;
	}
	public void setIdChampPartida(Long idChampPartida) {
		this.idChampPartida = idChampPartida;
	}
	public String getNomeChampPartida() {
		return nomeChampPartida;
	}
	public void setNomeChampPartida(String nomeChampPartida) {
		this.nomeChampPartida = nomeChampPartida;
	}
	public Long getLvlChamPartida() {
		return lvlChampPartida;
	}
	public void setLvlChamPartida(Long lvlChampPartida) {
		this.lvlChampPartida = lvlChampPartida;
	}
	public Long getKills() {
		return kills;
	}
	public void setKills(Long kills) {
		this.kills = kills;
	}
	public Long getAssists() {
		return assists;
	}
	public void setAssists(Long assists) {
		this.assists = assists;
	}
	public Long getDeaths() {
		return deaths;
	}
	public void setDeaths(Long deaths) {
		this.deaths = deaths;
	}
	public Long getTempoPartida() {
		return tempoPartida;
	}
	public void setTempoPartida(Long tempoPartida) {
		this.tempoPartida = tempoPartida;
	}
	public Long getpSpellPartida() {
		return pSpellPartida;
	}
	public void setpSpellPartida(Long pSpellPartida) {
		this.pSpellPartida = pSpellPartida;
	}
	public Long getsSpellPartida() {
		return sSpellPartida;
	}
	public void setsSpellPartida(Long sSpellPartida) {
		this.sSpellPartida = sSpellPartida;
	}
}
