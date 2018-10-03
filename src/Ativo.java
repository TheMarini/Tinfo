import java.time.LocalDate;

public class Ativo {
	private String nome, descricao;
	private LocalDate dataAquisicao;
	private int quantidade;
	private double preco;

	public Ativo(String nome, String descricao, LocalDate dataAquisicao, int quantidade, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataAquisicao = dataAquisicao;
		this.quantidade = quantidade;
		this.preco = preco;
	}
}