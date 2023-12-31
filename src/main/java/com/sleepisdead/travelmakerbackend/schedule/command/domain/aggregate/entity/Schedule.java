package com.sleepisdead.travelmakerbackend.schedule.command.domain.aggregate.entity;

import com.sleepisdead.travelmakerbackend.common.BaseTimeEntity;
import com.sleepisdead.travelmakerbackend.map.command.domain.aggregate.entity.Map;
import com.sleepisdead.travelmakerbackend.plan.command.domain.aggregate.entity.Plan;
import com.sleepisdead.travelmakerbackend.schedule.command.application.dto.ScheduleDto;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    private int price;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    private String memo;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    //연관관계 메서드
    public void addPlan(Plan plan) {
        this.plan = plan;
        plan.getSchedules().add(this);
    }

    public void addMap(Map map) {
        this.map = map;
        map.addSchedule(this);
    }

    public void addSchedule(String scheduleTitle, int price, LocalDateTime startDateTime, LocalDateTime endDateTime, String memo) {
        this.scheduleTitle = scheduleTitle;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.memo = memo;
    }

    //생성 메서드
    public static Schedule createSchedule(Plan plan, Map map, ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.addPlan(plan);
        schedule.addMap(map);
        schedule.addSchedule(scheduleDto.getScheduleTitle(), scheduleDto.getPrice(), scheduleDto.getStartDateTime(), scheduleDto.getEndDateTime(), scheduleDto.getMemo());

        return schedule;
    }

}
