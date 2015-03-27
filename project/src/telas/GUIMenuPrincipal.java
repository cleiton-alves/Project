package telas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class GUIMenuPrincipal extends JFrame {

	private JButton cadastro;
	private JLabel  fundo;
	private ResourceBundle bn = null;
	private Icon iconfundo,iconcliente,iconcarro,iconajuda,iconrelatorio,iconsobre,iconsair,iconconsultar,icondevolver;
	public String perfil;
	public int codagencia;


	public GUIMenuPrincipal(final ResourceBundle bn, final String perfil, final int agencia){
		this.perfil = perfil;
		this.codagencia = agencia;		
		Container cont = getContentPane();	
		
		JPanel pane = new JPanel();
		
		iconfundo = new ImageIcon(getClass().getResource("/telas/background.png"));
		iconcliente = new ImageIcon(getClass().getResource("/telas/cliente.png"));
		iconcarro = new ImageIcon(getClass().getResource("/telas/carro.png"));
		iconrelatorio = new ImageIcon(getClass().getResource("/telas/relatorio.png"));
		iconsobre = new ImageIcon(getClass().getResource("/telas/sobre.png"));
		iconajuda = new ImageIcon(getClass().getResource("/telas/ajuda.png"));
		iconsair = new ImageIcon(getClass().getResource("/telas/sair.png"));
		iconconsultar = new ImageIcon(getClass().getResource("/telas/consultar.png"));
		icondevolver = new ImageIcon(getClass().getResource("/telas/devolver.png"));
        
        
        
		fundo = new JLabel(iconfundo);

		
        pane.add(fundo);
        cont.add(fundo);
        this.setState(JFrame.NORMAL) ;
        

		 this.bn = bn;	
		//Menu arquivo
		JMenu arquivoMenu = new JMenu( bn.getString("guimenuprin.arquivo" )); 
		arquivoMenu.setMnemonic( bn.getString("guimenuprin.mnmonic_arquivo" ).charAt(0)); 

		JMenuItem sobreItem = new JMenuItem( bn.getString("guimenuprin.sobre" ),iconsobre); 
		sobreItem.setMnemonic( bn.getString("guimenuprin.mnmonic_sobre" ).charAt(0) ); 
		arquivoMenu.add( sobreItem ); 
		sobreItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				JOptionPane.showMessageDialog( GUIMenuPrincipal.this,bn.getString("guimenuprin.msgsobre" ) , 
						bn.getString("guimenuprin.sobre") , JOptionPane.PLAIN_MESSAGE ); 
			} 
		} 

				); 
		JMenuItem sairItem = new JMenuItem( bn.getString("guimenuprin.sair" ),iconsair); 
		sairItem.setMnemonic(bn.getString("guimenuprin.mnmonic_sair" ).charAt(0) );
		arquivoMenu.add( sairItem );  
		sairItem.addActionListener( 
				new ActionListener(){ 

					public void actionPerformed( ActionEvent event){ 
						System.exit( 0 ); 
					} 
				} 
				); 

		JMenuBar bar = new JMenuBar(); 
		setJMenuBar( bar ); 
		
		
		//Menu ajuda
		
		JMenu ajudaMenu = new JMenu ( bn.getString("guimenuprin.ajuda" )); 
		ajudaMenu.setMnemonic(bn.getString("guimenuprin.mnmonic_ajuda" ).charAt(0) );
		JMenuItem faleItem = new JMenuItem( bn.getString("guimenuprin.faleconosco"),iconajuda);
		faleItem.setMnemonic(bn.getString("guimenuprin.mnmonic_faleconosco" ).charAt(0) );
		ajudaMenu.add(faleItem);
		faleItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				JOptionPane.showMessageDialog( GUIMenuPrincipal.this,bn.getString("guimenuprin.msgfaleconosco"),  
						bn.getString("guimenuprin.suporte") , JOptionPane.PLAIN_MESSAGE ); 
			} 
		} );
		
		
		
		
		// Menu cliente
		
		JMenu clienteMenu = new JMenu ( bn.getString("guimenuprin.cliente" )); 
		clienteMenu.setMnemonic(bn.getString("guimenuprin.mnmonic_cliente" ).charAt(0) );
		
		JMenuItem gerenciarclienteItem = new JMenuItem( bn.getString("guimenuprin.gerencliente" ),iconcliente);
		gerenciarclienteItem.setMnemonic(bn.getString("guimenuprin.mnmonic_gerencli" ).charAt(0) );
		clienteMenu.add(gerenciarclienteItem);
		gerenciarclienteItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				
				GUIGerenciarCliente cli = new GUIGerenciarCliente(bn);
			}
			
		} );
		
		
		
		
		
		//  menu automovel		
		
		JMenu autoMenu = new JMenu ( bn.getString("guimenuprin.auto" )); 
		autoMenu.setMnemonic(bn.getString("guimenuprin.mnmonic_automovel" ).charAt(0) );
		
		// permissão p/ supervisor
		if(perfil.equals("adm")){
			
		JMenuItem gerenciarautoItem = new JMenuItem( bn.getString("guimenuprin.gerenauto" ),iconcarro);
		gerenciarautoItem.setMnemonic(bn.getString("guimenuprin.mnmonic_gerenauto" ).charAt(0) );
		autoMenu.add(gerenciarautoItem);
		gerenciarautoItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				
				GUIGerenciarAutomovel cli = new GUIGerenciarAutomovel(bn);
			}
			
		} );
		
		}
		
		JMenuItem consultaItem = new JMenuItem( bn.getString("guimenuprin.consauto" ),iconconsultar);
		consultaItem.setMnemonic(bn.getString("guimenuprin.mnmonic_automovel" ).charAt(0) );
		autoMenu.add(consultaItem);
		consultaItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				GUIConsultarAutomovel c = new GUIConsultarAutomovel(bn,agencia);				
			}
			
		} );
		
		JMenuItem devolverItem = new JMenuItem( bn.getString("guimenuprin.devolvauto" ),icondevolver);
		devolverItem.setMnemonic(bn.getString("guimenuprin.mnmonic_devolauto" ).charAt(0) );
		autoMenu.add(devolverItem);
		devolverItem.addActionListener(new ActionListener(){ 
			public void actionPerformed( ActionEvent event ){ 
				GUIDevolverAutomovel a = new GUIDevolverAutomovel(bn,agencia);

				
			}
			
		} );
		
		
						
				// menu relatorio
				
			JMenu relatMenu = new JMenu ( bn.getString("guimenuprin.relatorio" )); 
			relatMenu.setMnemonic(bn.getString("guimenuprin.mnmonic_relatorio" ).charAt(0) );
				
			JMenuItem relatdiaItem = new JMenuItem( bn.getString("guimenuprin.locdiaria" ),iconrelatorio);
			relatdiaItem.setMnemonic(bn.getString("guimenuprin.mnmonic_locdiaria" ).charAt(0) );
			relatMenu.add(relatdiaItem);
			relatdiaItem.addActionListener(new ActionListener(){ 
				public void actionPerformed( ActionEvent event ){ 
				
					GUIRelatorio c = new GUIRelatorio(bn);
				}
					
			} );
				
				
				
				
				
				// Adicionando menus na barra
				
				
				bar.add( arquivoMenu );
				bar.add( clienteMenu );
				bar.add( autoMenu );
				bar.add( relatMenu );
				bar.add( ajudaMenu );
				 

		setTitle(bn.getString("guimenuprin.titulo"));
		setVisible(true);
		setSize(1050,740);
		setResizable(true);
		this.setLocationRelativeTo(null); 
		
	
	}
	
} 

	







