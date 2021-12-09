package com.luxoft.training.junit5;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplateTest {
    private static TestTemplateInvocationContext urlShortenerInvocationContext(UrlShortenerTestContext testContext) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return testContext.displayName();
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return asList(new ParameterResolver() {
                                  @Override
                                  public boolean supportsParameter(ParameterContext parameterContext,
                                                                   ExtensionContext extensionContext)
                                          throws ParameterResolutionException {
                                      return true;
                                  }

                                  @Override
                                  public Object resolveParameter(ParameterContext parameterContext,
                                                                 ExtensionContext extensionContext)
                                          throws ParameterResolutionException {
                                      return testContext;
                                  }
                              },
                        (BeforeEachCallback) context -> System.out.println("Custom before each implementation"),
                        (AfterEachCallback) context -> System.out.println("Custom after each implementation"));
            }
        };
    }

    @TestTemplate
    @ExtendWith(UrlShortenerTestInvocationContextProvider.class)
    void urlShortenerTest(UrlShortenerTestContext testContext) {
        UrlShortener shortener = new UrlShortener(testContext.base(), testContext.length());
        String shortUrl = shortener.generate("https://url.com");
        assertEquals(testContext.expectedResult(), shortUrl);
    }

    static class UrlShortener {
        private final int length;
        private final String base;

        private final Encoder encoder = Base64.getEncoder();

        UrlShortener(String base, int length) {
            this.length = length;
            this.base = base;
        }

        String generate(String url) {
            return base
                    + encoder
                    .encodeToString(url.getBytes())
                    .substring(0, length);
        }
    }

    static class UrlShortenerTestInvocationContextProvider implements TestTemplateInvocationContextProvider {

        @Override
        public boolean supportsTestTemplate(ExtensionContext context) {
            return true;
        }

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return Stream.of(urlShortenerInvocationContext(new UrlShortenerTestContext(
                            "Tests the url shortener with 'https://by.tt' base and 3 symbols length",
                            "https://by.tt/",
                            3,
                            "https://by.tt/aHR")),
                    urlShortenerInvocationContext(new UrlShortenerTestContext(
                            "Tests the url shortener with 'https://mc.by' base and 4 symbols length",
                            "https://mc.by/",
                            4,
                            "https://mc.by/aHR0"))
            );
        }

    }

    private static class UrlShortenerTestContext {
        private final int length;
        private final String base;
        private final String displayName;
        private final String expectedResult;

        private UrlShortenerTestContext(String displayName, String base, int length, String expectedResult) {
            this.displayName = displayName;
            this.base = base;
            this.length = length;
            this.expectedResult = expectedResult;
        }

        public int length() {
            return length;
        }

        public String base() {
            return base;
        }

        public String displayName() {
            return displayName;
        }

        public String expectedResult() {
            return expectedResult;
        }
    }
}
