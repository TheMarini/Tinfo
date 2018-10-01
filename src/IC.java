import java.time.LocalDate;

public class IC {
	private int ID;
	private String nome, description;
	private String[] tipo = { "servico", "hardware", "software", "documentacao", "equipe" };
	private boolean status;
	private LocalDate dataFornecimento;
	private DadosHistoricos dadosHistoricos;
	private Ativo ativo;

	public IC() {

	}
}