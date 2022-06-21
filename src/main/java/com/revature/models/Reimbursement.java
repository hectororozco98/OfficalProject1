package com.revature.models;

import java.util.Objects;

public class Reimbursement {
	
	private int Id;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;
	
	public Reimbursement() {
		
		
	}

	public Reimbursement(int id, double amount, String submitted, String resolved, String description, int authorId,
			int resolverId, int statusId, int typeId) {
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
			int resolverId, int statusId, int typeId) {
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
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
