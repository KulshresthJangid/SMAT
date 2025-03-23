package com.nerdyGeek.smat.documents;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(indexName = "instagram_analytics")
public class InstagramAnalytics {

	@Id
	private String postId;

	@Field(type = FieldType.Date, format = DateFormat.date_optional_time)
	private LocalDateTime timestamp;

	@Field(type = FieldType.Integer)
	private int likes;

	@Field(type = FieldType.Integer)
	private int comments;

	@Field(type = FieldType.Integer)
	private int shares;

	@Field(type = FieldType.Integer)
	private int reach;

	@Field(type = FieldType.Text)
	private String category;
}
