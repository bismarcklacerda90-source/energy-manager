import java.util.*;
import model.Equipamento;
import service.SistemaEnergia;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Equipamento servidor = new Equipamento("Servidor", 30);
        Equipamento roteador = new Equipamento("Roteador", 10);

        List<Equipamento> equipamentos = Arrays.asList(servidor, roteador);
        SistemaEnergia sistema = new SistemaEnergia(50, equipamentos);

        int turno = 1;
        final int TURNO_MAX = 10;

        while (true) {
            System.out.println("\n=== ENERGY MANAGER ===");
            System.out.println("Turno: " + turno + "/" + TURNO_MAX);
            System.out.println("Energia total: " + sistema.getEnergiaTotal());
            System.out.println("Consumo atual: " + sistema.consumoAtual());

            // Condição de derrota
            if (sistema.consumoAtual() > sistema.getEnergiaTotal()) {
                System.out.println("💥 ENERGIA ESTOURADA! GAME OVER");
                break;
            }

            // Condição de vitória
            if (turno > TURNO_MAX) {
                System.out.println("🏆 PARABÉNS! VOCÊ VENCEU!");
                break;
            }

            // Mostrar equipamentos
            for (int i = 0; i < equipamentos.size(); i++) {
                Equipamento e = equipamentos.get(i);
                System.out.println(
                    (i + 1) + " - " + e.getNome() +
                    (e.isLigado() ? " (Ligado)" : " (Desligado)")
                );
            }

            System.out.println("0 - Avançar turno");
            int op;

            try {
                op = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Digite apenas números!");
                sc.next();
                continue;
            }

            if (op == 0) {
                // Falha aleatória
                if (random.nextInt(100) < 30) { // 30% de chance
                    Equipamento falhou = equipamentos.get(
                        random.nextInt(equipamentos.size())
                    );
                    falhou.desligar();
                    System.out.println("⚠️ FALHA! " + falhou.getNome() + " desligou!");
                }

                turno++;
                continue;
            }

            if (op < 1 || op > equipamentos.size()) {
                System.out.println("Opção inválida");
                continue;
            }

            Equipamento escolhido = equipamentos.get(op - 1);

            if (!escolhido.isLigado() && sistema.podeLigar(escolhido)) {
                escolhido.ligar();
                System.out.println(escolhido.getNome() + " ligado.");
            } else {
                escolhido.desligar();
                System.out.println(escolhido.getNome() + " desligado.");
            }
        }

        sc.close();
    }
}
