package com.jaeyeonling.lotto.domain;

import com.jaeyeonling.lotto.exception.LottoNumberLongerThanMaxException;
import com.jaeyeonling.lotto.exception.LottoNumberShorterThanMinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoNumberTest {

    @DisplayName("LottoNumber 최소 값(" + LottoNumber.MIN + ") 생성")
    @Test
    void should_return_lottoNumber_when_minLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(LottoNumber.MIN);
        assertThat(lottoNumber).isNotNull();
    }

    @DisplayName("LottoNumber 최대 값(" + LottoNumber.MAX + ") 생성")
    @Test
    void should_return_lottoNumber_when_maxLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(LottoNumber.MAX);
        assertThat(lottoNumber).isNotNull();
    }

    @DisplayName("LottoNumber 최소 값(" + LottoNumber.MIN + ") 보다 낮은 값 생성 시 예외처리")
    @Test
    void should_throw_LottoNumberShorterThanMinException_when_shorterThanMin() {
        assertThatExceptionOfType(LottoNumberShorterThanMinException.class)
                .isThrownBy(() -> {
                    new LottoNumber(LottoNumber.MIN - 1);
                });
    }

    @DisplayName("LottoNumber 최대 값(" + LottoNumber.MIN + ") 보다 높은 값 생성 시 예외처리")
    @Test
    void should_throw_LottoNumberLongerThanMaxException_when_longerThanMax() {
        assertThatExceptionOfType(LottoNumberLongerThanMaxException.class)
                .isThrownBy(() -> {
                    new LottoNumber(LottoNumber.MAX + 1);
                });
    }

    @DisplayName("LottoNumber 가 같은 값을 가질 경우 같은 객체 확인")
    @Test
    void should_return_true_when_equals_sameLottoNumber() {
        final LottoNumber target = new LottoNumber(LottoNumber.MAX);
        final LottoNumber expect = new LottoNumber(LottoNumber.MAX);

        assertThat(target).isEqualTo(expect);
    }
}
