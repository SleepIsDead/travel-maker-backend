package com.sleepisdead.travelmakerbackend.member.command.domain.aggregate.entity;

import com.sleepisdead.travelmakerbackend.common.BaseTimeEntity;
import com.sleepisdead.travelmakerbackend.planjoin.command.domain.aggregate.entity.PlanJoin;
import com.sleepisdead.travelmakerbackend.question.command.domain.aggregate.entity.Question;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "Member")
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "image_source")
    private String imageSource;

    @Column(name = "report_count", nullable = false)
    private int reportCount;

    @Column(name = "social_login", nullable = false)
    private String socialLogin;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "access_token_expire_date", nullable = false)
    private long accessTokenExpireDate;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "refresh_token_expire_date", nullable = false)
    private long refreshTokenExpireDate;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "is_deleted", columnDefinition = "varchar (2)", nullable = false)
    private String isDeleted;

    @Column(name = "preferred_location")
    private String preferredLocation;

    @Column(name = "preferred_type")
    private String preferredType;

    // 친구 초대, 문의 필드 추가
    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<PlanJoin> planJoins = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<Question> questions = new ArrayList<>();

    public List<PlanJoin> getPlanJoins() {
        return planJoins;
    }

    public void setPlanJoins(List<PlanJoin> planJoins) {
        this.planJoins = planJoins;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Member() {}

    public Member(long memberId, String nickname, String imageSource, int reportCount,
                  String socialLogin, String socialId, String accessToken, long accessTokenExpireDate, String refreshToken,
                  long refreshTokenExpireDate, String email, String gender, String isDeleted,
                  String preferredLocation, String preferredType) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.imageSource = imageSource;
        this.reportCount = reportCount;
        this.socialLogin = socialLogin;
        this.socialId = socialId;
        this.accessToken = accessToken;
        this.accessTokenExpireDate = accessTokenExpireDate;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireDate = refreshTokenExpireDate;
        this.email = email;
        this.gender = gender;
        this.isDeleted = isDeleted;
        this.preferredLocation = preferredLocation;
        this.preferredType = preferredType;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public String getSocialLogin() {
        return socialLogin;
    }

    public void setSocialLogin(String socialLogin) {
        this.socialLogin = socialLogin;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPreferredType() {
        return preferredType;
    }

    public void setPreferredType(String preferredType) {
        this.preferredType = preferredType;
    }


    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getAccessTokenExpireDate() {
        return accessTokenExpireDate;
    }

    public void setAccessTokenExpireDate(long accessTokenExpireDate) {
        this.accessTokenExpireDate = accessTokenExpireDate;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getRefreshTokenExpireDate() {
        return refreshTokenExpireDate;
    }

    public void setRefreshTokenExpireDate(long refreshTokenExpireDate) {
        this.refreshTokenExpireDate = refreshTokenExpireDate;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "Member{" +
            "memberId=" + memberId +
            ", nickname='" + nickname + '\'' +
            ", imageSource=" + imageSource +
            ", reportCount=" + reportCount +
            ", socialLogin='" + socialLogin + '\'' +
            ", socialId=" + socialId +
            ", accessToken='" + accessToken + '\'' +
            ", accessTokenExpireDate=" + accessTokenExpireDate +
            ", refreshToken='" + refreshToken + '\'' +
            ", refreshTokenExpireDate=" + refreshTokenExpireDate +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", isDeleted='" + isDeleted + '\'' +
            ", preferredLocation='" + preferredLocation + '\'' +
            ", preferredType='" + preferredType + '\'' +
            '}';
    }
}
