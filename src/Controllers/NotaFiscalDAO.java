package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.NotaFiscal;
import java.io.PrintStream;

public class NotaFiscalDAO implements DAO<NotaFiscal, String> {
	public NotaFiscalDAO() {

	}

	@Override
	public void add(NotaFiscal nf) {
		NotaFiscal b = nf;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("notasfiscais.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(b.getNfID() + separadorDeLinha);
			buffer_saida.write(b.getValorUnit() + separadorDeLinha);
			buffer_saida.write(b.getQuantidade() + separadorDeLinha);
			buffer_saida.write(b.getDataEmissaoNF() + separadorDeLinha);
			buffer_saida.write(b.getDescricao() + separadorDeLinha);
			buffer_saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar a Nota Fiscal '" + b.getNfID() + "' no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public NotaFiscal get(String chave) {
		NotaFiscal retorno = null;
		NotaFiscal nf = null;
		
		
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("notasfiscais.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				nf = new NotaFiscal();
				nf.setNfID(idSTR);
				nf.setValorUnit(Double.parseDouble(buffer_entrada.readLine()));
				nf.setQuantidade(Integer.parseInt(buffer_entrada.readLine()));
				nf.setDataEmissaoNF(LocalDate.parse(buffer_entrada.readLine()));
				nf.setDescricao(buffer_entrada.readLine());

				if (chave.equals(nf.getDescricao())) {
					retorno = nf;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a Nota Fiscal '" + nf.getDescricao() + "' do disco rígido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public List<NotaFiscal> getAll() {
		List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
		NotaFiscal nf = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("notasfiscais.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				nf = new NotaFiscal();
				nf.setNfID(idSTR);
				nf.setValorUnit(Double.parseDouble(buffer_entrada.readLine()));
				nf.setQuantidade(Integer.parseInt(buffer_entrada.readLine()));
				nf.setDataEmissaoNF(LocalDate.parse(buffer_entrada.readLine()));
				nf.setDescricao(buffer_entrada.readLine());
				notasFiscais.add(nf);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler as Notas Fiscais do disco rígido!");
			e.printStackTrace();
		}
		return notasFiscais;
	}

	@Override
	public void update(NotaFiscal p) {
		List<NotaFiscal> notasFiscais = getAll();
		int index = notasFiscais.indexOf(p);
		if (index != -1) {
			notasFiscais.set(index, p);
		}
		saveToFile(notasFiscais);
	}

	@Override
	public void delete(NotaFiscal p) {
		List<NotaFiscal> notasFiscais = getAll();
		int index = notasFiscais.indexOf(p);
		if (index != -1) {
			notasFiscais.remove(index);
		}
		saveToFile(notasFiscais);
	}

	private void saveToFile(List<NotaFiscal> notasFiscais) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("notasfiscais.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (NotaFiscal b : notasFiscais) {
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
