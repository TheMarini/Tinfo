package Models;

import java.time.LocalDate;

public class Hardware extends IC {

	public Hardware(String nf, LocalDate dataFornecimento, String status, String usuarioDesignado) {
		super(nf, dataFornecimento, status, usuarioDesignado);

	}

	public Hardware() {
		super();
	}

}
