package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimbursement_status")
@JsonIgnoreProperties({"reimbursements"})
public class ReimbursementStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reim_status_id;
	
	@Column(unique=true, nullable=false)
	@Enumerated(EnumType.STRING)
	private ReimbursementStatusEnum reim_status;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="statusId", fetch=FetchType.LAZY)
	private List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(int reim_status_id, ReimbursementStatusEnum reim_status,
			List<Reimbursement> reimbursements) {
		super();
		this.reim_status_id = reim_status_id;
		this.reim_status = reim_status;
		this.reimbursements = reimbursements;
	}

	public ReimbursementStatus(ReimbursementStatusEnum reim_status, List<Reimbursement> reimbursements) {
		super();
		this.reim_status = reim_status;
		this.reimbursements = reimbursements;
	}

	public int getReim_status_id() {
		return reim_status_id;
	}

	public void setReim_status_id(int reim_status_id) {
		this.reim_status_id = reim_status_id;
	}

	public ReimbursementStatusEnum getReim_status() {
		return reim_status;
	}

	public void setReim_status(ReimbursementStatusEnum reim_status) {
		this.reim_status = reim_status;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reim_status, reim_status_id, reimbursements);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatus other = (ReimbursementStatus) obj;
		return reim_status == other.reim_status && reim_status_id == other.reim_status_id
				&& Objects.equals(reimbursements, other.reimbursements);
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reim_status_id=" + reim_status_id + ", reim_status=" + reim_status
				+ ", reimbursements=" + reimbursements + "]";
	}

	
	
}
