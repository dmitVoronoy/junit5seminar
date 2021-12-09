package com.luxoft.training.junit5;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

public class ParameterizedExecutionTest {
    static Stream<String> stringStream() {
        return Stream.of("first", "second");
    }

    static Stream<Arguments> multipleParametersStream() {
        return Stream.of(
                arguments("firstString", 1),
                arguments("secondString", 2)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6})
    void intValueSource(int arg) {
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"text1", "text2"})
    void stringValueSource(String arg) {
    }

    @ParameterizedTest
    @EnumSource
    void enumSource(DayOfWeek arg) {
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = {"MONDAY", "TUESDAY"})
    void enumSourceExclude(DayOfWeek arg) {
    }

    @ParameterizedTest
    @MethodSource("stringStream")
    void methodSource(String arg) {
    }

    @ParameterizedTest
    @MethodSource("multipleParametersStream")
    void methodSourceMultiple(String string, int number) {
    }

    @ParameterizedTest
    @CsvSource({
            "cat, 1",
            "dog, 2",
            "mouse, 3"
    })
    void csvSource(String pet, int number) {
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
                cat, 1
                dog, 2
                mouse, 3
            """)
    void csvSourceTextBlock(String pet, int number) {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/parameterizedTest.csv", delimiter = '|')
    void csvSourceFile(String pet, int number) {
    }

    @ParameterizedTest
    @ArgumentsSource(CustomArgumentProvider.class)
    void argumentProviderTest(String string) {
    }

    @ParameterizedTest(name = "Called with {0}")
    @CsvSource(textBlock = """
                cat, 1
                dog, 2
                mouse, 3    
            """)
    void csvSourceAggregated(@AggregateWith(CustomAggregator.class) Pet pet) {
        assertTrue(pet.id < 4);
    }

    static class CustomArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            // provide arguments from any source
            return Stream.of("1", "2").map(Arguments::arguments);
        }
    }

    static class CustomAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
                throws ArgumentsAggregationException {
            return new Pet(accessor.getString(0), accessor.getInteger(1));
        }
    }

    static class Pet {
        final String kind;
        final int id;

        Pet(String kind, int id) {
            this.kind = kind;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Pet{" +
                    "kind='" + kind + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
