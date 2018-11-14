package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import Models.Software;

public class SoftwareDAO implements DAO<Software, String> {
	public SoftwareDAO() {

	}

	@Override
	public void add(Software software) {
		Software b = software;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("softwares.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(b.getId() + separadorDeLinha);
			buffer_saida.write(b.getNotaFiscal() + separadorDeLinha);
			buffer_saida.write(b.getDataFornecimento() + separadorDeLinha);
			buffer_saida.write(b.getItem() + separadorDeLinha);
			buffer_saida.write(b.getMarca() + separadorDeLinha);
			buffer_saida.write(b.getModelo() + separadorDeLinha);
			buffer_saida.write(b.getStatus() + separadorDeLinha);
			buffer_saida.write(b.getUsuarioDesignado() + separadorDeLinha);
			buffer_saida.write(b.getDepartamento() + separadorDeLinha);

			buffer_saida.flush();

		} catch (Exception e) {

			System.out.println("ERRO ao gravar o Software " + b.getId() + " no disco!");

			e.printStackTrace();
		}
	}

	@Override
	public Software get(String chave) {
		Software retorno = null;
		Software software = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("computadores.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				software = new Software();
				software.setId(idSTR);
				software.setNotaFiscal(buffer_entrada.readLine());
				software.setNotaFiscal(buffer_entrada.readLine());
				software.setDataFornecimento(buffer_entrada.readLine());
				software.setItem(buffer_entrada.readLine());
				software.setMarca(buffer_entrada.readLine());
				software.setModelo(buffer_entrada.readLine());
				software.setStatus(buffer_entrada.readLine());
				software.setUsuarioDesignado(buffer_entrada.readLine());
				software.setDepartamento(buffer_entrada.readLine());

				if (chave.equals(software.getId())) {
					retorno = software;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Software '" + software.getId() + "' do disco rigido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public List<Software> getAll() {
		List<Software> computadores = new ArrayList<Software>();
		Software software = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("computadores.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				software = new Software();
				software.setId(idSTR);
				software.setNotaFiscal(buffer_entrada.readLine());
				software.setNotaFiscal(buffer_entrada.readLine());
				software.setDataFornecimento(buffer_entrada.readLine());
				software.setItem(buffer_entrada.readLine());
				software.setMarca(buffer_entrada.readLine());
				software.setModelo(buffer_entrada.readLine());
				software.setStatus(buffer_entrada.readLine());
				software.setUsuarioDesignado(buffer_entrada.readLine());
				software.setDepartamento(buffer_entrada.readLine());
				computadores.add(software);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler os Softwares do disco r�gido!");
			e.printStackTrace();
		}

		return computadores;
	}

	@Override
	public void update(Software p) {
		List<Software> computadores = getAll();
		int index = computadores.indexOf(p);
		if (index != -1) {
			computadores.set(index, p);
		}
		saveToFile(computadores);
	}

	@Override
	public void delete(Software software) {
		List<Software> computadores = getAll();
		int index = computadores.indexOf(software);
		if (index != -1) {
			computadores.remove(index);
			saveToFile(computadores);
		}
	}

	private void saveToFile(List<Software> softwares) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("softwares.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (Software b : softwares) {
				buffer_saida.write(b.getId() + separadorDeLinha);
				buffer_saida.write(b.getNotaFiscal() + separadorDeLinha);
				buffer_saida.write(b.getDataFornecimento() + separadorDeLinha);
				buffer_saida.write(b.getItem() + separadorDeLinha);
				buffer_saida.write(b.getMarca() + separadorDeLinha);
				buffer_saida.write(b.getModelo() + separadorDeLinha);
				buffer_saida.write(b.getStatus() + separadorDeLinha);
				buffer_saida.write(b.getUsuarioDesignado() + separadorDeLinha);
				buffer_saida.write(b.getDepartamento() + separadorDeLinha);
				buffer_saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar os Softwares no disco!");
			e.printStackTrace();
		}
	}

}
