package aspect;

import aspect.exception.WrapperAspect;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {WrapperAspect.class})
public class WrapExceptionAspectTest {

}
