package com.jaeyeonling.lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListUtilsTest {

    @DisplayName("리스트 합치기 테스트")
    @Test
    void should_return_concatList_when_concat() {
        final List<String> list1 = List.of("Hello");
        final List<String> list2 = List.of("World");

        final List<String> joinList = ListUtils.concat(list1, list2);

        assertThat(joinList).containsAll(list1);
        assertThat(joinList).containsAll(list2);
    }
}