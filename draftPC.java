ComputadorDAO computadorDAO = new ComputadorDAO();
				computadorDAO.add(new Computador("1", 10.01, 5, LocalDate.of(2018, 9, 02), "computador"));
				computadorDAO.add(new Computador("2", 10.02, 5, LocalDate.of(2018, 9, 02), "computador"));
				computadorDAO.add(new Computador("3", 10.03, 5, LocalDate.of(2018, 9, 02), "computador"));
				computadorDAO.add(new Computador("4", 10.04, 5, LocalDate.of(2018, 9, 02), "computador"));

				List<Computador> computadores;

				opcao = 9;
				br = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Cadastro de Computadores");
				System.out.println("Selecione uma opcao:");
				System.out.println("1-Buscar um Computador");
				System.out.println("2-Cadastrar um Computador");
				System.out.println("3-Atualizar um Computador");
				System.out.println("4-Remover um Computador");
				System.out.println("5-Listar Computadores");
				System.out.println("0-SAIR");

				opcao = Integer.parseInt(br.readLine());
				while (opcao != 0) {
					switch (opcao) {

					case 1:
						System.out.println("Informe a ID do computador: ");
						String idComputador = br.readLine();
						System.out.println(computadorDAO.get(idComputador));
						break;
					case 2:
						System.out.println("Informe a ID do computador: ");
						int idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						String numNF = Integer.parseInt(br.readLine());
						System.out.println("Informe a data de fornecimento do computador (AAAA-MM-DD): ");
						LocalDate dataFornecimento = LocalDate.parse(br.readLine());
						System.out.println("Informe o status do computador: ");
						String status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						String usuarioDesignado = br.readLine();
						Computador novoComputador = new Computador(id, numNF, dataFornecimento, status, usuarioDesignado);

						computadorDAO.add(novoComputador);

						break;
					case 3:
						System.out.println("Informe a ID do computador: ");
						int idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						String numNF = Integer.parseInt(br.readLine());
						System.out.println("Informe a data de fornecimento do computador (AAAA-MM-DD): ");
						LocalDate dataFornecimento = LocalDate.parse(br.readLine());
						System.out.println("Informe o status do computador: ");
						String status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						String usuarioDesignado = br.readLine();
                        Computador novoComputador = new Computador(id, numNF, dataFornecimento, status, usuarioDesignado);

						computadorDAO.update(novoComputador);

						break;
					case 4:
						System.out.println("Informe a ID do computador: ");
						int idComputador = br.readLine();
						System.out.println("Informe o numero da nota fiscal: ");
						String numNF = Integer.parseInt(br.readLine());
						System.out.println("Informe a data de fornecimento do computador (AAAA-MM-DD): ");
						LocalDate dataFornecimento = LocalDate.parse(br.readLine());
						System.out.println("Informe o status do computador: ");
						String status = br.readLine();
						System.out.println("Informe o usuario designado: ");
						String usuarioDesignado = br.readLine();
                        Computador novoComputador = new Computador(id, numNF, dataFornecimento, status, usuarioDesignado);

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

					opcao = Integer.parseInt(br.readLine());
