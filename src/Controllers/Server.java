package Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.LocalDate;
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

import Models.Computador;
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
		error.put("error", "Nao encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Tinfo - Controle de Ativos de TI (1.0)");
		response.setValue("Access-Control-Allow-Origin", "null");
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

		int porta = 3000;

		Container container = new Server();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tinfo - Controle de Itens de Configuracao");
		int opcao = 9;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Cadastro de Notas Fiscais");
		System.out.println("Selecione uma opcao:");
		System.out.println("1-Notas Fiscais");
		System.out.println("2-Computadores");
		System.out.println("0-SAIR");

		opcao = Integer.parseInt(br.readLine());
		while (opcao != 0) {
			switch (opcao) {
			case 1:

				NotaFiscalDAO nfDAO = new NotaFiscalDAO();
				nfDAO.add(new NotaFiscal("1", 10.01, 5, LocalDate.of(2018, 9, 02), "computador"));
				nfDAO.add(new NotaFiscal("2", 10.02, 5, LocalDate.of(2018, 9, 02), "computador"));
				nfDAO.add(new NotaFiscal("3", 10.03, 5, LocalDate.of(2018, 9, 02), "computador"));
				nfDAO.add(new NotaFiscal("4", 10.04, 5, LocalDate.of(2018, 9, 02), "computador"));

				List<NotaFiscal> notasFiscais;

				int opcao1 = 9;
				br = new BufferedReader(new InputStreamReader(System.in));

				System.out.println("Cadastro de Notas Fiscais");
				System.out.println("Selecione uma opcao:");
				System.out.println("1-Buscar uma Nota Fiscal");
				System.out.println("2-Cadastrar uma Nota Fiscal");
				System.out.println("3-Atualizar uma Nota Fiscal");
				System.out.println("4-Remover uma Nota Fiscal");
				System.out.println("5-Listar Notas Fiscais");
				System.out.println("0-SAIR");

				opcao1 = Integer.parseInt(br.readLine());
				while (opcao1 != 0) {
					// MÉTODOS PARA IMPLEMENTAR:
					switch (opcao1) {

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

					opcao1 = Integer.parseInt(br.readLine());

				}
				break;
			case 2:
				ComputadorDAO computadorDAO = new ComputadorDAO();
				computadorDAO.add(new Computador("1", "999854", "02/09/2018", "Em uso", "Jose"));
				computadorDAO.add(new Computador("2", "999854", "02/09/2018", "Em uso", "Carlos"));
				computadorDAO.add(new Computador("3", "999854", "02/09/2018", "Em uso", "Jaqueline"));
				computadorDAO.add(new Computador("4", "999854", "02/09/2018", "Em uso", "Tamara"));

				List<Computador> computadores;

				int opcao2 = 9;
				br = new BufferedReader(new InputStreamReader(System.in));

				System.out.println("Cadastro de Computadores");
				System.out.println("Selecione uma opcao:");
				System.out.println("1-Buscar um Computador");
				System.out.println("2-Cadastrar um Computador");
				System.out.println("3-Atualizar um Computador");
				System.out.println("4-Remover um Computador");
				System.out.println("5-Listar Computadores");
				System.out.println("0-SAIR");

				opcao2 = Integer.parseInt(br.readLine());
				while (opcao2 != 0) {
					switch (opcao2) {

					case 1:
						System.out.println("Informe a ID do computador: ");
						String idComputadorSTR = br.readLine();
						System.out.println(computadorDAO.get(idComputadorSTR));
						break;
					case 2:
						System.out.println("Informe a ID do computador: ");
						String idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						String numNF = br.readLine();
						System.out.println("Informe a data de fornecimento do computador: ");
						String dataFornecimento = br.readLine();
						System.out.println("Informe o status do computador: ");
						String status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						String usuarioDesignado = br.readLine();
						Computador novoComputador = new Computador(idComputador, numNF, dataFornecimento, status,
								usuarioDesignado);

						computadorDAO.add(novoComputador);

						break;
					case 3:
						System.out.println("Informe a ID do computador: ");
						idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						numNF = br.readLine();
						System.out.println("Informe a data de fornecimento do computador: ");
						dataFornecimento = br.readLine();
						System.out.println("Informe o status do computador: ");
						status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						usuarioDesignado = br.readLine();
						novoComputador = new Computador(idComputador, numNF, dataFornecimento, status,
								usuarioDesignado);

						computadorDAO.update(novoComputador);

						break;
					case 4:
						System.out.println("Informe a ID do computador: ");
						idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						numNF = br.readLine();
						System.out.println("Informe a data de fornecimento do computador: ");
						dataFornecimento = br.readLine();
						System.out.println("Informe o status do computador: ");
						status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						usuarioDesignado = br.readLine();
						novoComputador = new Computador(idComputador, numNF, dataFornecimento, status,
								usuarioDesignado);

						computadorDAO.delete(novoComputador);

						break;
					case 5:
						computadores = computadorDAO.getAll();
						for (Computador computador : computadores) {
							System.out.println(computador);
						}
						break;
					default:
						System.out.println("Opcao invalida");
						break;
					}

					System.out.println("Cadastro de Computadores");
					System.out.println("Selecione uma opcao:");
					System.out.println("1-Buscar um Computador");
					System.out.println("2-Cadastrar um Computador");
					System.out.println("3-Atualizar um Computador");
					System.out.println("4-Remover um Computador");
					System.out.println("5-Listar Computadores");
					System.out.println("0-SAIR");

					opcao2 = Integer.parseInt(br.readLine());
				}
				break;
			default:
				System.out.println("Opcao invalida");
				break;

			}
		}
		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();

	}

}
