package com.Mario.Nhanengue.web.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Classe abstracta genérica de suporte, partilhada por todas as entidades
 * JPA do sistema (Usuario, Hotel, Reserva, etc.). Concentra o que é comum a
 * qualquer entidade: chave primária autogerada e equals/hashCode baseados
 * no id.
 *
 * Usa @MappedSuperclass (não @Entity): não gera tabela própria nem
 * relação de herança JOINED — os seus atributos são simplesmente
 * "copiados" para a tabela de cada entidade que a estenda. É diferente,
 * e complementar, da herança JOINED usada em Usuario -> Cliente (secção
 * 8.2), que essa sim gera uma tabela "usuario" partilhada.
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		return Objects.equals(id, other.id);
	}

}