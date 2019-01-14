package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import collection.Invocador;
import service.InvocadorServico;

public class BuscaInvocador extends JFrame{
	
	JLabel lblTitulo, lblInvocador, lblRegiao;
	JTextField txtInvocador;
	JComboBox cbRegiao;
	JButton btnPesquisa;
	
	private static BuscaInvocador frame;
	
	public BuscaInvocador() {
		InicializarComponentes();
		DefinirEventos();
	}
	
	private void InicializarComponentes() {
		String[] servers = {"br", "na", "eu", "kr", "jp"};
		
		setTitle("Profile Hunter - League of Legends");
		getContentPane().setBackground(new Color(38, 38, 38));
		setBounds(0,0,480,370);
		setLayout(null);
		setResizable(false);
		
		
		lblTitulo = new JLabel("Profile Hunter");
		lblTitulo.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 28));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(150,40,200,30);
		
		lblInvocador = new JLabel("Nome de invocador:");
		lblInvocador.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 16));
		lblInvocador.setForeground(Color.WHITE);
		lblInvocador.setBounds(60,120,170,20);
		
		lblRegiao = new JLabel("Região:");
		lblRegiao.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 16));
		lblRegiao.setForeground(Color.WHITE);
		lblRegiao.setBounds(160,170,65,20);
		
		txtInvocador = new JTextField();
		txtInvocador.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 12));
		txtInvocador.setBounds(235,120,190,25);
		
		cbRegiao = new JComboBox(servers);
		cbRegiao.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 12));
		cbRegiao.setBounds(235,170,75,25);
		
		btnPesquisa = new JButton("Buscar");
		btnPesquisa.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 22));
		btnPesquisa.setBackground(new Color(35, 178, 255));
		btnPesquisa.setForeground(Color.WHITE);
		btnPesquisa.setBounds(170,240, 135,45);
		
		add(lblTitulo);
		add(lblInvocador);
		add(lblRegiao);
		add(txtInvocador);
		add(cbRegiao);
		add(btnPesquisa);
		
		
	}
	
	private void DefinirEventos() {
		btnPesquisa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtInvocador.getText().equals("")) {
					String server = (String)cbRegiao.getSelectedItem();
					String nomeInvocador = txtInvocador.getText().replace(" ", "%20");
					nomeInvocador = nomeInvocador.replace("ó", "%C3%B3");
					if(server.equals("br")) {
						server = "br1";
					}
					InvocadorServico invocadorServico = new InvocadorServico();
					Invocador invocador = new Invocador();
					invocador = invocadorServico.BuscaInvocador(server, nomeInvocador);
					System.out.println(invocador);
					if(invocador != null) {
						AbrirPerfil(invocador);
					}else {
						System.out.println("null, " + invocador);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Digite um nome de invocador!!", "Digite um nome", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	private void AbrirPerfil(Invocador invocador) {
		PerfilInvocador frame = new PerfilInvocador(invocador);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((tela.width - frame.getSize().width) / 2, (tela.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				frame = new BuscaInvocador();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((tela.width - frame.getSize().width) / 2, (tela.height - frame.getSize().height) / 2);
				frame.setVisible(true);
			}
		});
		
		
	}

}
