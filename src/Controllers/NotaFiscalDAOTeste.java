package Controllers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Models.NotaFiscal;

class NotaFiscalDAOTeste {
	private NotaFiscalDAO dao;
	private NotaFiscal n1;
	private NotaFiscal n2;
	
	@Before
	void init() {
		n1 = new NotaFiscal("1", 10.01, 5, LocalDate.of(2018, 9, 02), "Servidor");
		n2 = new NotaFiscal("2", 30.01, 4, LocalDate.of(2016, 2, 03), "Computador");
		
		dao = new NotaFiscalDAO();
		
		dao.add(n1);
		dao.add(n2);
	}
	
	@After
	void clean() {
		File file = new File("notasfiscais.txt");
		file.delete();
	}
	
	@Test
	void testAdd() {
		NotaFiscal n3 = new NotaFiscal("3", 50.50, 1, LocalDate.of(2017, 10, 02), "Modem");
		dao.add(n3);
		
		List<NotaFiscal> lista = dao.getAll();
		assertEquals(n1.getNfID(), lista.get(0).getNfID());
		assertEquals(n2.getNfID(), lista.get(1).getNfID());
		assertEquals(n3.getNfID(), lista.get(2).getNfID());
	}
	
	@Test
	void testGet() {
		NotaFiscal nota1 = dao.get("Computador");
		NotaFiscal nota2 = dao.get("Servidor");
		assertEquals(nota1.getNfID(), nota1.getNfID());
		assertEquals(nota2.getNfID(), nota2.getNfID());
	}
	
	@Test
	void testGetAll() {
		List<NotaFiscal> lista = dao.getAll();
		assertEquals(n1.getNfID(), lista.get(0).getNfID());
		assertEquals(n2.getNfID(), lista.get(1).getNfID());
	}
	
	@Test
	void testDelete() {
		NotaFiscal n3 = new NotaFiscal("3", 50.99, 5, LocalDate.of(2018, 9, 02), "Modem");
		dao.add(n3);
		
		List<NotaFiscal> lista1 = dao.getAll();
		int tam1 = lista1.size();
		
		System.out.println(tam1);
		dao.delete(n3);
		
		List<NotaFiscal> lista2 = dao.getAll();
		int tam2 = lista2.size();
		
		System.out.println(tam2);
		assertEquals(tam1-1, tam2);
	}
	
	@Test
	void testUpdate() {
		n1.setDescricao("Manter o site online");
		dao.update(n1);
		
		NotaFiscal deposito = dao.get("Servidor");
		assertEquals(n1.getDescricao(), deposito.getDescricao());
		
	}
	
}