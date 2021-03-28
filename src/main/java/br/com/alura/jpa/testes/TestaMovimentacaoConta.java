package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class TestaMovimentacaoConta {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("alura");
		EntityManager em = factory.createEntityManager();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select new br.com.alura.jpa.modelo.MediaComData(avg(m.valor), day(m.data), month(m.data), year(m.data)) ");
		sb.append(" from Movimentacao m ");
		sb.append(" group by day(m.data), month(m.data), year(m.data) ");
		
		TypedQuery<MediaComData> query = em.createQuery(sb.toString(), MediaComData.class);
		
		List<MediaComData> resultList = query.getResultList();
		
		resultList.forEach(media -> System.out.println("Média do dia " + media.getDia() + "/" + media.getMes() + "/" + media.getAno() + " : " + media.getAvg()));
	}

}
