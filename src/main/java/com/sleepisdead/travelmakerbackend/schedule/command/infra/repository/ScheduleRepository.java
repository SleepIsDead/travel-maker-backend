package com.sleepisdead.travelmakerbackend.schedule.command.infra.repository;

import com.sleepisdead.travelmakerbackend.schedule.command.domain.aggregate.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //시작시간 빠른 순서대로 오름차순 정렬
    @Query("select s from Schedule s where s.plan.id = :planId order by s.startDateTime")
    List<Schedule> findAllSchedules(@Param("planId") Long planId);
}
