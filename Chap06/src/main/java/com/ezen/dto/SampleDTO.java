package com.ezen.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter(), Setter(), ToString(), equals(), hashCode() 모든 기능 사용 가능
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleDTO {

	private Long sno;
	private String first;
	private String last;
	private LocalDateTime regTime;
}
