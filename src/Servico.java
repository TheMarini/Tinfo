import java.time.LocalDate;

public class Servico extends IC {

	public Servico(String nome, String descricao, LocalDate dataAquisicao, int quantidade, double preco, int iD,
			LocalDate dataFornecimento, LocalDate dataGarantia, Tipo[] tipo, Usuario[] usuarioDesignado,
			Departamento[] departamento, boolean status, DadosHistoricos dadosHistoricos) {
		super(nome, descricao, dataAquisicao, quantidade, preco, iD, dataFornecimento, dataGarantia, tipo,
				usuarioDesignado, departamento, status, dadosHistoricos);
	}

}