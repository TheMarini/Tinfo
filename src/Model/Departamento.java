package Models;

public class Departamento {
	private String nome;
	private Usuario[] usuarios;
	// private String[] equipamentos, softwares;

	public Departamento() {

	}

	public String getNome() {
		return nome;
	}

	public Usuario[] getUsuarios() {
		return usuarios;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsuarios(Usuario[] usuarios) {
		this.usuarios = usuarios;
	}
}
