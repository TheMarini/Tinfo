package Models;

public class Computador extends Hardware {

	public Computador() {
		super();
	}

	public Computador(String id, String notaFiscal, String dataFornecimento, String item, String marca, String modelo,
			String status, String usuarioDesignado, String departamento) {
		super(id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado, departamento);
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + " - Nota Fiscal: " + this.getNotaFiscal() + " - Data de fornecimento: "
				+ this.getDataFornecimento() + " - Status do item: " + this.getStatus() + " - Usuario designado: "
				+ this.getUsuarioDesignado();
	}

}