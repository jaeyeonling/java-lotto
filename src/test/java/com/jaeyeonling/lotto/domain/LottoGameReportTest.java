package com.jaeyeonling.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameReportTest {

    @DisplayName("상금 별 당첨 횟수 반환")
    @ParameterizedTest
    @ValueSource(ints = {
            1,
            2,
            100,
            5000
    })
    void should_return_matchCount(final int matchCount) {
        // given
        final LottoPrize prize = LottoPrize.JACKPOT;
        final LottoGameReport lottoGameReport = generateLottoGameReport(matchCount, prize);

        // when
        final int target = lottoGameReport.getMatchCountByPrize(prize);

        // then
        assertThat(target).isEqualTo(matchCount);
    }

    @DisplayName("당첨 별 상금 반환")
    @ParameterizedTest
    @ValueSource(ints = {
            1,
            2,
            100,
            5000
    })
    void should_return_totalPrizeMoney(final int matchCount) {
        // given
        final LottoPrize prize = LottoPrize.JACKPOT;
        final LottoGameReport lottoGameReport = generateLottoGameReport(matchCount, prize);

        // when
        final Money totalPrizeMoney = lottoGameReport.getTotalPrizeMoney();
        final Money expect = new Money(prize.getPrizeMoney() * matchCount);

        // then
        assertThat(totalPrizeMoney).isEqualTo(expect);
    }

    @DisplayName("수익률 계산")
    @ParameterizedTest
    @ValueSource(ints = {
            1,
            2,
            100,
            5000
    })
    void should_return_returnOnInvestment(final int matchCount) {
        // given
        final LottoPrize prize = LottoPrize.FOURTH;
        final LottoGameReport lottoGameReport = generateLottoGameReport(matchCount, prize);

        final int totalBuyingMoney = matchCount * Lotto.PRICE_VALUE;

        // when
        final double returnOnInvestment = lottoGameReport.getReturnOnInvestment();
        final double expect = prize.getPrizeMoney() * matchCount / totalBuyingMoney;

        // then
        assertThat(returnOnInvestment).isEqualTo(expect);
    }

    private LottoGameReport generateLottoGameReport(final int matchCount, final LottoPrize prize) {
        final Map<LottoPrize, Integer> matchCountByRank = new HashMap<>();
        matchCountByRank.put(prize, matchCount);

        return new LottoGameReport(matchCountByRank);
    }
}
