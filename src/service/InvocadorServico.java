package service;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import collection.Invocador;
import collection.Maestria;
import collection.Partida;
import resource.RiotAPI;

public class InvocadorServico {

	RiotAPI riot = new RiotAPI();
	private String resp = "";
	JSONParser parser;

	public Invocador BuscaInvocador(String server, String nomeInvocador) {
		Invocador invocador = new Invocador();
		resp = riot.request(server, "lol/summoner/v4/summoners/by-name/", nomeInvocador + "?");
		if (!resp.equals("")) {
			try {
				parser = new JSONParser();
				JSONObject lolJSON;

				lolJSON = (JSONObject) parser.parse(resp);
				invocador.setIdConta((String) lolJSON.get("accountId"));
				invocador.setIdIconeInv((Long) lolJSON.get("profileIconId"));
				invocador.setIdInv((String) lolJSON.get("id"));
				invocador.setNivelInv((Long) lolJSON.get("summonerLevel"));
				String name = "";
				try {
					name = new String(((String) lolJSON.get("name")).getBytes("ISO-8859-1"), "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}

				invocador.setNomeInv(name);
				invocador.setServerInv(server);

				BuscaMaestrias(invocador.getIdInv() + "?", "lol/champion-mastery/v4/champion-masteries/by-summoner/",
						server, invocador);
				BuscaTodasPartidas(invocador.getIdConta() + "?endIndex=20&", "lol/match/v4/matchlists/by-account/",
						server, invocador);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao buscar o invocador, verifique o nome e o servidor!",
					"Erro na busca!", JOptionPane.ERROR_MESSAGE);
		}
		return invocador;
	}

	private void BuscaMaestrias(String idInvocador, String endpoint, String server, Invocador invocador) {
		resp = riot.request(server, endpoint, idInvocador);
		if (!resp.equals("")) {
			try {

				JSONArray lolJSONArray;
				lolJSONArray = (JSONArray) parser.parse(resp);

				for (int i = 0; i < lolJSONArray.size(); i++) {
					JSONObject jsonObj = (JSONObject) lolJSONArray.get(i);
					Maestria maestria = new Maestria();
					maestria.setMaestriaCampId((Long) jsonObj.get("championId"));
					maestria.setMaestriaCampLvl((Long) jsonObj.get("championLevel"));
					maestria.setMaestriaCampPt((Long) jsonObj.get("championPoints"));
					maestria.setBauCamp((boolean) jsonObj.get("chestGranted"));
					maestria.setMaestriaCampNome(ChampPorID(Long.toString(maestria.getMaestriaCampId())));
					invocador.addMaestrias(maestria);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao buscar o invocador, verifica o nome e o servidor!",
					"Erro na busca!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void BuscaTodasPartidas(String idConta, String endpoint, String server, Invocador invocador) {
		resp = riot.request(server, endpoint, idConta);
		try {
			JSONArray jsonArray;

			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(resp);

			jsonArray = (JSONArray) jsonObject.get("matches");

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonMatchObject = (JSONObject) jsonArray.get(i);
				Partida partida = new Partida();
				partida.setIdPartida((Long) jsonMatchObject.get("gameId"));
				partida.setIdChampPartida((Long) jsonMatchObject.get("champion"));
				partida.setNomeChampPartida(ChampPorID(Long.toString(partida.getIdChampPartida())));
				BuscaPartidaIndividual(partida.getIdPartida() + "?", "lol/match/v4/matches/", server, partida, invocador.getNomeInv());
				invocador.addPartidas(partida);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void BuscaPartidaIndividual(String idPartida, String endpoint, String server, Partida partida, String nomeInvocador) {
		Long participantId = 20L;
		resp = riot.request(server, endpoint, idPartida);
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(resp);
			partida.setTempoPartida((Long) jsonObject.get("gameDuration"));

			JSONArray jsonArray; 

			jsonArray = (JSONArray) jsonObject.get("participantIdentities");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonParticipant = (JSONObject) jsonArray.get(i);
				JSONObject jsonPlayer = (JSONObject) jsonParticipant.get("player");
				if (jsonPlayer.get("summonerName").toString().equals(nomeInvocador)) {
					participantId = (Long)jsonParticipant.get("participantId");
				}
			}
			
			jsonArray = (JSONArray) jsonObject.get("participants");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonParticipant = (JSONObject) jsonArray.get(i);
				if (jsonParticipant.get("participantId").toString().equals(Long.toString(participantId))) {
					partida.setpSpellPartida((Long)jsonParticipant.get("spell1Id"));
					partida.setsSpellPartida((Long)jsonParticipant.get("spell2Id"));
					JSONObject playerStats = (JSONObject) jsonParticipant.get("stats");
					partida.setStatusPartida((Boolean)playerStats.get("win"));
					partida.setLvlChamPartida((Long) playerStats.get("champLevel"));
					partida.setKills((Long) playerStats.get("kills"));
					partida.setDeaths((Long) playerStats.get("deaths"));
					partida.setAssists((Long) playerStats.get("assists"));
					
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String ChampPorID(String champID) {
		String champName = "";
		try {
			Object championOBJ = parser.parse(new FileReader("src/collection/champion.json"));
			JSONObject championJSONObj = (JSONObject) championOBJ;
			JSONObject campeoes = (JSONObject) championJSONObj.get("data");
			JSONObject campeao = (JSONObject) campeoes.get((String) champID);
			champName = (String) campeao.get("name");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return champName;
	}

}
