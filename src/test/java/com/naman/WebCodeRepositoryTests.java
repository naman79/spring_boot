package com.naman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.naman.domain.WebCode;
import com.naman.persistence.WebCodeRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebCodeRepositoryTests {

	@Autowired
	WebCodeRepository repo;
	
	@Test
	public void testList() {
		
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "codeno");
		
		Page<WebCode> result = repo.findAll(
				repo.makePredicate(null, null), pageable);
		
		log.info("PAGE: " + result.getPageable());
		
		log.info("------------------------------");
		result.getContent().forEach(code -> log.info(""+ code));
		
	}
	
}
