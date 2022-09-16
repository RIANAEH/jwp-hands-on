package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ServletTest {

    @Test
    void testSharedCounter() throws Exception {
        // 톰캣 서버 시작
        final var tomcatStarter = TestHttpUtils.createTomcatStarter();
        tomcatStarter.start();

        // shared-counter 페이지를 3번 호출한다.
        final var PATH = "/shared-counter";
        TestHttpUtils.send(PATH);
        TestHttpUtils.send(PATH);
        final var response = TestHttpUtils.send(PATH);

        // 톰캣 서버 종료
        tomcatStarter.stop();

        assertThat(response.statusCode()).isEqualTo(200);

        /*
            sharedCounter 값은 누적된다.

            페이지를 호출하면 인스턴스 변수 sharedCounter의 값을 증가시킨다.
            톰캣은 SharedCounterServlet의 객체를 하나만 생성하고, 이를 계속해서 사용한다.
            따라서 인스턴스 변수인 sharedCounter의 결과가 계속 공유(누적)된다.
         */
        assertThat(Integer.parseInt(response.body())).isEqualTo(3);
    }

    @Test
    void testLocalCounter() throws Exception {
        // 톰캣 서버 시작
        final var tomcatStarter = TestHttpUtils.createTomcatStarter();
        tomcatStarter.start();

        // local-counter 페이지를 3번 호출한다.
        final var PATH = "/local-counter";
        TestHttpUtils.send(PATH);
        TestHttpUtils.send(PATH);
        final var response = TestHttpUtils.send(PATH);

        // 톰캣 서버 종료
        tomcatStarter.stop();

        assertThat(response.statusCode()).isEqualTo(200);

        /*
            localCounter 값은 메서드가 끝나면 소멸된다.

            페이지를 호출하면 지역 변수 localCounter의 값을 증가시킨다.
            페이지를 호출하면 인스턴스 변수 sharedCounter의 값을 증가시킨다.
            지역 변수이기 때문에 하나의 객체를 사용하더라도 메서드가 호출 될 때마다 변수가 새로 생성된다.
            따라서 localCounter의 값은 공유(누적)되지 않는다.
         */
        assertThat(Integer.parseInt(response.body())).isEqualTo(1);
    }
}
