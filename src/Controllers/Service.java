package Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import Models.Ativos;
import Models.Computador;
import Models.Hardware;
import Models.IC;
import Models.Software;

public final class Service {
	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	private ComputadorDAO computadorDAO;

	public Service() {
		computadorDAO = new ComputadorDAO();
	}

	public String addIC(Request request) {

		IC ic = null;

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
		case 1:

			ic = new Computador(id, notaFiscal, dataFornecimento, item, marca, modelo, status, usuarioDesignado,
					departamento);

			computadorDAO.add((Computador) ic);
			break;
		case 2:
			// ic = new Software(id, nf, dataFornecimento, status, usuarioDesignado);
			// softwareDAO.add((Software) ic);
			break;
		}

		return ic.toString();

	}

	public String consultarIC(Request request) throws Exception {

		Query query = request.getQuery();

		IC ic = null;
		String id = query.get("id");
		ic = computadorDAO.get(id);

		return ic.toString();

	}

	public String remover(Request request) {
		int num = listaNF.getNumNFs();
		Query query = request.getQuery();
		String descricao = query.get("descricao");
		listaNF.remover(descricao);

		if (num < listaNF.getNumNFs())
			return "Removido";
		else
			return null;

	}

}