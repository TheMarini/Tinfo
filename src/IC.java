import java.time.LocalDate;

public class IC {
	int ID;
	String nome, description;
	String[] tipo = {"servico", "hardware", "software", "documentacao", "pessoal"};
	boolean status;
	LocalDate dataFornecimento;
	DadosHistoricos dadosHistoricos;
}
