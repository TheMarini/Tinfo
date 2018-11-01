package Models;

import java.time.LocalDate;

public class Computador extends Hardware {
	private String id;

	public Computador(String id, String nf, LocalDate dataFornecimento, String status, String usuarioDesignado) {
		super(nf, dataFornecimento, status, usuarioDesignado);
		this.id = id;
	}

	public Computador() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}