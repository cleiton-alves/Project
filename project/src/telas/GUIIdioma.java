	package telas;
	import javax.swing.*;

	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	import java.util.ResourceBundle;
	
public class GUIIdioma extends JFrame implements ActionListener {

	   private JButton bpt,bin,bsp;
	   public ResourceBundle bn = null;
	   private JLabel lport,ling,lspa;
	   private Icon port,ing,espan;
	   
		
		public GUIIdioma(){		

	      
	      Container c = getContentPane();
			setLayout(null);
			 
	      port  = new ImageIcon(getClass().getResource("/telas/br.png"));
	      ing   = new ImageIcon(getClass().getResource("/telas/ing.png"));
	      espan = new ImageIcon(getClass().getResource("/telas/espan.png"));

	      bpt = new JButton(port);
	      bsp = new JButton(espan);
	      bin = new JButton(ing);
	      
	      lport = new JLabel("Português");
	      ling = new JLabel("Español");
	      lspa = new JLabel("English");
	      

			
	      bpt.setBounds(20,30,75,65);
	      bsp.setBounds(130,30,75,65);
	      bin.setBounds(240,30,75,65);
	      
	      lport.setBounds(32,80,75,65);
	      ling.setBounds(150,80,75,65);
	      lspa.setBounds(265,80,75,65);

	      
	      
	      
	      bpt.setBorder(null);  
	      bsp.setBorder(null);
	      bin.setBorder(null);  
			
	      
	      c.add(bpt);
		  c.add(bin);
		  c.add(bsp);
	      c.add(lspa);
	      c.add(lport);
	      c.add(ling);
	      
	      
	      
	     
	     bpt.setBorder(null);  
	     bsp.setBorder(null);
	     bin.setBorder(null);      
	   
			
	      
	      bpt.addActionListener(this);
	      bsp.addActionListener(this);
	      bin.addActionListener(this);
			
	      setVisible(true);
	      setSize(350,170);
	      setResizable(true);
	      this.setLocationRelativeTo(null); 

	   
			
			
		}
		
		public void actionPerformed(ActionEvent evento){
			if(evento.getSource() == bpt){
				bn = ResourceBundle.getBundle("telas.Language", new Locale("pt", "BR"));

				GUILogin  obj = new GUILogin(bn);
				obj.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
	         dispose();
				
			}else if(evento.getSource() == bin){
				bn = ResourceBundle.getBundle("telas.Language", Locale.US);

				GUILogin  obj = new GUILogin(bn);
				obj.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
				dispose();
			
			}else if(evento.getSource() == bsp){
				bn = ResourceBundle.getBundle("telas.Language", new Locale("es","ES"));

				GUILogin  obj = new GUILogin(bn);
				obj.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
				dispose();				
			}
		}	
		
		
		
		public static void main (String args[]){
			
			    
			try {
	          //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				 UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel"); 
	        }
		
			catch (Exception e){
				e.printStackTrace();
	        }
	      
	      
	      GUIIdioma menu = new GUIIdioma();
			menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 

			
			
		}
		

	}





