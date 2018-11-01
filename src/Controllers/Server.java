package Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

		NotaFiscal nf1 = new NotaFiscal("1", 10.01, 5, LocalDate.of(2018, 9, 02), "computador");
		NotaFiscal nf2 = new NotaFiscal("2", 10.02, 5, LocalDate.of(2018, 9, 02), "computador");
		NotaFiscal nf3 = new NotaFiscal("3", 10.03, 5, LocalDate.of(2018, 9, 02), "computador");
		NotaFiscal nf4 = new NotaFiscal("4", 10.04, 5, LocalDate.of(2018, 9, 02), "computador");

		List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
		NotaFiscalDAO nfDAO = new NotaFiscalDAO();

		nfDAO.add(nf1);
		nfDAO.add(nf2);
		nfDAO.add(nf3);
		nfDAO.add(nf4);

		int opcao = 9;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Cadastro de Notas Fiscais");
		System.out.println("Selecione uma opcao:");
		System.out.println("1-Buscar uma Nota Fiscal");
		System.out.println("2-Cadastrar uma Nota Fiscal");
		System.out.println("3-Atualizar uma Nota Fiscal");
		System.out.println("4-Remover uma Nota Fiscal");
		System.out.println("5-Listar Notas Fiscais");
		System.out.println("0-SAIR");

		opcao = Integer.parseInt(br.readLine());
		while (opcao != 0) {
			// MÉTODOS PARA IMPLEMENTAR:
			switch (opcao) {

			case 1:
				// Buscar nota
				System.out.println("Informe o numero da Nota Fiscal: ");
				String numNF = br.readLine();
				System.out.println(nfDAO.get(numNF));

				break;
			case 2:
				// Salva nota fiscal
				System.out.println("Informe o numero da Nota Fiscal: ");
				numNF = br.readLine();
				System.out.println("Informe o valor unitario dos itens: ");
				double valorUnit = Double.parseDouble(br.readLine());
				System.out.println("Informe a quantidade de itens: ");
				int quantidade = Integer.parseInt(br.readLine());
				System.out.println("Informe a data de Emissao da NF (AAAA-MM-DD)");
				LocalDate dataEmissaoNF = LocalDate.parse(br.readLine());
				System.out.println("Informe a descricao: ");
				String descricao = br.readLine();
				NotaFiscal novaNF = new NotaFiscal(numNF, valorUnit, quantidade, dataEmissaoNF, descricao);

				nfDAO.add(novaNF);

				break;
			case 3:
				// Atualiza nota
				System.out.println("Informe o numero da Nota Fiscal: ");
				numNF = br.readLine();
				System.out.println("Informe o valor unitario dos itens: ");
				valorUnit = Double.parseDouble(br.readLine());
				System.out.println("Informe a quantidade de itens: ");
				quantidade = Integer.parseInt(br.readLine());
				System.out.println("Informe a data de Emissao da NF (AAAA-MM-DD)");
				dataEmissaoNF = LocalDate.parse(br.readLine());
				System.out.println("Informe a descricao: ");
				descricao = br.readLine();
				novaNF = new NotaFiscal(numNF, valorUnit, quantidade, dataEmissaoNF, descricao);

				nfDAO.update(novaNF);

				break;
			case 4:
				// Remove nota
				System.out.println("Informe o numero da Nota Fiscal: ");
				numNF = br.readLine();
				System.out.println("Informe o valor unitario dos itens: ");
				valorUnit = Double.parseDouble(br.readLine());
				System.out.println("Informe a quantidade de itens: ");
				quantidade = Integer.parseInt(br.readLine());
				System.out.println("Informe a data de Emissao da NF (AAAA-MM-DD)");
				dataEmissaoNF = LocalDate.parse(br.readLine());
				System.out.println("Informe a descricao: ");
				descricao = br.readLine();
				novaNF = new NotaFiscal(numNF, valorUnit, quantidade, dataEmissaoNF, descricao);

				nfDAO.delete(novaNF);

				break;
			case 5:
				// Buscar lista de notas
				// Receber Notas Fiscais do txt e exibir como String:
				notasFiscais = nfDAO.getAll();
				for (NotaFiscal nf : notasFiscais) {
					System.out.println(nf);
				}
				break;
			default:
				System.out.println("Opcao invalida");
				break;
			}

			System.out.println("Cadastro de Notas Fiscais");
			System.out.println("Selecione uma opcao:");
			System.out.println("1-Buscar uma Nota Fiscal");
			System.out.println("2-Cadastrar uma Nota Fiscal");
			System.out.println("3-Atualizar uma Nota Fiscal");
			System.out.println("4-Remover uma Nota Fiscal");
			System.out.println("5-Listar Notas Fiscais");
			System.out.println("0-SAIR");

			opcao = Integer.parseInt(br.readLine());

		}
		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();

	}

}
