package reflection;

import annotation.Controller;
import annotation.Repository;
import annotation.Service;
import java.lang.annotation.Annotation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReflectionsTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    void showAnnotationClass() {
        /*
            Reflections 클래스는 원하는 클래스를 찾기 위해 사용한다.
            prefix는 클래스를 찾을 때 출발 패키지를 의미한다.
         */
        Reflections reflections = new Reflections("examples");

        // 클래스 레벨에 @Controller, @Service, @Repository 애노테이션이 설정되어 모든 클래스 찾아 로그로 출력한다.
        List.of(Controller.class, Service.class, Repository.class)
                .forEach(a -> searchAndPrintWithAnnotation(reflections, a));
    }

    private void searchAndPrintWithAnnotation(Reflections reflections, Class<? extends Annotation> annotation) {
        reflections.getTypesAnnotatedWith(annotation)
                .stream()
                .map(Class::getSimpleName)
                .forEach(log::info);
    }
}
