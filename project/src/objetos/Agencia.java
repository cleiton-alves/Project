package objetos;

import java.sql.SQLException;
import java.util.ArrayList;

import daos.DAOAgencia;

public class Agencia {
	
	private int idAgencia;
	private String nomeAgencia, cidadeAgencia;
	
	public Agencia(){
	
	}
	
	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getCidadeAgencia() {
		return cidadeAgencia;
	}

	public void setCidadeAgencia(String cidadeAgencia) {
		this.cidadeAgencia = cidadeAgencia;
	}
	
	public Agencia consultarAgencia(int codAgencia) throws SQLException{
		DAOAgencia daoA = new DAOAgencia();		
		return daoA.consultarAgencia(codAgencia);
	}
	
	public ArrayList<Agencia> consultarTodasAgencias() throws SQLException{
		DAOAgencia daoA = new DAOAgencia();		
		return daoA.consultarTodasAgencias();
	}
	
	public Agencia consultarAgPorNome(String agencia) throws SQLException{
		DAOAgencia daoA = new DAOAgencia();		
		return daoA.consultarAgPorNome(agencia);
	}

}
