import java.time.LocalDate;

public class IC {
	private int ID;
	private String nome, descricao;
	private double preco;
	private LocalDate dataAquisicao, dataFornecimento, dataGarantia;
	private Usuario[] usuarioDesignado;
	private Departamento[] departamento;
	private boolean status;
	private DadosHistoricos dadosHistoricos;

	public IC(String nome, String descricao, LocalDate dataAquisicao, int quantidade, double preco, int iD,
			LocalDate dataFornecimento, LocalDate dataGarantia, Usuario[] usuarioDesignado, Departamento[] departamento,
			boolean status, DadosHistoricos dadosHistoricos) {

	}
}