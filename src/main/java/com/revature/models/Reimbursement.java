package com.revature.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimbursements")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(nullable=false)
	private double amount;
	
	@Column(nullable=false, columnDefinition="TIMESTAMP")
	private Instant submitted;
	
	@Column(columnDefinition="TIMESTAMP")
	private Instant resolved;
	
	@Column()
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="authorId")
	private User authorId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="resolverId")
	private User resolverId;
	
	//FOREIGN KEY
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="StatusId")
	private ReimbursementStatus statusId;
	
	//FOREIGN KEY
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="TypeId")
	private ReimbursementType typeId;
	
	public Reimbursement() {
	}

	public Reimbursement(int id, double amount, Instant submitted, Instant resolved, String description, User authorId,
			User resolverId, ReimbursementStatus statusId, ReimbursementType typeId) {
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

	public Reimbursement(double amount, Instant submitted, Instant resolved, String description, User authorId,
			User resolverId, ReimbursementStatus statusId, ReimbursementType typeId) {
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

	public Instant getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Instant submitted) {
		this.submitted = submitted;
	}

	public Instant getResolved() {
		return resolved;
	}

	public void setResolved(Instant resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthorId() {
		return authorId;
	}

	public void setAuthorId(User authorId) {
		this.authorId = authorId;
	}

	public User getResolverId() {
		return resolverId;
	}

	public void setResolverId(User resolverId) {
		this.resolverId = resolverId;
	}

	public ReimbursementStatus getStatusId() {
		return statusId;
	}

	public void setStatusId(ReimbursementStatus statusId) {
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
