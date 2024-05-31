package org.sopt.practice.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.practice.entity.Part;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.request.MemberCreateDto;
import org.sopt.practice.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ApiTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested//여러 테스트를 중첩해서 할 수 있도록
    @DisplayName("멤버 생성 테스트")
    public class CreateMember {

        @Test
        @DisplayName("멤버 생성 성공")
        public void createMemberSuccess() throws Exception {
            //given
            final var request = new MemberCreateDto("박재연", Part.SERVER, 27);//var은 타입 추론

            //when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/members")
                    .then().log().all().extract();
            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        }
        /*@Test
        @DisplayName("멤버 생성 실패")*/
    }
}
