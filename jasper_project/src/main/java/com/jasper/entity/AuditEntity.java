package com.jasper.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_record")
public class AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public AuditEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuditEntity(int id, String name, String email, LocalDateTime createdTime, LocalDateTime modifiedTime) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "AuditEntity [id=" + id + ", name=" + name + ", email=" + email + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + "]";
	}

	@PrePersist

	protected void onCreate() {

		this.createdTime = LocalDateTime.now();

		this.modifiedTime = this.createdTime;

	}

	@PreUpdate

	protected void onUpdate() {

		this.modifiedTime = LocalDateTime.now();

	}
}
