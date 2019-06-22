package com.jaeyeonling.lotto.domain;

import com.jaeyeonling.lotto.config.Env;
import com.jaeyeonling.lotto.exception.InvalidCountOfLottoNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @DisplayName("Lotto 생성 시 LottoNumber " + Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO  + "개 가지게 생성")
    @Test
    void should_create_lotto_when_correct_lottoNumber() {
        // given
        final Set<LottoNumber> lottoNumbers = new HashSet<>();

        // when
        for (int lottoNumber = 1; lottoNumber <= Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO; lottoNumber++) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }

        // then
        new Lotto(lottoNumbers);
    }

    @DisplayName("Lotto 생성 시 LottoNumber " + Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO  + "개 보다 적을 시 예외처리")
    @Test
    void should_throw_InvalidCountOfLottoNumberException_lotto_when_shorterThan_countOfLotto() {
        // given
        final Set<LottoNumber> lottoNumbers = new HashSet<>();

        // when
        for (int lottoNumber = 1; lottoNumber <= Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO - 1; lottoNumber++) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }

        // then
        Assertions.assertThatExceptionOfType(InvalidCountOfLottoNumberException.class)
                .isThrownBy(() -> {
                    new Lotto(lottoNumbers);
                });
    }

    @DisplayName("Lotto 생성 시 LottoNumber " + Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO  + "개 보다 클 시 예외처리")
    @Test
    void should_throw_InvalidCountOfLottoNumberException_lotto_when_longerThan_countOfLotto() {
        // given
        final Set<LottoNumber> lottoNumbers = new HashSet<>();

        // when
        for (int lottoNumber = 1; lottoNumber <= Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO + 1; lottoNumber++) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }

        // then
        Assertions.assertThatExceptionOfType(InvalidCountOfLottoNumberException.class)
                .isThrownBy(() -> {
                    new Lotto(lottoNumbers);
                });
    }

    @DisplayName("Lotto 가 같은 값을 가질 경우 같은 객체 확인")
    @Test
    void should_return_true_when_equals_sameLotto() {
        // given
        final Set<LottoNumber> lottoNumbers = new HashSet<>();

        for (int lottoNumber = 1; lottoNumber <= Env.COUNT_OF_LOTTO_NUMBER_IN_LOTTO; lottoNumber++) {
            lottoNumbers.add(new LottoNumber(lottoNumber));
        }

        // when
        final Lotto target = new Lotto(lottoNumbers);
        final Lotto expect = new Lotto(lottoNumbers);

        // then
        assertThat(target).isEqualTo(expect);
    }


}