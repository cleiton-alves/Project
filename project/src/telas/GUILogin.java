package telas;
import javax.swing.*;

import cripto.SistemaAcesso;

import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;


public class GUILogin extends JFrame implements ActionListener {
	private JLabel llogin, lsenha,limage,llogo;
	private JTextField tlogin;
	private JPasswordField tsenha;
	private JButton bok,bsair;
	private Icon image,logo;
	private ResourceBundle bn = null;
	
	public GUILogin(ResourceBundle bn){

		this.bn = bn;
		
		Container c = getContentPane();
		c.setLayout(null);
		
		image  = new ImageIcon(getClass().getResource("/telas/iconlogin.png"));
		limage = new JLabel(image);
		
		logo  = new ImageIcon(getClass().getResource("/telas/logo.png"));
		llogo = new JLabel(logo);
		
		bok  = new JButton(bn.getString("guilogin.ok"));
		bsair  = new JButton(bn.getString("guilogin.sair"));
		
		llogin = new JLabel(bn.getString("guilogin.login"));
		lsenha = new JLabel(bn.getString("guilogin.senha"));
		
		tlogin = new JTextField(15);
		tsenha = new JPasswordField(15);		
		
		
		// Setbounds... localização dos textfields, botoes e labels  na tela
		 
		limage.setBounds(-60,-35,250,310);

		llogo.setBounds(310,-100,250,310);
		llogin.setBounds(180,105,70,30);
		tlogin.setBounds(250,110,120,20);
		lsenha.setBounds(180,140,70,20);
		tsenha.setBounds(250,140,120,20);
		bok.setBounds(240,200,60,20);
		bsair.setBounds(310,200,60,20);
		 
		
		
		
		c.add(llogin);
		c.add(tlogin);
		c.add(lsenha);
		c.add(tsenha);
		c.add(bok);
		c.add(bsair);
		c.add(limage);
		c.add(llogo);
		
		bok.addActionListener(this);
		bsair.addActionListener(this);
		tsenha.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if(keyEvent.getKeyCode() == 10){
					logar();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		setTitle(bn.getString("guilogin.titulo"));
		setVisible(true);
		setSize(500,285);
		setResizable(false);
		this.setLocationRelativeTo(null); 
	}
	
	public void actionPerformed(ActionEvent evento){
		if(evento.getSource() == bok){
			logar();
		}else
			if(evento.getSource() == bsair){
				
				System.exit(0);
		}
	}	
	
	@SuppressWarnings("deprecation")
	public void logar(){
		SistemaAcesso sist = new SistemaAcesso();
		sist.addArrayList();		
		
		try {
			if(sist.buscarLogin(tlogin.getText(),tsenha.getText())){
				
				GUIMenuPrincipal menu = new GUIMenuPrincipal(bn, sist.getPerfil(),sist.getAgencia());
				menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
				dispose();
			}else{						
				tsenha.setText("");
				tlogin.setText("");
				JOptionPane.showMessageDialog(null,"Login e/ou senha incorretos");
				tlogin.requestFocus();
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

