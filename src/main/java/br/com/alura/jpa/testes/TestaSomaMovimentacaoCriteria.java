package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaSomaMovimentacaoCriteria {
	
	public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = criteriaBuilder.createQuery(BigDecimal.class);
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		Expression<BigDecimal> sum = criteriaBuilder.sum(root.get("valor"));
		query.select(sum);
		
		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);
		
		System.out.println("Soma das movimentações: " + typedQuery.getSingleResult());
	}

}
