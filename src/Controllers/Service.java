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

		String id = query.get("id");
		String nf = query.get("nf");
		String status = query.get("status");
		String dataFornecimento = query.get("dataFornecimento");
		String usuarioDesignado = query.get("usuarioDesignado");
		int tipo = query.getInteger("tipo");

		switch (tipo) {
		case 1:

			ic = new Computador(id, nf, dataFornecimento, status, usuarioDesignado);

			break;
		case 2:
			ic = new Software(id, nf, dataFornecimento, status, usuarioDesignado);
			break;
		}

		if (ic != null) {
			computadorDAO.adicionar(ic);
		}

		return ic.toString();

	}

	public String consultarNF(Request request) throws Exception {

		Query query = request.getQuery();

		NotaFiscal nf = null;
		int id = query.getInteger("id");
		nf = listaNF.consultar(id);

		return nf.toString();

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