package com.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Represents  weather sensor in this application
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "SENSOR")
public class Sensor extends BaseEntity<Long> implements Serializable {
		@Column(name = "NAME")
		private String name;

		@Getter
		public static enum Parameter {
				ID("id"),
				NAME("name");

				private final String fieldName;
				Parameter(String fieldName) {
					this.fieldName = fieldName;
			 }
				public static Sensor.Parameter fromApiParameter(String key) {
				for (Sensor.Parameter b : Sensor.Parameter.values()) {
					if (b.fieldName.equals(key)) {
						return b;
					}
				}
				throw new IllegalArgumentException("Unexpected value '" + key + "'");
			}
	 }
}
