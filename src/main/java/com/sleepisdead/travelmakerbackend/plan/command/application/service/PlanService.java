package com.sleepisdead.travelmakerbackend.plan.command.application.service;

import com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity.Member;
import com.sleepisdead.travelmakerbackend.member.command.domain.repository.MemberRepository;
import com.sleepisdead.travelmakerbackend.plan.command.application.dto.PlanDto;
import com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity.Plan;
import com.sleepisdead.travelmakerbackend.planjoin.command.domain.aggregate.entity.PlanJoin;
import com.sleepisdead.travelmakerbackend.exception.PlanNotFoundException;
import com.sleepisdead.travelmakerbackend.planjoin.command.infra.repository.PlanJoinRepository;
import com.sleepisdead.travelmakerbackend.plan.command.infra.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanJoinRepository planJoinRepository;
    private final MemberRepository memberRepository;

    //계획 저장
    @Transactional
    public Long save(Long member_id, PlanDto planDto) {
        Member member = memberRepository.findById(member_id).get();
        Plan plan  = Plan.createPlan(planDto);

        PlanJoin planJoin = PlanJoin.builder()
                .member(member)
                .plan(plan)
                .build();

        planJoinRepository.save(planJoin);
        planRepository.save(plan);

        return plan.getId();
    }

    //계획 전체 조회
    public List<PlanDto> findAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());
    }

    //계획 단건 조회
    public PlanDto findOne(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        return new PlanDto(plan);
    }

    //계획 수정(제목, 시작날짜, 끝날짜)
    @Transactional
    public Long update(Long id, PlanDto planDto) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        plan.updatePlan(planDto.getPlanTitle(), planDto.getStartDate(), planDto.getEndDate());
        return id;
    }

    //계획 삭제
    @Transactional
    public void delete(Member member, Long id) {
        List<PlanJoin> planJoins = planJoinRepository.findByMember_memberId(member.getMemberId());

        for (int i = 0; i < planJoins.size(); i++) {
            PlanJoin planJoin = planJoins.get(i);
            Long planJoinPlanId = planJoin.getPlan().getId();

            if (planJoinPlanId == id) {
                planRepository.deleteById(id);
            }
        }
    }
}
