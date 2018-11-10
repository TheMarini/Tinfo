package Models;

public abstract class Hardware extends IC {

	public Hardware() {
		super();
	}

	public Hardware(String id, String notaFiscal, String dataFornecimento, String item, String marca, String modelo,
			String status, String usuarioDesignado, String departamento) {
		super(id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado, departamento);
	}

}
