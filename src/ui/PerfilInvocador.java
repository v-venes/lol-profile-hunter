package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import collection.Invocador;
import collection.Maestria;

public class PerfilInvocador extends JFrame{
	
	private static PerfilInvocador frame;
	private static Invocador invocador;
	JPanel pnPerfil, pnMaestrias, pnSpMaestrias, pnHistPartidas, pnSpHistPartidas;
	JScrollPane spMaestrias, spHistPartidas;
	JLabel lblNomeInvocador, lblNivelInvocador, lblMaestrias, lblHistPartidas;
	JLabel lblImgInvocador, lblImgChamp;
	
	public PerfilInvocador(Invocador invocador) {
		this.invocador = invocador;
		InicializarComponentes();
	}
	
	private void InicializarComponentes() {
		setTitle("Perfil de " + invocador.getNomeInv());
		setBounds(0,0,1024,768);
		getContentPane().setBackground(new Color(38, 38, 38));
		setLayout(null);
		setResizable(false);
		
		// - painel invocador
		
		pnPerfil= new JPanel();
		pnPerfil.setLayout(null);
		pnPerfil.setSize(380, 140);
		pnPerfil.setLocation(75, 35);
		pnPerfil.setBackground(new Color(50, 50, 50));
		
		lblNomeInvocador = new JLabel(invocador.getNomeInv());
		lblNomeInvocador.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
		lblNomeInvocador.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeInvocador.setForeground(Color.WHITE);
		lblNomeInvocador.setSize(230,35);
		lblNomeInvocador.setLocation(150, 25);
		
		lblNivelInvocador = new JLabel("Lvl: " + invocador.getNivelInv());
		lblNivelInvocador.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 36));
		lblNivelInvocador.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelInvocador.setForeground(Color.WHITE);
		lblNivelInvocador.setSize(230,45);
		lblNivelInvocador.setLocation(150, 70);
		
		String uri = "src/assets/profileicon/"+ invocador.getIdIconeInv() + ".png";
		lblImgInvocador = new JLabel(GetImage(uri, 130, 130));
		lblImgInvocador.setSize(130,130);
		lblImgInvocador.setLocation(5, 5);
		
		pnPerfil.add(lblNomeInvocador);
		pnPerfil.add(lblNivelInvocador);
		pnPerfil.add(lblImgInvocador);
		
		add(pnPerfil);
		
		// - painel maestrias
		
		pnMaestrias = new JPanel();
		pnMaestrias.setLayout(null);
		pnMaestrias.setSize(470,530);
		pnMaestrias.setLocation(35, 195);
		pnMaestrias.setBackground(new Color(50,50,50));
		
		lblMaestrias = new JLabel("Maestrias");
		lblMaestrias.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
		lblMaestrias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaestrias.setSize(470, 35);
		lblMaestrias.setLocation(0, 10);
		lblMaestrias.setForeground(Color.WHITE);
		
		pnSpMaestrias = new JPanel();
		pnSpMaestrias.setLayout(null);
		pnSpMaestrias.setPreferredSize(new Dimension(442, 1000));
		pnSpMaestrias.setLocation(10,10);
		pnSpMaestrias.setBackground(new Color(50,50,50));
		
		int maestriaYPos = 0;
		
		for(Maestria maestria : invocador.getMaestrias()) {
			
			JPanel pnMaestria = new JPanel();
			pnMaestria.setLayout(null);
			pnMaestria.setBackground(new Color(63,63,63));
			pnMaestria.setLocation(5,maestriaYPos+10);
			pnMaestria.setSize(450,90);
			
			String champNome = maestria.getMaestriaCampNome().replaceAll(" ", "");
			champNome = champNome.replace("'", "");
			champNome = champNome.replace(".", "");
			uri = "src/assets/champion/"+champNome +".png";
			JLabel lblImgChamp = new JLabel(GetImage(uri, 80, 80));
			lblImgChamp.setSize(80,80);
			lblImgChamp.setLocation(5,5);
			
			JLabel lblNomeChamp = new JLabel(maestria.getMaestriaCampNome());
			lblNomeChamp.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
			lblNomeChamp.setForeground(Color.WHITE);
			lblNomeChamp.setHorizontalAlignment(SwingConstants.LEFT);
			lblNomeChamp.setLocation(100, 15);
			lblNomeChamp.setSize(260,35);
			
			JLabel lblNvChamp = new JLabel("Nivel: "+maestria.getMaestriaCampLvl());
			lblNvChamp.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblNvChamp.setForeground(Color.WHITE);
			lblNvChamp.setHorizontalAlignment(SwingConstants.LEFT);
			lblNvChamp.setLocation(100, 40);
			lblNvChamp.setSize(120,35);
			
			JLabel lblPontosChamp = new JLabel(maestria.getMaestriaCampPt() + " Pontos");
			lblPontosChamp.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblPontosChamp.setForeground(Color.WHITE);
			lblPontosChamp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPontosChamp.setLocation(215, 25);
			lblPontosChamp.setSize(210,35);
			
			pnMaestria.add(lblPontosChamp);
			pnMaestria.add(lblNvChamp);
			pnMaestria.add(lblNomeChamp);
			pnMaestria.add(lblImgChamp);
			
			maestriaYPos += 100;
			
			pnSpMaestrias.add(pnMaestria);
			pnSpMaestrias.setPreferredSize(new Dimension(442, maestriaYPos + 10));
			
		}
		
		//for each java 8
		//invocador.getMaestrias().forEach((Maestria maestria) ->{ });
		
		spMaestrias = new JScrollPane(pnSpMaestrias);
		spMaestrias.setSize(460,465);
		spMaestrias.setLocation(5, 60);
		spMaestrias.getVerticalScrollBar().setUnitIncrement(16);
		setVisible(true);
		
		
		pnMaestrias.add(spMaestrias);
		pnMaestrias.add(lblMaestrias);
		
		add(pnMaestrias);
		
		// - painel historico de partidas
		
		pnHistPartidas = new JPanel();
		pnHistPartidas.setLayout(null);
		pnHistPartidas.setSize(470,690);
		pnHistPartidas.setLocation(520, 35);
		pnHistPartidas.setBackground(new Color(50, 50, 50));
		
		lblHistPartidas = new JLabel("Histórico de Partidas");
		lblHistPartidas.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
		lblHistPartidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistPartidas.setForeground(Color.WHITE);
		lblHistPartidas.setSize(470,35);
		lblHistPartidas.setLocation(0, 10);
		
		pnSpHistPartidas = new JPanel();
		pnSpHistPartidas.setLayout(null);
		pnSpHistPartidas.setPreferredSize(new Dimension(442, 1000));
		pnSpHistPartidas.setLocation(10,10);
		pnSpHistPartidas.setBackground(new Color(50,50,50));
		
		int partidaYPos = 0;
		//----------- historico partidas
		for (int i = 0; i <= 10; i++) {
			JPanel pnPartida = new JPanel();
			pnPartida.setLayout(null);
			pnPartida.setSize(450,130);
			pnPartida.setLocation(0,partidaYPos + 10);
			pnPartida.setBackground(new Color(63, 63, 63));
			
			JLabel lblImgChamp = new JLabel(GetImage("src/assets/champion/Sona.png", 120, 120));
			lblImgChamp.setLocation(5, 5);
			lblImgChamp.setSize(120, 120);
			
			JLabel lblImgFeitico1 = new JLabel(GetImage("src/assets/summonerspell/SummonerFlash.png", 45, 45));
			lblImgFeitico1.setLocation(135,15);
			lblImgFeitico1.setSize(45,45);
			
			JLabel lblImgFeitico2 = new JLabel(GetImage("src/assets/summonerspell/SummonerDot.png", 45, 45));
			lblImgFeitico2.setLocation(135,70);
			lblImgFeitico2.setSize(45,45);
			
			JLabel lblNomeChamp = new JLabel("Sona");
			lblNomeChamp.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblNomeChamp.setForeground(Color.WHITE);
			lblNomeChamp.setLocation(200, 10);
			lblNomeChamp.setSize(100, 35);
			
			JLabel lblLvlChamp = new JLabel("Lvl 18");
			lblLvlChamp.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblLvlChamp.setForeground(Color.WHITE);
			lblLvlChamp.setLocation(200, 45);
			lblLvlChamp.setSize(130, 35);
			
			JLabel lblKDA = new JLabel("0/0/0");
			lblKDA.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblKDA.setForeground(Color.WHITE);
			lblKDA.setLocation(200, 80);
			lblKDA.setSize(130, 35);
			
			JLabel lblResultado = new JLabel("Vitória");
			lblResultado.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
			lblResultado.setForeground(Color.WHITE);
			lblResultado.setLocation(320, 25);
			lblResultado.setSize(130, 40);
			
			JLabel lblTempo = new JLabel("00:00");
			lblTempo.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 24));
			lblTempo.setForeground(Color.WHITE);
			lblTempo.setLocation(340, 60);
			lblTempo.setSize(100, 35);
			
			pnPartida.add(lblNomeChamp);
			pnPartida.add(lblLvlChamp);
			pnPartida.add(lblKDA);
			pnPartida.add(lblResultado);
			pnPartida.add(lblTempo);
			pnPartida.add(lblImgFeitico1);
			pnPartida.add(lblImgFeitico2);
			pnPartida.add(lblImgChamp);
			
			partidaYPos += 140;
			
			pnSpHistPartidas.add(pnPartida);
			pnSpHistPartidas.setPreferredSize(new Dimension(442, partidaYPos + 10));
			
		}
		//------------
		
		spHistPartidas = new JScrollPane(pnSpHistPartidas);
		spHistPartidas.setSize(460,625);
		spHistPartidas.setLocation(5, 60);
		spHistPartidas.getVerticalScrollBar().setUnitIncrement(16);
		
		
		pnHistPartidas.add(lblHistPartidas);
		pnHistPartidas.add(spHistPartidas);
		
		add(pnHistPartidas);
		
		
		
	}
	
	private ImageIcon GetImage(String uri, int width, int height) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(uri));
		} catch (IOException e) {
		    System.out.println(uri);
			e.printStackTrace();
		    
		}
		Image dimg = img.getScaledInstance(width, height,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		return imageIcon;
	}
	

}
