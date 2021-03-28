package br.com.alura.jpa.testes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaMovimentacaoFiltradaPorDataCriteria {
	
	public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> query = criteriaBuilder.createQuery(Movimentacao.class);
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		List<Predicate> predicates = filtrarMovimentacoesPorData(criteriaBuilder, root, null, null, 2017);
		
		// new Predicate[0] -> serve para evitar alocar memória desnecessariamente
		// vai criar o array de predicates de acordo com o necessário
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Movimentacao> typedQuery = em.createQuery(query);
		
		List<Movimentacao> movimentacoes = typedQuery.getResultList();
		movimentacoes.forEach(movimentacao -> {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Valor: " + movimentacao.getValor());
			System.out.println("-----------------------------------------");
		});
		
	}
	
	public static List<Predicate> filtrarMovimentacoesPorData(CriteriaBuilder criteriaBuilder, Root<Movimentacao> root, Integer dia, Integer mes, Integer ano) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (dia != null) {
			predicates.add(criteriaBuilder.equal(criteriaBuilder.function("day", Integer.class, root.get("data")), dia));
		}
		
		if (mes != null) {
			predicates.add(criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, root.get("data")), mes));		
		}
		
		if (ano != null) {
			predicates.add(criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("data")), ano));
		}
		
		return predicates;
	}
	
}
