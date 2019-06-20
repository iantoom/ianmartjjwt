package com.ianmart.jwt.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ianmart.jwt.domains.Address;
import com.ianmart.jwt.domains.PhoneNumber;

public class CurrentUser {

	private Long id;
	
	private Name name;
	
	private String picture;
	
	private String email;
	
	private boolean enabled;
	
	private String role;
	
	private Date dateOfBirth;
	
	private Address address;
	
	private List<PhoneNumber> phones;
	
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneNumber> phoneNumber) {
		this.phones = phoneNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, createdDate, dateOfBirth, email, enabled, id, name, phones, picture, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CurrentUser)) {
			return false;
		}
		CurrentUser other = (CurrentUser) obj;
		return Objects.equals(address, other.address) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& enabled == other.enabled && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phones, other.phones) && Objects.equals(picture, other.picture)
				&& Objects.equals(role, other.role);
	}


	public class Name {
		
		private String firstName;
		
		private String middleName;
		
		private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(firstName, lastName, middleName);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Name)) {
				return false;
			}
			Name other = (Name) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance())) {
				return false;
			}
			return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
					&& Objects.equals(middleName, other.middleName);
		}

		private CurrentUser getEnclosingInstance() {
			return CurrentUser.this;
		}
	}
}
