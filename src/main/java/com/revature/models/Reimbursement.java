package com.revature.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reimbursements")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(nullable=false)
	private double amount;
	
	@Column(nullable=false)
	//@Temporal(TemporalType.DATE)
	private String submitted;
	
	@Column()
	//@Temporal(TemporalType.DATE)
	private String resolved;
	
	@Column()
	private String description;
	
	//FOREIGN KEY 
	private int authorId;
	
	//FOREIGN KEY
	private int resolverId;
	
	//FOREIGN KEY
	private int statusId;
	
	//FOREIGN KEY
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="TypeId")
	private ReimbursementType typeId;
	
	public Reimbursement() {
	}

	public Reimbursement(int id, double amount, String submitted, String resolved, String description, int authorId,
			int resolverId, int statusId, ReimbursementType typeId) {
		super();
		Id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(double amount, String submitted, String resolved, String description, int authorId,
			int resolverId, int statusId, ReimbursementType typeId) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public ReimbursementType getTypeId() {
		return typeId;
	}

	public void setTypeId(ReimbursementType typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, amount, authorId, description, resolved, resolverId, statusId, submitted, typeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Id == other.Id && Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& authorId == other.authorId && Objects.equals(description, other.description)
				&& Objects.equals(resolved, other.resolved) && resolverId == other.resolverId
				&& statusId == other.statusId && Objects.equals(submitted, other.submitted) && typeId == other.typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [Id=" + Id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", authorId=" + authorId + ", resolverId=" + resolverId
				+ ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}
	
	
	

}
