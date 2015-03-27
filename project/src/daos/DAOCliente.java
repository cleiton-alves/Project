package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import objetos.Cliente;

public class DAOCliente {

	AcessoBD bd = new AcessoBD();	
	
	public boolean consultar1cliente(String cpf) throws SQLException {	
		Connection conn = bd.obtemConexao();	
		boolean var = false;
		String sqlSelect = "select * from cliente where cpf = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try{
			stm = conn.prepareStatement(sqlSelect);
			stm.setString(1, cpf);
			rs = stm.executeQuery();
		
			if(!rs.next())
				var = true;				   
			;	
		}catch (Exception e){
			e.printStackTrace();
		}
		return var;
	}
	
	public void excluirCliente(String cpf) throws SQLException {
		
		Connection conn = bd.obtemConexao();
			try{
				
				String sqlSelect = "delete  from cliente where cpf = ?";
				PreparedStatement  stm = conn.prepareStatement(sqlSelect);
		         stm.setString(1,cpf); 
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
	
	public void alterarCliente(String nome, String cpf, String estadoEmissor, String sexo,
		String email, String docIdentidade, String validadeCnh,
		String telefone, String nregistro, String dataNasc, String priCnh) throws SQLException {	
		
		Connection conn = bd.obtemConexao();
			
		if(validadeCnh.equals("")) {
			validadeCnh=null;
		}
		if (dataNasc.equals("")){
			dataNasc=null;
		}
		
		if(priCnh.equals("")){			
			priCnh=null;
		}
		
		try{
	         String sqlSelect = "update cliente set nome=?, cpf=?, estadoEmissor=?, nRegistro=?, ValidadeCnh=?, " +
						"dataPrimeiraChn=?, docIdentidade=?, telefone=?, dataNasc=?,sexo=?, email=? where cpf=?";
	     	PreparedStatement  stm = conn.prepareStatement(sqlSelect);
	     	stm.setString(1, nome);
			stm.setString(2, cpf);
			stm.setString(3, estadoEmissor);			
			stm.setString(4, nregistro);
			stm.setString(5, validadeCnh);
			stm.setString(6,priCnh);
			stm.setString(7,docIdentidade );
			stm.setString(8,telefone );
			stm.setString(9,dataNasc );
			stm.setString(10,sexo );
			stm.setString(11, email);	
			stm.setString(12, cpf);
			
			if (stm.executeUpdate() > 0) { 
				JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
			} else { 
				
			} 
			
		}catch (Exception e){
			e.printStackTrace();
			try{
			conn.rollback();
			}
			catch (SQLException e1){
			System.out.print(e1.getStackTrace());
			}
		
		}	   
	}	
	
	public Cliente consultarCliente(String cpf) throws SQLException {
		  Connection conn = bd.obtemConexao();
	        Cliente objcli = new Cliente();
	         
	        String sqlSelect = "select * from cliente where cpf = ?";
	        PreparedStatement stm = null;
	        ResultSet rs = null;
	        try{
	            stm = conn.prepareStatement(sqlSelect);
	            stm.setString(1, cpf);
	            rs = stm.executeQuery();
	         
	            if(!rs.next()){
	 
	                JOptionPane.showMessageDialog(null,"Cliente não encontrado no banco de dados","ERROR", JOptionPane.ERROR_MESSAGE);                 
	            }
	 
	            else{
	            	objcli.setId(rs.getInt(1));
	                objcli.setNome(rs.getString(2));
	                objcli.setCpf(rs.getString(3));
	                objcli.setEstadoEmissor(rs.getString(4));
	                objcli.setNregistro(rs.getString(5));
	                objcli.setValidadeCnh(rs.getString(6));
	                objcli.setPriCnh(rs.getString(7));
	                objcli.setDocIdentidade(rs.getString(8));
	                objcli.setTelefone(rs.getString(9));
	                objcli.setDataNasc(rs.getString(10));
	                objcli.setSexo(rs.getString(11));
	                objcli.setEmail(rs.getString(12));
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
		return objcli;  
	}
	
	public void cadastrarCliente(String nome, String cpf, String estadoEmissor, String sexo,
			String email, String docIdentidade, String validadeCnh,
			String telefone, String nregistro, String dataNasc, String priCnh) throws SQLException {
		if(validadeCnh.equals("")) {
			validadeCnh=null;
		}
		if (dataNasc.equals("")){
			dataNasc=null;
		}
		
		if(priCnh.equals("")){			
			priCnh=null;
		}
		Connection conn = bd.obtemConexao();
		try{				
			
			String sqlSelect = "insert into cliente (nome, cpf, estadoEmissor, nRegistro, ValidadeCnh, " +
					"dataPrimeiraChn, docIdentidade, telefone, dataNasc,sexo, email) values (?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stm  = conn.prepareStatement(sqlSelect);
			
			stm.setString(1, nome);
			stm.setString(2, cpf);
			stm.setString(3, estadoEmissor);			
			stm.setString(4, nregistro);
			stm.setString(5, validadeCnh);
			stm.setString(6,priCnh);
			stm.setString(7,docIdentidade );
			stm.setString(8,telefone );
			stm.setString(9,dataNasc );
			stm.setString(10,sexo );
			stm.setString(11, email);		
			
			stm.executeUpdate();			
			stm.close();
			
			JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
		}	
		catch (Exception e){
			JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
			e.printStackTrace();
			try{
				conn.rollback();
			}
			catch (SQLException e1){
				System.out.print(e1.getStackTrace());
			}
		
		}
		
		
		
		
		
	}

	
	

	
	
}
