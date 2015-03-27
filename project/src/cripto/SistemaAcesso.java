package cripto;
import java.io.File; 
import java.io.FileReader; 
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SistemaAcesso {
	
	//ARRAYLIST PRINCIPAL
	private  ArrayList <String> vetLogin = new ArrayList<String>();
	private  ArrayList <String> vetSenha = new ArrayList<String>();		
	private  ArrayList <String> vetPerfil = new ArrayList<String>();	
	private  ArrayList <Integer> vetAgencia = new ArrayList<Integer>();	
	private String perfil;
	private int codagencia;
	
		
	public SistemaAcesso(){
		
	}
			
		public String cripitografarSenhas() throws Exception{ 
			
			
			String cript = JOptionPane.showInputDialog(null,"Digite  senha a ser criptografada");
	
			
			String saida = "";
			byte[] bMsgCifrada = null;	
			byte[] bMsgClara = cript.getBytes("ISO-8859-1");
			
			CryptoDummy cdummy = new CryptoDummy(); 
			//cdummy.geraChave(new File ("chave.dummy")); 						
			cdummy.geraCifra(bMsgClara, new File ("chave.dummy")); 

			bMsgCifrada = cdummy.getTextoCifrado(); 
			
			
			
			saida = (new String (bMsgCifrada, "ISO-8859-1"));	
		System.out.println(saida);
			return saida;
		}
		
		
		/*
			File cifra = new File("texto_cifrado_dummy.txt"); 
			
			try{ 
				FileWriter fw = new FileWriter(cifra); 
				String a = new String (bMsgCifrada, "ISO-8859-1");
				fw.write(a);
				fw.flush(); 
			}catch(IOException ex){
				ex.printStackTrace(); 
			}			
			 
			sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));			

			*/
		
			//Método que lê o arquivo cifrado e joga os dados nos ArrayLists, cujo são atributos 
			
			public void addArrayList(){
			
			String textoArquivoCifr ="";
			String textoArquivoParaDescrip ="";
			
				try{ 				 
					Scanner input = new Scanner(new FileReader("login_senha.txt"));				
					while(input.hasNext()){
						textoArquivoCifr= input.next();											
					 	String linha[] = textoArquivoCifr.split(",");					
					 	vetLogin.add(linha[0]);
					 	vetSenha.add(linha[1]);	
					 	vetPerfil.add(linha[2]);
					 	vetAgencia.add(Integer.parseInt(linha[3]));				 	
					}
					
					input.close();
				
				} catch (Exception e) { 				
					System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
				} 

			}
			
			
			public boolean buscarLogin(String login,String senha) throws Exception{
				
				final int n = vetLogin.size();
				int inicio=0,fim=n-1,meio,comp = 0;
				String senhadescrip;
					while (inicio<=fim){
						meio=(inicio+fim)/2;	
						if (login.equals( vetLogin.get(meio))){
							senhadescrip = descriptSenha(vetSenha.get(meio));
							
							if(senha.equals(senhadescrip)){								
								perfil = vetPerfil.get(meio);
								codagencia = vetAgencia.get(meio);								
								return true;
								
								
							}		
							
						}else
							 comp = vetLogin.get(meio).compareTo(login);					
						if (comp < 0){
								inicio = meio+1;
							}else{
								
									fim = meio-1;
							}
					}
					return false;
				}
				
				
			public String descriptSenha(String senha) throws Exception{
				
				CryptoDummy cdummy = new CryptoDummy(); 		
				cdummy.geraDecifra(senha.getBytes("ISO-8859-1"), new File ("chave.dummy"));			
			
				byte[] bMsgDecifrada = cdummy.getTextoDecifrado(); 							
				String sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1")); 			

				return sMsgDecifrada;
			}
			
			
			public String getPerfil() {
				return perfil;
			}

			public void setPerfil(String perfil) {
				this.perfil = perfil;
			}

			public int getAgencia() {
				return codagencia;
			}

			public void setAgencia(int agencia) {
				this.codagencia = agencia;
			}


}
	
	

