package resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;
import config.*;
public class RiotAPI {
	private String key = Config.LOL_API_KEY;
	
	// o endpoint é oq vem depois do https://server.api.riotgames.com/
	public String request(String server, String endpoint, String param) {
		String uri = "https://" + server + ".api.riotgames.com/" + endpoint + param + "?api_key=" + key;
		String resp = "";
		try {
			URL retornoAPI = new URL(uri);
			BufferedReader bf = new BufferedReader(new InputStreamReader(retornoAPI.openStream()));
			resp = bf.readLine();
		} catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "Erro:" + e, "Erro na busca do invocador", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e, "Erro na busca do invocador", JOptionPane.ERROR_MESSAGE);
        }
		
		return resp;
	}
}
