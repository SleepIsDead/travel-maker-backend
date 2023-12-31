package com.sleepisdead.travelmakerbackend.schedule.command.application.service;

import com.sleepisdead.travelmakerbackend.schedule.command.application.dto.ScheduleDto;
import com.sleepisdead.travelmakerbackend.map.command.domain.aggregate.entity.Map;
import com.sleepisdead.travelmakerbackend.map.command.domain.aggregate.entity.MapStatus;
import com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity.Plan;
import com.sleepisdead.travelmakerbackend.schedule.command.domain.aggregate.entity.Schedule;
import com.sleepisdead.travelmakerbackend.map.command.infra.repository.MapRepository;
import com.sleepisdead.travelmakerbackend.plan.command.infra.repository.PlanRepository;
import com.sleepisdead.travelmakerbackend.schedule.command.infra.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PlanRepository planRepository;
    private final MapRepository mapRepository;

    //일정 저장
    @Transactional
    public Long save(Long plan_id, ScheduleDto scheduleDto) {
        Plan plan = planRepository.findById(plan_id).orElseThrow();

        Map map = Map.builder()
                .mapStatus(MapStatus.SCHEDULE)
                .build();

        Schedule schedule = Schedule.createSchedule(plan, map, scheduleDto);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    //계획 별로 일정 전체목록 조회
    public List<ScheduleDto> findAllSchedules(Long planId) {
        return scheduleRepository.findAllSchedules(planId)
                .stream()
                .map(ScheduleDto::new)
                .collect(Collectors.toList());
    }

    //일정 단건 조회
    public ScheduleDto findOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        return new ScheduleDto(schedule);
    }

    //일정 수정
    public Long update(Long id, ScheduleDto scheduleDto) {


        return id;
    }

    //일정 삭제
    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
