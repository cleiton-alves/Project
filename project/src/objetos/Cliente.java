package objetos;

import java.sql.SQLException;

import daos.DAOCliente;

public class Cliente {
	
	private String nome, cpf, estadoEmissor, sexo, email, docIdentidade;
    private String validadeCnh, telefone, nregistro, dataNasc, priCnh;
    private int id;
    
	public Cliente() {
		nome = "";
		cpf = "";
		estadoEmissor = "";
		sexo = "";
		email = "";
		docIdentidade = "";
		validadeCnh = "";
		telefone = "";
		nregistro = "";
		dataNasc = "";
		priCnh = "";
	}

	public Cliente(String nome, String cpf, String estadoEmissor, String sexo,
			String email, String docIdentidade, String validadeCnh,
			String telefone, String nregistro, String dataNasc, String priCnh) {		
		this.nome = nome;
		this.cpf = cpf;
		this.estadoEmissor = estadoEmissor;
		this.sexo = sexo;
		this.email = email;
		this.docIdentidade = docIdentidade;
		this.validadeCnh = validadeCnh;
		this.telefone = telefone;
		this.nregistro = nregistro;
		this.dataNasc = dataNasc;
		this.priCnh = priCnh;
	}

	
	
	public Cliente consultarCliente(String cpf) throws SQLException{		
		DAOCliente obj = new DAOCliente();	
		return obj.consultarCliente(cpf);
	}	
	
	public void alterarCliente(String nome, String cpf, String estadoEmissor, String sexo,
			String email, String docIdentidade, String validadeCnh,
			String telefone, String nregistro, String dataNasc, String priCnh) throws SQLException {
		DAOCliente objdao = new DAOCliente();
		
		objdao.alterarCliente(nome, cpf, estadoEmissor,sexo,email, docIdentidade, validadeCnh, telefone, nregistro, dataNasc, priCnh);
	}
	
	public void cadastrarCliente(String nome, String cpf, String estadoEmissor, String sexo,
			String email, String docIdentidade, String validadeCnh,
			String telefone, String nregistro, String dataNasc, String priCnh) throws SQLException {
		DAOCliente objcli = new DAOCliente();		
		objcli.cadastrarCliente(nome, cpf, estadoEmissor,sexo,email, docIdentidade, validadeCnh, telefone, nregistro, dataNasc, priCnh);	
	}
	
	
	public void excluirCliente(String cpf) throws SQLException {
		DAOCliente daocli = new DAOCliente();
		daocli.excluirCliente(cpf);
	}
	
	public String getValidadeCnh() {
		return validadeCnh;
	}

	public void setValidadeCnh(String validadeCnh) {
		this.validadeCnh = validadeCnh;
	}

	public String getNregistro() {
		return nregistro;
	}

	public void setNregistro(String nregistro) {
		this.nregistro = nregistro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEstadoEmissor() {
		return estadoEmissor;
	}

	public void setEstadoEmissor(String estadoEmissor) {
		this.estadoEmissor = estadoEmissor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocIdentidade() {
		return docIdentidade;
	}

	public void setDocIdentidade(String docIdentidade) {
		this.docIdentidade = docIdentidade;
	}
		
	public String getPriCnh() {
		return priCnh;
	}

	public void setPriCnh(String priCnh) {
		this.priCnh = priCnh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
			
	

}
