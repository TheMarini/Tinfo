package Models;

import java.time.LocalDate;

import org.json.JSONObject;

public abstract class IC {
//	static NotaFiscal nf;

	private String nf;
	private LocalDate dataFornecimento;
	private String status;
	// private Usuario[] usuarioDesignado;
	private String usuarioDesignado;
	// private Departamento[] departamento;
	// private DadosHistoricos dadosHistoricos;

	public IC(String nf, LocalDate dataFornecimento, String status, String usuarioDesignado) {
		this.nf = nf;
		this.dataFornecimento = dataFornecimento;
		this.status = status;
		this.usuarioDesignado = usuarioDesignado;
	}

	@Override
	public String toString() {
		return "Nota Fiscal: " + this.nf + " - Data de fornecimento: " + this.dataFornecimento + " - Status do item: "
				+ this.status + " - Usuario designado: " + this.usuarioDesignado;
	}

	/**
	 * Converte a Nota Fiscal para o formato Json
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("nf", this.getNf());
		obj.put("dataFornecimento", this.getDataFornecimento());
		obj.put("status", this.getStatus());
		obj.put("usuarioDesignado", this.getUsuarioDesignado());
		return obj;
	}

	public String getNf() {
		return nf;
	}

	public LocalDate getDataFornecimento() {
		return dataFornecimento;
	}

	public String getStatus() {
		return status;
	}

	public String getUsuarioDesignado() {
		return usuarioDesignado;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public void setDataFornecimento(LocalDate dataFornecimento) {
		this.dataFornecimento = dataFornecimento;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsuarioDesignado(String usuarioDesignado) {
		this.usuarioDesignado = usuarioDesignado;
	}

}