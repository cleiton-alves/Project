package credito;

import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.util.ArrayList;
import java.util.Scanner;


public class SistemaCredito {

		
		//ARRAYLIST PRINCIPAL		
		private  ArrayList <String> vetCartao 	= new ArrayList<String>();
		private  ArrayList <String> vetTitular 	= new ArrayList<String>();
		private  ArrayList <String> vetCpf 		= new ArrayList<String>();		
		private  ArrayList <String> vetNumCart	= new ArrayList<String>();
		private  ArrayList <String> vetDataVal	= new ArrayList<String>();	
		private  ArrayList <String> vetCodSeg 	= new ArrayList<String>();

			
		public SistemaCredito(){
			String dados ="";
			
			try{ 				 
				Scanner input = new Scanner(new FileReader("sistema_credito.txt"));		
				while(input.hasNext()){
				
					dados= input.next();											
			 		String linha[] = new String[7];
			 		linha = dados.split(",");					
			 		vetCartao.add(linha[0]);
			 		vetTitular.add(linha[1]);
			 		vetCpf.add(linha[2]);
			 		vetNumCart.add(linha[3]);
					vetDataVal.add(linha[4]);
					vetCodSeg.add(linha[5]);					
				}
			
				input.close();
		
			} catch (Exception e) { 				
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
				e.printStackTrace();
			} 			
		}
			
				
		public boolean verficarDados(String cartao,String titular,String cpf, String numero, String data, String cod) throws Exception{

			final int n = vetTitular.size();
			int inicio=0,fim=n-1,meio,comp = 0;
			while (inicio<=fim){
				meio=(inicio+fim)/2;	
				if (titular.equals( vetTitular.get(meio))){		
					if(cpf.equals(vetCpf.get(meio)) && numero.equals(vetNumCart.get(meio)) 
							&& data.equals(vetDataVal.get(meio)) && cod.equals(vetCodSeg.get(meio)) && cartao.equals(vetCartao.get(meio))){
						return true;	
					}		

				}else
					comp = vetTitular.get(meio).compareTo(titular);					
				if (comp < 0){
					inicio = meio+1;
				}else{

					fim = meio-1;
				}
			}
			return false;
		}

		
	}
		
		




