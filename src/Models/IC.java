package Models;

import org.json.JSONObject;

public abstract class IC {
	private String id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado, departamento;
	// private DadosHistoricos dadosHistoricos;

	public IC(String id, String notaFiscal, String dataFornecimento, String item, String marca, String modelo,
			String status, String usuarioDesignado, String departamento) {
		this.id = id;
		this.notaFiscal = notaFiscal;
		this.dataFornecimento = dataFornecimento;
		this.item = item;
		this.marca = marca;
		this.modelo = modelo;
		this.status = status;
		this.usuarioDesignado = usuarioDesignado;
		this.departamento = departamento;
	}

	public IC() {
	}

	@Override
	public String toString() {
		return "ID: " + this.id + " - Nota Fiscal: " + this.notaFiscal + " - Data de fornecimento: "
				+ this.dataFornecimento + " - Status do item: " + this.status + " - Usuario designado: "
				+ this.usuarioDesignado;
	}

	/**
	 * Converte a Nota Fiscal para o formato Json
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nf", this.getNotaFiscal());
		obj.put("dataFornecimento", this.getDataFornecimento());
		obj.put("status", this.getStatus());
		obj.put("usuarioDesignado", this.getUsuarioDesignado());
		return obj;
	}

	public String getId() {
		return id;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public String getDataFornecimento() {
		return dataFornecimento;
	}

	public String getItem() {
		return item;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getStatus() {
		return status;
	}

	public String getUsuarioDesignado() {
		return usuarioDesignado;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public void setDataFornecimento(String dataFornecimento) {
		this.dataFornecimento = dataFornecimento;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsuarioDesignado(String usuarioDesignado) {
		this.usuarioDesignado = usuarioDesignado;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}