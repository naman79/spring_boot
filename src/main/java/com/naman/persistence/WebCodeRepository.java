package com.naman.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.naman.domain.QWebCode;
import com.naman.domain.WebCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebCodeRepository extends CrudRepository<WebCode, Integer>, QuerydslPredicateExecutor<WebCode>  {

	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QWebCode code = QWebCode.webCode;
		
		builder.and(code.codeno.gt(0));
		
		if(type == null) {
			return builder;
		}
		
		switch(type) {
		case "c":
			builder.and(code.code1.like("%" + keyword + "%"));
			break;
		case "n":
			builder.and(code.name.like("%" + keyword + "%"));
			break;
		case "d":
			builder.and(code.descript.like("%" + keyword + "%"));
			break;	
		}
		
		return builder;
	}
	
	@Query("SELECT MAX(c.code2) FROM WebCode c WHERE c.code1 = ?1")
	public String findByCode2Max(String code1);
	
}
