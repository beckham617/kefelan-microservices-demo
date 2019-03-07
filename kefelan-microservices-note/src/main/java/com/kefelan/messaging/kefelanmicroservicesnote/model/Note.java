package com.kefelan.messaging.kefelanmicroservicesnote.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "note", catalog = "mstest")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer userId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name="datetime")
	private Timestamp datetime; 
	
	@Column(name="note")
	private String note;

	public Note() {
	}

	public Note(Integer id, Integer userId, Timestamp datetime, String note) {
		this.id = id;
		this.userId = userId;
		this.datetime = datetime;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
