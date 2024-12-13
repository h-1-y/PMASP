package h1y.my.portfolio.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import h1y.my.portfolio.config.jwt.JwtUtils;
import h1y.my.portfolio.dto.ExperiencedRequestDto;
import h1y.my.portfolio.dto.MemberInfoDto;
import h1y.my.portfolio.entity.Experienced;
import h1y.my.portfolio.entity.Job;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.ExperiencedRepository;
import h1y.my.portfolio.repository.MemberRepositoy;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ExperiencedServiceImplTest {

	@Autowired
	EntityManager em;
	
	@Autowired
	ExperiencedRepository experiencedRepository;
	
	@Autowired
	MemberRepositoy memberRepositoy;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Test
	public void setExperiencedTest() {
		
		Job job = new Job("dev");
		em.persist(job);
		
		Member member = new Member("tomhan", "5212", "han", job);
		em.persist(member);
		
		MemberInfoDto infoDto = member.toDto();
		String accessToken = jwtUtils.createAccessToken(infoDto);
		
		System.out.println("accessToken ======== " + accessToken);
		
		Long memberId = jwtUtils.getMemberId(accessToken);
		
		assertThat(memberId).isEqualTo(1);
		
		em.flush();
		em.clear();
		
		Member findMember = memberRepositoy.getById(memberId);
		
		assertThat(findMember.getName()).isEqualTo("han");
		
		ExperiencedRequestDto expDto = new ExperiencedRequestDto();
		
		expDto.setCompanyName("cubeA");
		expDto.setDepartment("dev team");
		expDto.setRank("선임");
		expDto.setStartDate("202101");
		expDto.setEndDate("202401");
		
		Experienced exp = expDto.toEntity(findMember);
		experiencedRepository.save(exp);
		
		em.flush();
		em.clear();
		
		Experienced findExp = experiencedRepository.findById(1L).get();
		
		assertThat(findExp.getId()).isEqualTo(1);
		assertThat(findExp.getCompanyName()).isEqualTo("cubeA");
		assertThat(findExp.getDepartment()).isEqualTo("dev team");
		assertThat(findExp.getRank()).isEqualTo("선임");
		assertThat(findExp.getMember().getId()).isEqualTo(1);
		assertThat(findExp.getMember().getName()).isEqualTo("han");
		
	}

}
