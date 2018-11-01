package Models;

import org.json.JSONObject;

public abstract class IC {
//	static NotaFiscal nf;

	// informações da compra
	private String id;
	private String nf;
	private String dataFornecimento;
	//

	// detalhes do item
	// nome do item, marca, modelo,

	//
	private String status;
	// private Usuario[] usuarioDesignado;
	private String usuarioDesignado;
	// private Departamento[] departamento;
	// private DadosHistoricos dadosHistoricos;

	public IC(String id, String nf, String dataFornecimento2, String status, String usuarioDesignado) {
		this.id = id;
		this.nf = nf;
		this.dataFornecimento = dataFornecimento2;
		this.status = status;
		this.usuarioDesignado = usuarioDesignado;
	}

	public IC() {
	}

	@Override
	public String toString() {
		return "ID: " + this.id + " - Nota Fiscal: " + this.nf + " - Data de fornecimento: " + this.dataFornecimento
				+ " - Status do item: " + this.status + " - Usuario designado: " + this.usuarioDesignado;
	}

	/**
	 * Converte a Nota Fiscal para o formato Json
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nf", this.getNf());
		obj.put("dataFornecimento", this.getDataFornecimento());
		obj.put("status", this.getStatus());
		obj.put("usuarioDesignado", this.getUsuarioDesignado());
		return obj;
	}

	public String getId() {
		return id;
	}

	public String getNf() {
		return nf;
	}

	public String getDataFornecimento() {
		return dataFornecimento;
	}

	public String getStatus() {
		return status;
	}

	public String getUsuarioDesignado() {
		return usuarioDesignado;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public void setDataFornecimento(String dataFornecimento) {
		this.dataFornecimento = dataFornecimento;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsuarioDesignado(String usuarioDesignado) {
		this.usuarioDesignado = usuarioDesignado;
	}

}