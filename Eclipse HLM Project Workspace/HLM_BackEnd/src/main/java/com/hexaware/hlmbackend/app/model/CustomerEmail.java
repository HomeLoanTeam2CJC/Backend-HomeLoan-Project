package com.hexaware.hlmbackend.app.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class CustomerEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerEmailId;
	private String fromSender;
	private String toReceiver;
	private String subject;
	private String textBody;
	@Lob
	private byte[] attachment;

}


//{
//	"subject":""
//	"textBody": ""
//}

