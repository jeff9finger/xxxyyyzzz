package com.interview.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * The base class for all entities
 * @param <IdType> The type of the id and the primary key
 */
@Getter
@MappedSuperclass
public abstract class BaseEntity<IdType extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3330193491362488868L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID", nullable = false, unique = true)
   	private IdType id;

		public void setId(IdType id) {
   		this.id = id;
   	}

    @Override
   	public boolean equals(Object o) {
   		if (this == o)
   			return true;
   		if (o == null || getClass() != o.getClass()) {
   			return false;
   		}

   		BaseEntity<IdType> entity = (BaseEntity<IdType>) o;

   		return Objects.equals(id, entity.id);
   	}

    @Override
   	public int hashCode() {
   		return Objects.hash(id);
   	}
}
