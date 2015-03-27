package debito;

import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.util.ArrayList;
import java.util.Scanner;


public class SistemaDebito {


		
		//ARRAYLIST PRINCIPAL
		private  ArrayList <String> vetBanco 		= new ArrayList<String>();
		private  ArrayList <String> vetTitular 		= new ArrayList<String>();
		private  ArrayList <String> vetCcorrente	= new ArrayList<String>();		
		private  ArrayList <String> vetAgencia		= new ArrayList<String>();
		private  ArrayList <String> vetCpf			= new ArrayList<String>();	
		private  ArrayList <String> vetTelefone 	= new ArrayList<String>();

			
		public SistemaDebito(){
		
			String dados ="";
			
			try{ 				 
				Scanner input = new Scanner(new FileReader("sistema_debito.txt"));		
				while(input.hasNext()){
				
					dados= input.next();											
			 		String linha[] = dados.split(",");					
			 		vetTitular.add(linha[0]);
			 		vetCcorrente.add(linha[1]);
			 		vetAgencia.add(linha[2]);
					vetCpf.add(linha[3]);
					vetTelefone.add(linha[4]);
					vetBanco.add(linha[5]);
					

				}
			
				input.close();
		
			} catch (Exception e) { 				
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
			} 
		
		}
		
		
			
			
				
		public boolean verficarDados(String banco,String titular,String ccorrente, String agencia, String cpf, String telefone) throws Exception{

			final int n = vetTitular.size();
			int inicio=0,fim=n-1,meio,comp = 0;
			while (inicio<=fim){
				meio=(inicio+fim)/2;	
				if (titular.equals( vetTitular.get(meio))){		
					if(ccorrente.equals(vetCcorrente.get(meio)) && telefone.equals(vetTelefone.get(meio)) 
							&& agencia.equals(vetAgencia.get(meio)) && cpf.equals(vetCpf.get(meio)) && banco.equals(vetBanco.get(meio))){
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
		
		




