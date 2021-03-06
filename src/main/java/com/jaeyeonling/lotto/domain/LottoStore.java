package com.jaeyeonling.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public final class LottoStore {

    private static final LottoGenerator RANDOM_LOTTO_GENERATOR = new RandomLottoGenerator();

    private LottoStore() { }

    public static List<Lotto> buy(final Money money,
                                  final LottoTicket lottoTicket) {
        final LottoGenerator manualLottoGenerator = new ManualLottoGenerator(lottoTicket);

        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoTicket.lottoSize(); i++) {
            lottos.add(manualLottoGenerator.generate(money));
        }

        lottos.addAll(buyAutoByRemainingMoney(money));

        return lottos;
    }

    public static List<Lotto> buyAutoByRemainingMoney(final Money money) {
        final List<Lotto> lottos = new ArrayList<>();
        while (money.canBuy(Lotto.PRICE)) {
            lottos.add(RANDOM_LOTTO_GENERATOR.generate(money));
        }

        return lottos;
    }
}
