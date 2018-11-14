//Referencia
//Ex48_AJAXJson
//EstoqueService.java

package Controllers;

import java.time.format.DateTimeFormatter;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import Models.Computador;
import Models.Software;

public final class ICService {
	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	private ComputadorDAO computadorDAO;
	private SoftwareDAO softwareDAO;
	Computador computador = null;
	Software software = null;

	public ICService() {
		computadorDAO = new ComputadorDAO();
	}

	public String addIC(Request request) {

		Query query = request.getQuery();
		System.out.println("Query" + query);

		int tipo = query.getInteger("tipo");

		String id = query.get("id");
		String notaFiscal = query.get("nf");
		String dataFornecimento = query.get("dataFornecimento");
		String item = query.get("item");
		String marca = query.get("marca");
		String modelo = query.get("modelo");
		String status = query.get("status");
		String usuarioDesignado = query.get("usuarioDesignado");
		String departamento = query.get("departamento");

		switch (tipo) {
		// add OK - falta criar os campos dos outros atributos no HTML
		case 1:
			computador = new Computador(id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado,
					departamento);

			computadorDAO.add(computador);
			break;
		case 2:
			software = new Software(id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado,
					departamento);
			softwareDAO.add(software);
			break;
		}

		return computador.toString();

	}

	public String consultarIC(Request request) throws Exception {

		Query query = request.getQuery();

		String id = query.get("id");
		computador = computadorDAO.get(id);

		return computador.toString();

	}

	public String removerComputador(Request request) {
		int num = computadorDAO.getAll().size();
		Query query = request.getQuery();
		String descricao = query.get("descricao");
		listaNF.remover(descricao);

		if (num < listaNF.getNumNFs())
			return "Removido";
		else
			return null;

	}

}