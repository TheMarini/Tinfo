import java.time.LocalDate;

public class IC extends Ativo {
	private int ID;
	private LocalDate dataFornecimento, dataGarantia;
	private Tipo[] tipo;
	private Usuario[] usuarioDesignado;
	private Departamento[] departamento;
	private boolean status;
	private DadosHistoricos dadosHistoricos;

	public IC(String nome, String descricao, LocalDate dataAquisicao, int quantidade, double preco, int iD,
			LocalDate dataFornecimento, LocalDate dataGarantia, Tipo[] tipo, Usuario[] usuarioDesignado,
			Departamento[] departamento, boolean status, DadosHistoricos dadosHistoricos) {
		super(nome, descricao, dataAquisicao, quantidade, preco);
		ID = iD;
		this.dataFornecimento = dataFornecimento;
		this.dataGarantia = dataGarantia;
		this.tipo = tipo;
		this.usuarioDesignado = usuarioDesignado;
		this.departamento = departamento;
		this.status = status;
		this.dadosHistoricos = dadosHistoricos;
	}
}