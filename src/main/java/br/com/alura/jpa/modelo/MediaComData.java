package br.com.alura.jpa.modelo;

public class MediaComData {
	
	private Double avg;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	public MediaComData(Double avg, Integer dia, Integer mes, Integer ano) {
		this.avg = avg;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public Double getAvg() {
		return avg;
	}
	
	public Integer getDia() {
		return dia;
	}
	
	public Integer getMes() {
		return mes;
	}
	
	public Integer getAno() {
		return ano;
	}
	
}
