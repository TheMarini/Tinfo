package Models;

import java.time.LocalDate;

import org.json.JSONObject;

import Controllers.JsonFormatter;

public class NotaFiscal implements JsonFormatter {
	private String nf;
	private double valorUnit;
	private int quantidade;
	private LocalDate dataEmissaoNF;
	private String descricao;

	public NotaFiscal(String nf, double valorUnit, int quantidade, LocalDate dataEmissaoNF, String descricao) {
		this.nf = nf;
		this.valorUnit = valorUnit;
		this.quantidade = quantidade;
		this.dataEmissaoNF = dataEmissaoNF;
		this.descricao = descricao;
	}

	public NotaFiscal() {

	}

	@Override
	public String toString() {
		return "Nota Fiscal: " + this.nf + " - Valor unitario: " + this.valorUnit + " - Quantidade: " + this.quantidade
				+ " - Data de emissao: " + this.dataEmissaoNF + " - Descricao: " + this.descricao;
	}

	/**
	 * Converte a Nota Fiscal para o formato Json
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getNfID());
		obj.put("descricao", this.getDescricao());
		obj.put("valorUnit", this.getValorUnit());
		obj.put("quantidade", this.getQuantidade());
		return obj;
	}

	public String getNfID() {
		return nf;
	}

	public double getValorUnit() {
		return valorUnit;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getDataEmissaoNF() {
		return dataEmissaoNF;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setNfID(String nfID) {
		this.nf = nfID;
	}

	public void setValorUnit(double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setDataEmissaoNF(LocalDate dataEmissaoNF) {
		this.dataEmissaoNF = dataEmissaoNF;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
