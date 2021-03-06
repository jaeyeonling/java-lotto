package com.jaeyeonling;

import com.jaeyeonling.lotto.domain.Lotto;
import com.jaeyeonling.lotto.domain.LottoAnalyzer;
import com.jaeyeonling.lotto.domain.LottoGameReport;
import com.jaeyeonling.lotto.domain.LottoNumber;
import com.jaeyeonling.lotto.domain.LottoStore;
import com.jaeyeonling.lotto.domain.LottoTicket;
import com.jaeyeonling.lotto.domain.Money;
import com.jaeyeonling.lotto.domain.WinningLotto;
import com.jaeyeonling.lotto.view.ConsoleInputView;
import com.jaeyeonling.lotto.view.ConsoleOutputView;

import java.util.List;

public class Application {

    public static void main(final String... args) {
        final Money money = ConsoleInputView.readMoney();
        final LottoTicket lottoTicket = ConsoleInputView.readLottoTicket();

        final List<Lotto> lottos = LottoStore.buy(money, lottoTicket);
        ConsoleOutputView.printBuyingLotto(lottoTicket.lottoSize(), lottos);

        final Lotto inputWinningLotto = ConsoleInputView.readWinningLotto();
        final LottoNumber inputBonusLottoNumber = ConsoleInputView.readBonusLottoNumber();

        final WinningLotto winningLotto = new WinningLotto(inputWinningLotto, inputBonusLottoNumber);

        final LottoAnalyzer analyzer = new LottoAnalyzer(winningLotto);

        final LottoGameReport report = analyzer.analyze(lottos);
         ConsoleOutputView.printReport(report);
    }
}
