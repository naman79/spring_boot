package com.naman.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "tbl_codes")
@EqualsAndHashCode(of = "codeno")
@ToString
public class WebCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codeno;
	
	private String code1;
	
	private String code2;
	
	private String name;

	private String isuse;
	
	private String descript;
	
	private String name2;
	
	private String name3;
	
	private String name4;
	
	private String tenant_id;
	
	private String companyid;
	
	@CreationTimestamp
	private Timestamp createdate;
	
	@UpdateTimestamp
	private Timestamp modifydate;
}
