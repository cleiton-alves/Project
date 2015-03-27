package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import objetos.Cliente;
import objetos.Pagamento;

public class DAOPagamento {
	
	AcessoBD bd = new AcessoBD();
	
	public void cadastrarPagamentoCredito(String cartao, double valor, String titular,String cpf, boolean cartaoCredito, String numCart,String dataVal, String codSeg) throws SQLException{
		Connection conn = bd.obtemConexao();
		try{				
			
			String sqlSelect = "insert into pagamento(bandeiraCartao,valor,titular,cpf,cartaoCredito,numCartao,dataVal,codSeg) values (?,?,?,?,?,?,?,?);";
			PreparedStatement stm  = conn.prepareStatement(sqlSelect);
			
			stm.setString(1, cartao);
			stm.setDouble(2, valor);
			stm.setString(3, titular);
			stm.setString(4, cpf);
			stm.setBoolean(5, cartaoCredito);			
			stm.setString(6, numCart);
			stm.setString(7, dataVal);
			stm.setString(8,codSeg);
									
			stm.executeUpdate();			
			stm.close();

		}	
		catch (Exception e){
			e.printStackTrace();
			try{
			conn.rollback();
			}
			catch (SQLException e1){
			System.out.print(e1.getStackTrace());
			}
		
		}
	}
	
	
	public void cadastrarPagamentoDebito(String banco,String conta, double valor, String titular,String cpf, boolean cartaoDebito, String telefone,String agencia) throws SQLException{
		Connection conn = bd.obtemConexao();
		try{				
			
			String sqlSelect = "insert into pagamento(banco,valor,titular,cpf,cartaoDebito,cCorrente,telefone,agencia) values (?,?,?,?,?,?,?,?);";
			PreparedStatement stm  = conn.prepareStatement(sqlSelect);
			
			stm.setString(1, banco);
			stm.setDouble(2, valor);
			stm.setString(3, titular);
			stm.setString(4, cpf);
			stm.setBoolean(5, cartaoDebito);			
			stm.setString(6, conta);
			stm.setString(7, telefone);
			stm.setString(8,agencia);
		


									
			stm.executeUpdate();			
			stm.close();

		}	
		catch (Exception e){
			e.printStackTrace();
			try{
			conn.rollback();
			}
			catch (SQLException e1){
			System.out.print(e1.getStackTrace());
			JOptionPane.showMessageDialog(null,"Não foi possivel realizar o pagamento.");
			}
		
		}
	}
	
	
	
	
	public int consultaIdPagamento() throws SQLException {	
		Connection conn = bd.obtemConexao();	
		String sqlSelect = "select idPagamento from pagamento order by idPagamento desc limit 1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		int codpag = 0;
		try{
			stm = conn.prepareStatement(sqlSelect);
			 
			 rs = stm.executeQuery();
			
			 if(rs.next()){

				 codpag = rs.getInt(1);
	
			 }
			 
			 else{
				 JOptionPane.showMessageDialog(null,"Pagamento não efetuado");
				 
			 }
		}catch (Exception e){
			e.printStackTrace();
		}
		return codpag;
		
	
	}
	
	
	public  Pagamento  consultarPagamento(int cod) throws SQLException {
		  Connection conn = bd.obtemConexao();
	        Pagamento pag = new  Pagamento ();
	         
	        String sqlSelect = "select * from pagamento where idPagamento = ?";
	        PreparedStatement stm = null;
	        ResultSet rs = null;
	        try{
	            stm = conn.prepareStatement(sqlSelect);
	            stm.setInt(1, cod);
	            rs = stm.executeQuery();
	         
	            if(!rs.next()){	 
	                JOptionPane.showMessageDialog(null,"Pagamento não encontrado no banco de dados","ERROR", JOptionPane.ERROR_MESSAGE);                 
	            }
	 
	            else{
	            	pag.setValor(rs.getDouble(1));
	                pag.setTitular(rs.getString(2));
	                pag.setCpf(rs.getString(3));
	                pag.setCartaoDebito(rs.getBoolean(4));
	                pag.setBanco(rs.getString(5));
	                pag.setAgencia(rs.getString(6));
	                pag.setcCorrente(rs.getString(7));
	                pag.setTelefone(rs.getString(8));
	                pag.setCartaoCredito(rs.getBoolean(9));
	                pag.setBandeiraCartao(rs.getString(10));
	                pag.setNumCartao(rs.getString(11));
	                pag.setDataVal(rs.getString(12));
	                pag.setCodSeg(rs.getString(13));
	            }
		}catch (Exception e){			
			System.out.println("erro");
			try{
				conn.rollback();
			}
			catch (SQLException e1){
				System.out.print(e1.getStackTrace());
			}
		
		}
		finally	{
			if (stm != null){
				try{
					stm.close();
				}
				catch (SQLException e1){
					System.out.print(e1.getStackTrace());
				}
			}
		}		
		return pag;  
	}
	

}
