package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

@Entity
@Table(name="reimbursement_type")
public class ReimbursementType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reim_type_id")
	private int reim_type_id;
	
	@Column(unique=true, nullable = false)
	@Enumerated(EnumType.STRING)
	private ReimbursementTypeEnum reim_type;
	
	@OneToMany(mappedBy="typeId", fetch=FetchType.LAZY)
	private List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

	public ReimbursementType() {
		super();
	}

	public ReimbursementType(int reim_type_id, ReimbursementTypeEnum reim_type) {
		super();
		this.reim_type_id = reim_type_id;
		this.reim_type = reim_type;
	}

	public ReimbursementType(ReimbursementTypeEnum reim_type) {
		super();
		this.reim_type = reim_type;
	}

	public int getReim_type_id() {
		return reim_type_id;
	}

	public void setReim_type_id(int reim_type_id) {
		this.reim_type_id = reim_type_id;
	}

	public ReimbursementTypeEnum getReim_type() {
		return reim_type;
	}

	public void setReim_type(ReimbursementTypeEnum reim_type) {
		this.reim_type = reim_type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reim_type, reim_type_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementType other = (ReimbursementType) obj;
		return reim_type == other.reim_type && reim_type_id == other.reim_type_id;
	}

	@Override
	public String toString() {
		return "ReimbursementType [reim_type_id=" + reim_type_id + ", reim_type=" + reim_type + "]";
	}
	
	
	
}
