package Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import Models.Computador;
import Models.Hardware;
import Models.IC;
import Models.Inventario;
import Models.NotaFiscal;
import Models.NotasFiscais;
import Models.Servico;
import Models.Software;

public final class Service {
	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	private Inventario listaIC;

	public Service() {
		listaIC = new Inventario();
	}

	public String adicionarIC(Request request) {

		IC ic = null;

		Query query = request.getQuery();
		System.out.println("Query" + query);

		String nf = query.get("nf");
		String status = query.get("status");
		LocalDate dataFornecimento = LocalDate.parse(query.get("dataFornecimento"), formatter);
		String usuarioDesignado = query.get("usuarioDesignado");
		int tipo = query.getInteger("tipo");

		switch (tipo) {
		case 0:

			ic = new Computador(nf, dataFornecimento, status, usuarioDesignado);

			break;
		case 1:
			ic = new Hardware(nf, dataFornecimento, status, usuarioDesignado);
			break;
		case 2:
			ic = new Servico(nf, dataFornecimento, status, usuarioDesignado);
			break;
		case 3:
			ic = new Software(nf, dataFornecimento, status, usuarioDesignado);
			break;
		}

		if (ic != null) {
			listaIC.adicionar(ic);
		}

		return ic.toString();

	}

}
