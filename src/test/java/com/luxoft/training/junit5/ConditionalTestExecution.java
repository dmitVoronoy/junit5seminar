package com.luxoft.training.junit5;

import org.junit.jupiter.api.condition.*;

@EnabledOnOs(OS.MAC)
@DisabledOnOs(OS.WINDOWS)
@EnabledOnJre(JRE.JAVA_8)
@EnabledForJreRange(min = JRE.JAVA_11)
@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
@EnabledIfEnvironmentVariable(named = "ENV", matches = "integration")
@EnabledIf("methodName")
//@EnabledIf("com.luxoft.training.junit5.ExternalCondition.methodName")
public class ConditionalTestExecution {
}
