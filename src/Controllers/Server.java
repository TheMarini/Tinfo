package Controllers;

import java.io.File;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import Models.NotaFiscal;

public class Server implements Container {

	static Service listaNF;
	static Service listaIC;

	public void handle(Request request, Response response) {
		try {

			String path = request.getPath().getPath();
			String method = request.getMethod();
			String mensagem;

			if (path.startsWith("/adicionarNota") && "POST".equals(method)) {
				mensagem = listaNF.adicionarNF(request);
				this.enviaResposta(Status.CREATED, response, mensagem);

			} else if (path.startsWith("/adicionarIC") && "POST".equals(method)) {
				mensagem = listaIC.adicionarIC(request);
				this.enviaResposta(Status.CREATED, response, mensagem);

			} else if (path.startsWith("/consultarNotas") && "GET".equals(method)) {
				mensagem = listaNF.consultarNF(request);
				this.enviaResposta(Status.OK, response, mensagem);

			} else if (path.startsWith("/removerNota") && "GET".equals(method)) {
				mensagem = listaNF.remover(request);
				this.enviaResposta(Status.OK, response, mensagem);
			} else {
				this.naoEncontrado(response, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "Não encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Tinfo - Controle de Ativos de TI (1.0)");
//		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		// json.put("id", id);
		// json.put("text", text);
		return json;
	}

	public static void main(String args[]) throws Exception {

		listaNF = new Service();
		listaIC = new Service();

		int porta = 881;

		Container container = new Server();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		File f = new File("notasfiscais.txt");

		NotaFiscal notaFiscal1 = new NotaFiscal("1550", 10.01, 5, LocalDate.of(2018, 9, 02), "computador");

		List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();

		NotaFiscalDAO nfDAO = new NotaFiscalDAO();
		nfDAO.add(notaFiscal1);

		// MÉTODOS PARA IMPLEMENTAR:
		// Atualiza nota
		// Remove nota
		// Salva nota pelo numero
		// Salva todas as notas
		notasFiscais = nfDAO.getAll();

		for (NotaFiscal nf : notasFiscais) {
			System.out.println(nf);
		}

		System.out.println(
				"Tecle ENTER para interromper o servidor...\nAbra o html da página no navegador para verificar o funcionamento do servidor ");
		System.in.read();

		conexao.close();
		servidor.stop();

	}

}
