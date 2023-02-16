package com.cos.photogramstart.domain.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.photogramstart.domain.BaseTimeEntity;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String content;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@JoinColumn(name = "imageId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Image image;
	
}
