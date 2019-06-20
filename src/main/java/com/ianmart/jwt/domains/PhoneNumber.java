package com.ianmart.jwt.domains;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number")
public class PhoneNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330120666480467149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name= "user_id", nullable = false)
	private User user;
	
	@Column(name="type")
	private String type;
	
	@Column(name="number")
	private String number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, number, type, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber other = (PhoneNumber) obj;
		return Objects.equals(id, other.id) && Objects.equals(number, other.number) && Objects.equals(type, other.type)
				&& Objects.equals(user, other.user);
	}
	
}
