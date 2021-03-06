package com.jaeyeonling.lotto.view;

import com.jaeyeonling.lotto.domain.Lotto;
import com.jaeyeonling.lotto.domain.LottoGameReport;
import com.jaeyeonling.lotto.domain.LottoPrize;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public final class ConsoleOutputView {

    private static final String BUYING_ALERT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String WINNING_REPORT_HEADER = "당첨 통계\n---------";
    private static final String WINNING_REPORT_MATCH_TEMPLATE = "%d개 일치 (%d원) - %d개";
    private static final String WINNING_REPORT_BONUS_TEMPLATE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String WINNING_REPORT_FOOTER = "총 수익률은 %f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private static final PrintStream CONSOLE = System.out;

    private ConsoleOutputView() { }

    public static void printBuyingLotto(final int manualLottoSize,
                                        final List<Lotto> lottos) {
        ConsoleOutputView.newline();
        print(String.format(BUYING_ALERT_MESSAGE, manualLottoSize, lottos.size() - manualLottoSize));
        lottos.forEach(ConsoleOutputView::print);
    }

    public static void printReport(final LottoGameReport report) {
        ConsoleOutputView.newline();
        printWinningReportHeader();
        printWinningReportMatchTemplate(report);
        printWinningReportFooter(report);
    }

    public static void print(final Object message) {
        CONSOLE.println(message);
    }

    public static void newline() {
        CONSOLE.println();
    }

    private static void printWinningReportHeader() {
        ConsoleOutputView.print(WINNING_REPORT_HEADER);
    }

    private static void printWinningReportMatchTemplate(final LottoGameReport report) {
        Arrays.stream(LottoPrize.values())
                .forEach(prize -> printWinningReportMatchTemplate(prize, report));
    }

    private static void printWinningReportMatchTemplate(final LottoPrize prize,
                                                        final LottoGameReport report) {
        final String message = String.format(getWinningReportTemplate(prize),
                prize.getCountOfMatch(), prize.getPrizeMoney(), report.getMatchCountByPrize(prize));

        ConsoleOutputView.print(message);
    }

    private static String getWinningReportTemplate(final LottoPrize prize) {
        return prize.isMatchBonus() ? WINNING_REPORT_BONUS_TEMPLATE : WINNING_REPORT_MATCH_TEMPLATE;
    }

    private static void printWinningReportFooter(final LottoGameReport report) {
        ConsoleOutputView.print(String.format(WINNING_REPORT_FOOTER, report.getReturnOnInvestment()));
    }
}
