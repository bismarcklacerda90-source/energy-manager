package service;

import java.util.List;
import model.Equipamento;

public class SistemaEnergia {
    private int energiaTotal;
    private List<Equipamento> equipamentos;

    public SistemaEnergia(int energiaTotal, List<Equipamento> equipamentos) {
        this.energiaTotal = energiaTotal;
        this.equipamentos = equipamentos;
    }

    public int consumoAtual() {
        int total = 0;
        for (Equipamento e : equipamentos) {
            total += e.getConsumo();
        }
        return total;
    }

    public boolean podeLigar(Equipamento e) {
        return consumoAtual() + e.getConsumo() <= energiaTotal;
    }

    public int getEnergiaTotal() {
        return energiaTotal;
    }
}
