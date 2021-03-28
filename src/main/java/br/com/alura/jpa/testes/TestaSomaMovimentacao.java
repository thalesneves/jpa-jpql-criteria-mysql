package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestaSomaMovimentacao {
	
	public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<BigDecimal> query = em.createNamedQuery("somarValores", BigDecimal.class);
		
		System.out.println("Soma das movimentações: " + query.getSingleResult());
	}

}
