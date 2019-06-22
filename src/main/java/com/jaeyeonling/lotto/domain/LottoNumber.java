package com.jaeyeonling.lotto.domain;

import com.jaeyeonling.lotto.config.Env;
import com.jaeyeonling.lotto.exception.LottoNumberLongerThanMaxException;
import com.jaeyeonling.lotto.exception.LottoNumberShorterThanMinException;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    LottoNumber(final int number) {
        if (number < Env.MIN_LOTTO_NUMBER) {
            throw new LottoNumberShorterThanMinException(number);
        }
        if (number > Env.MAX_LOTTO_NUMBER) {
            throw new LottoNumberLongerThanMaxException(number);
        }

        this.number = number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }

        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}