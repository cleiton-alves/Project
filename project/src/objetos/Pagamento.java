package objetos;

import java.sql.SQLException;

import daos.DAOPagamento;

public class Pagamento {
	
	
	private double valor;
	private String titular;
	private String cpf ;
	private boolean cartaoDebito;
	private String banco;
	
	

	private String agencia;
	private String cCorrente;
	private String telefone;
	private boolean cartaoCredito;
	private String bandeiraCartao;
	private String  numCartao;
	private String dataVal;
	private String codSeg;
	
	
	public Pagamento (){
		
	}
	
	
	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public String getTitular() {
		return titular;
	}


	public void setTitular(String titular) {
		this.titular = titular;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public boolean isCartaoDebito() {
		return cartaoDebito;
	}


	public void setCartaoDebito(boolean cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}


	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public String getAgencia() {
		return agencia;
	}


	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}


	public String getcCorrente() {
		return cCorrente;
	}


	public void setcCorrente(String cCorrente) {
		this.cCorrente = cCorrente;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public boolean isCartaoCredito() {
		return cartaoCredito;
	}


	public void setCartaoCredito(boolean cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}


	public String getBandeiraCartao() {
		return bandeiraCartao;
	}


	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}


	public String getNumCartao() {
		return numCartao;
	}


	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}


	public String getDataVal() {
		return dataVal;
	}


	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}


	public String getCodSeg() {
		return codSeg;
	}


	public void setCodSeg(String codSeg) {
		this.codSeg = codSeg;
	}

	
	
	public void cadastrarPagamentoCredito(String cartao,double valor, String titular,String cpf, boolean cartaoCredito, String numCart,String dataVal, String codSeg) throws SQLException{
		DAOPagamento pag = new DAOPagamento();
		pag.cadastrarPagamentoCredito(cartao,valor,titular, cpf,cartaoCredito,  numCart,dataVal,codSeg);
		
	}
	
	public void cadastrarPagamentoDebito(String banco,String conta,double valor, String titular,String cpf, boolean cartaoDebito, String telefone,String agencia) throws SQLException{
		DAOPagamento pag = new DAOPagamento();	
		pag.cadastrarPagamentoDebito(banco,conta,valor,titular, cpf,cartaoDebito, telefone,agencia);
		
	}
	
	
	public int consultaIdPagamento() throws SQLException{
		DAOPagamento pag = new DAOPagamento();
		
		return pag.consultaIdPagamento();
	}	
		
	
	
	public Pagamento consultarPagamento(int cod) throws SQLException{
			
			DAOPagamento pag = new DAOPagamento();
			
			return pag.consultarPagamento(cod);
		}		

	


	
}