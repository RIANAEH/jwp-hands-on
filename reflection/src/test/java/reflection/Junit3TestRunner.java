package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class Junit3TestRunner {

    @Test
    void run() throws Exception {
        // Junit3Test.clss의 Class 생성
        Class<Junit3Test> clazz = Junit3Test.class;
        // Class로 기본 생성자 생성
        Constructor<Junit3Test> constructor = clazz.getDeclaredConstructor();
        // 기본 생성자로 객제 생성
        Junit3Test junit3Test = constructor.newInstance();

        // Junit3Test에서 test로 시작하는 메소드 실행
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("test")) {
                method.invoke(junit3Test);
            }
        }
    }
}
