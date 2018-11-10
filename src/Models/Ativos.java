package Models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Controllers.JsonFormatter;

public class Ativos implements JsonFormatter {
	private List<IC> ativos;

	public Ativos() {
		ativos = new ArrayList<IC>();
	}

	public void adicionar(IC ic) {
		ativos.add(ic);
	}

	public IC consultar(int id) {
		if (ativos.get(id) != null) {
			return (IC) ativos.get(id);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder valor = new StringBuilder();
		for (int i = 0; i < ativos.size(); i++) {
			valor.append(ativos.get(i) + "\n");
		}
		return valor.toString();
	}

	/*
	 * @Override public JSONArray toJsonArray() { JSONArray array = new JSONArray();
	 * for (int i = 0; i < listaIC.size(); i++) {
	 * array.put(listaIC.get(i).toJson()); } return array; }
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("quantAtivos", this.getQuantidade());
		obj.put("ativos", this.toJsonArray());
		return obj;
	}

	public int getQuantidade() {
		return this.ativos.size();
	}

}