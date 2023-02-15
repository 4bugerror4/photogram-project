package com.cos.photogramstart.domain.subscribe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.BaseTimeEntity;
import com.cos.photogramstart.domain.user.User;

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
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "subscribe_uk",
						columnNames = {"fromUserId", "toUserId"}
						)
		}
		)
public class Subscribe extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser; // 구독 하는 쪽

	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // 구독 받는 쪽

}
