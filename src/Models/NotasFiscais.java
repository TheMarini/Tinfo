package Models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Controllers.JsonFormatter;

public class NotasFiscais implements JsonFormatter {
	// implementar List em vez de ArrayList, o método independe da implementação.
	private List<NotaFiscal> listaNF;
	// private int numNFs = listaNF.size();

	public NotasFiscais() {
		listaNF = new ArrayList<NotaFiscal>();
	}

	public void adicionar(NotaFiscal nf) {
		listaNF.add(nf);
	}

	public NotaFiscal consultar(int id) throws Exception {
		if (listaNF.get(id) != null) {
			return (NotaFiscal) listaNF.get(id);
		} else {
			throw new Exception("Nota Fiscal não existe");
		}
	}

	public void remover(String descricao) {
		for (int pos = 0; pos < listaNF.size(); pos++) {
			if (descricao.equalsIgnoreCase(listaNF.get(pos).getDescricao())) {
				listaNF.remove(pos);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder valor = new StringBuilder();
		for (int i = 0; i < listaNF.size(); i++) {
			valor.append(listaNF.get(i) + "\n");
		}
		return valor.toString();
	}

	@Override
	public JSONArray toJsonArray() {
		JSONArray array = new JSONArray();
		for (int i = 0; i < listaNF.size(); i++) {
			array.put(listaNF.get(i).toJson());
		}
		return array;
	}

	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("numNFs", this.getNumNFs());
		obj.put("listaDeNotasFiscais", this.toJsonArray());
		return obj;
	}

	public int getNumNFs() {
		return this.listaNF.size();
	}

}
