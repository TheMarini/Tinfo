package Models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Controllers.JsonFormatter;

public class Inventario implements JsonFormatter {
	private List<IC> listaIC;

	public Inventario() {
		listaIC = new ArrayList<IC>();
	}

	public void adicionar(IC ic) {
		listaIC.add(ic);
	}

	public IC consultar(int id) {
		if (listaIC.get(id) != null) {
			return (IC) listaIC.get(id);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder valor = new StringBuilder();
		for (int i = 0; i < listaIC.size(); i++) {
			valor.append(listaIC.get(i) + "\n");
		}
		return valor.toString();
	}
/*
	@Override
	public JSONArray toJsonArray() {
		JSONArray array = new JSONArray();
		for (int i = 0; i < listaIC.size(); i++) {
			array.put(listaIC.get(i).toJson());
		}
		return array;
	}
*/
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("numNFs", this.getNumNFs());
		obj.put("listaDeNotasFiscais", this.toJsonArray());
		return obj;
	}

	public int getNumNFs() {
		return this.listaIC.size();
	}

}
