package hello.jdbcStudy.service;

import hello.jdbcStudy.domain.Member;
import hello.jdbcStudy.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - @Transational AOP*/

@Slf4j
public class MemberServiceV3_3 {

    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_3( MemberRepositoryV3 memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        bizLogic(fromId, toId, money);
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        vaildation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void vaildation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException(("이체중 예외 발생"));
        }
    }

}
