import java.time.LocalDate;

public class Inventario extends Ativo {

	public Inventario(String nome, String descricao, LocalDate dataAquisicao, int quantidade, double preco) {
		super(nome, descricao, dataAquisicao, quantidade, preco);
	}
}