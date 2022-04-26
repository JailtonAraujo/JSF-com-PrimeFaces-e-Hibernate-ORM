package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lancamentos")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long numeroNota;

	@Temporal(TemporalType.DATE)
	private Date dataLancamento = new Date();
	
	private Double valor;

	@JoinColumn(name = "id_usuario")
	@ForeignKey(name = "fk_usuario_lancamento")
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa usuario;

	public Lancamento() {
	}
	
	

	public Lancamento(Long id, Date dataLancamento, Double valor, Long idUser) {
		this.id = id;
		this.dataLancamento = dataLancamento;
		this.valor = valor;
		this.usuario = new Pessoa();
		this.usuario.setId(idUser);
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Long numeroNota) {
		this.numeroNota = numeroNota;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
