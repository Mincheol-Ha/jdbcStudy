package hello.jdbcStudy.service;

import hello.jdbcStudy.domain.Member;
import hello.jdbcStudy.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
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
