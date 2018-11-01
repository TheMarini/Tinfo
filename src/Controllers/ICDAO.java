/*package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.IC;

public class ICDAO implements DAO<IC, String> {
	public ICDAO() {

	}

	@Override
	public void add(IC ic) {
		IC b = ic;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("ICs.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(b.getNf() + separadorDeLinha);
			buffer_saida.write(b.getDataFornecimento() + separadorDeLinha);
			buffer_saida.write(b.getStatus() + separadorDeLinha);
			buffer_saida.write(b.getUsuarioDesignado() + separadorDeLinha);
			buffer_saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Item de Configuracao '" + b.getNf() + "' no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public IC get(String chave) {
		IC retorno = null;
		IC ic = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("ICs.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				ic = new IC();
				ic.setNf(idSTR);
				ic.setDataFornecimento(LocalDate.parseDouble(buffer_entrada.readLine()));
				ic.setStatus(buffer_entrada.readLine());
				ic.setUsuarioDesignado(buffer_entrada.readLine()));

				if (chave.equals(ic.getNf())) {
					retorno = ic;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Item de Configuracao '" + ic.getNf() + "' do disco rígido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public List<IC> getAll() {
		List<IC> itens = new ArrayList<IC>();
		IC ic = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("ICs.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				ic = new IC();
				ic.setNf(idSTR);
				ic.setDataFornecimento(LocalDate.parse(buffer_entrada.readLine()));
				ic.setStatus(buffer_entrada.readLine());
				ic.setUsuarioDesignado(buffer_entrada.readLine());
				itens.add(ic);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler os Itens de Configuracao do disco rígido!");
			e.printStackTrace();
		}
		return itens;
	}

	@Override
	public void update(IC ic) {
		List<IC> itens = getAll();
		int index = itens.indexOf(ic);
		if (index != -1) {
			itens.set(index, ic);
		}
		saveToFile(itens);
	}

	@Override
	public void delete(IC ic) {
		List<IC> itens = getAll();
		int index = itens.indexOf(ic);
		if (index != -1) {
			itens.remove(index);
		}
		saveToFile(itens);
	}

	private void saveToFile(List<IC> notasFiscais) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("ICs.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (IC b : notasFiscais) {
				buffer_saida.write(b.getNfID() + separadorDeLinha);
				buffer_saida.write(b.getValorUnit() + separadorDeLinha);
				buffer_saida.write(b.getQuantidade() + separadorDeLinha);
				buffer_saida.write(b.getDataEmissaoNF() + separadorDeLinha);
				buffer_saida.write(b.getDescricao() + separadorDeLinha);
				buffer_saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar as Nota Fiscais no disco!");
			e.printStackTrace();
		}
	}

}
*/