package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import Models.Computador;

public class ComputadorDAO implements DAO<Computador, String> {
	public ComputadorDAO() {

	}

	@Override
	public void add(Computador computador) {
		Computador b = computador;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("computadores.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(b.getId() + separadorDeLinha);
			buffer_saida.write(b.getNf() + separadorDeLinha);
			buffer_saida.write(b.getDataFornecimento() + separadorDeLinha);
			buffer_saida.write(b.getStatus() + separadorDeLinha);
			buffer_saida.write(b.getUsuarioDesignado() + separadorDeLinha);

			buffer_saida.flush();

		} catch (Exception e) {

			System.out.println("ERRO ao gravar o Computador " + b.getId() + " no disco!");

			e.printStackTrace();
		}
	}

	@Override
	public Computador get(String chave) {
		Computador retorno = null;
		Computador computador = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("computadores.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				computador = new Computador();
				computador.setId(idSTR);
				computador.setNf(buffer_entrada.readLine());
				computador.setDataFornecimento(buffer_entrada.readLine());
				computador.setStatus(buffer_entrada.readLine());
				computador.setUsuarioDesignado(buffer_entrada.readLine());

				if (chave.equals(computador.getId())) {
					retorno = computador;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Computador '" + computador.getId() + "' do disco rigido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public List<Computador> getAll() {
		List<Computador> computadores = new ArrayList<Computador>();
		Computador computador = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("computadores.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				computador = new Computador();
				computador.setId(idSTR);
				computador.setNf(buffer_entrada.readLine());
				computador.setDataFornecimento(buffer_entrada.readLine());
				computador.setStatus(buffer_entrada.readLine());
				computador.setUsuarioDesignado(buffer_entrada.readLine());
				computadores.add(computador);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler os Computadores do disco rígido!");
			e.printStackTrace();
		}

		return computadores;
	}

	@Override
	public void update(Computador p) {
		List<Computador> computadores = getAll();
		int index = computadores.indexOf(p);
		if (index != -1) {
			computadores.set(index, p);
		}
		saveToFile(computadores);
	}

	@Override
	public void delete(Computador computador) {
		List<Computador> computadores = getAll();
		int index = computadores.indexOf(computador);
		if (index != -1) {
			computadores.remove(index);
			saveToFile(computadores);
		}
	}

	private void saveToFile(List<Computador> computadores) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("computadores.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (Computador b : computadores) {
				buffer_saida.write(b.getId() + separadorDeLinha);
				buffer_saida.write(b.getNf() + separadorDeLinha);
				buffer_saida.write(b.getDataFornecimento() + separadorDeLinha);
				buffer_saida.write(b.getStatus() + separadorDeLinha);
				buffer_saida.write(b.getUsuarioDesignado() + separadorDeLinha);
				buffer_saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar os Computadores no disco!");
			e.printStackTrace();
		}
	}

}
