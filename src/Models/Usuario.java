package Models;

public class Usuario {
	private String nome;
	private int[] nivelAcesso = { 1, 2, 3 };

	public Usuario() {

	}

	public String getNome() {
		return nome;
	}

	public int[] getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNivelAcesso(int[] nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
}
