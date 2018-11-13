package game.lotto.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LottoRegistry {

    private final List<Lotto> lottos;

    public LottoRegistry() {
        this.lottos = new ArrayList<>();
    }

    public LottoRegistry(Collection<Lotto> lottos) {
        this();
        regists(lottos);
    }

    public void regist(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public void regists(Collection<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public int size() {
        return this.lottos.size();
    }

    public Amount getAmount() {
        int autoCount = countAutoType();
        return new Amount(autoCount, this.lottos.size() - autoCount);
    }

    private int countAutoType() {
        return (int) this.lottos.stream().filter(Lotto::isAuto).count();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(this.lottos);
    }

}