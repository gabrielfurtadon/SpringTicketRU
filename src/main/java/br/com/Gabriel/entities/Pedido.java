
package br.com.Gabriel.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.Gabriel.dto.Mappers.EStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import br.com.Gabriel.entities.Ficha;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "comprado_em", insertable = true, updatable = true)
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "pago_em", insertable = true, updatable = true)
	@UpdateTimestamp
	private Timestamp updatedAt;

	@Column(name = "preco")
	private Double preco;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "status")
	private EStatus status;

	@Column(name = "ra")
	private String ra;

	@ManyToOne
	@JoinColumn(name = "user") // Update the column name to "user_id"
	private User user;

	public Pedido() {
		this.status = EStatus.PENDENTE;
	}

	public Pedido(int quantidade, String ra) {
		this.quantidade = quantidade;
		this.preco = quantidade * 3.50;
		this.ra = ra;
		this.status = EStatus.PENDENTE;
	}

	public Double total() {
		Ficha ficha = new Ficha();
		double sum = ficha.preco * quantidade;
		return sum;

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
		Pedido other = (Pedido) obj;
		return id == other.id;
	}

}
