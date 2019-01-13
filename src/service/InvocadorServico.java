package service;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import resource.RiotAPI;
import collection.*;

public class InvocadorServico {
	
	RiotAPI riot = new RiotAPI();
	private String resp = "";
	JSONParser parser;
	
	public Invocador BuscaInvocador(String server, String nomeInvocador) {
		Invocador invocador = new Invocador();
		resp = riot.request(server, "lol/summoner/v4/summoners/by-name/", nomeInvocador);
		if (!resp.equals("")) {
			try {
				parser = new JSONParser();
				JSONObject lolJSON;
				
				lolJSON = (JSONObject) parser.parse(resp);
				invocador.setIdConta((String)lolJSON.get("accountId"));
				invocador.setIdIconeInv((Long) lolJSON.get("profileIconId"));
                invocador.setIdInv((String)lolJSON.get("id"));
                invocador.setNivelInv((Long)lolJSON.get("summonerLevel"));
                String name = "";
                try {
                	name = new String(((String)lolJSON.get("name")).getBytes("ISO-8859-1"), "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
                
                invocador.setNomeInv(name);
                invocador.setServerInv(server);
            
                BuscaMaestrias(invocador.getIdInv(), "lol/champion-mastery/v4/champion-masteries/by-summoner/", server, invocador);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao buscar o invocador, verifique o nome e o servidor!", "Erro na busca!", JOptionPane.ERROR_MESSAGE);
		}
		return invocador;
	}
	
	private void BuscaMaestrias(String idInvocador,String endpoint, String server, Invocador invocador) {
		resp = riot.request(server, endpoint, idInvocador);
		if(!resp.equals("")) {
			try {
				
				JSONArray lolJSONArray;
				lolJSONArray = (JSONArray) parser.parse(resp);
				
				for (int i = 0; i < lolJSONArray.size(); i++) {
					JSONObject jsonObj = (JSONObject) lolJSONArray.get(i);
	                Maestria maestria = new Maestria();
	                maestria.setMaestriaCampId((Long)jsonObj.get("championId"));
	                maestria.setMaestriaCampLvl((Long)jsonObj.get("championLevel"));
	                maestria.setMaestriaCampPt((Long)jsonObj.get("championPoints"));
	                maestria.setBauCamp((boolean)jsonObj.get("chestGranted"));
	                maestria.setMaestriaCampNome(ChampPorID(Long.toString(maestria.getMaestriaCampId())));
	                invocador.addMaestrias(maestria);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao buscar o invocador, verifica o nome e o servidor!", "Erro na busca!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private String ChampPorID(String champID) {
		String champName = "";
		try {
			Object championOBJ = parser.parse(new FileReader("src/collection/champion.json"));
			JSONObject championJSONObj = (JSONObject) championOBJ;
			JSONObject campeoes = (JSONObject)championJSONObj.get("data");
	        JSONObject campeao = (JSONObject)campeoes.get((String) champID);
			champName = (String)campeao.get("name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return champName;
	}
	
}
