package com.cos.photogramstart.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cos.photogramstart.domain.BaseTimeEntity;
import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20, nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website;
	private String bio;
	
	@Column(nullable = false)
	private String email;
	
	private String phone;
	private String gender;
	private String profileImageUrl;
	private String role;
	
	// 테이블에 컬럼 만들지말고 연관관계 주인은 Image테이블의 user(핃드명)
	// LAZY : User를 SELECT 할 때 User id로 등록된 image들은 가져오는것이 아니고 getImages() 함수가 호출 될때 가져 옴
	// EAGER : User를 SELECT 할 때 User id로 등록된 image들 전부 Join 해서 가죠 옴
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Image> images;
}
