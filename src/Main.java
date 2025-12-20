import java.util.*;
import model.Equipamento;
import service.SistemaEnergia;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // Equipamentos
        Equipamento servidor = new Equipamento("Servidor", 30);
        Equipamento roteador = new Equipamento("Roteador", 10);
        Equipamento camera = new Equipamento("Câmera", 15);
        Equipamento nobreak = new Equipamento("NoBreak", 20);

        List<Equipamento> equipamentos = Arrays.asList(
            servidor, roteador, camera, nobreak
        );

SistemaEnergia sistema = new SistemaEnergia(50, equipamentos);
        int turno = 1;
        final int TURNO_MAX = 10;
        int pontos = 0;

        while (true) {

            System.out.println("\n=== ENERGY MANAGER ===");
            System.out.println("Turno: " + turno + "/" + TURNO_MAX);
            System.out.println("Pontuação: " + pontos);
            System.out.println("Energia total: " + sistema.getEnergiaTotal());
            System.out.println("Consumo atual: " + sistema.consumoAtual());

            // Derrota
            if (sistema.consumoAtual() > sistema.getEnergiaTotal()) {
                System.out.println("💥 ENERGIA ESTOURADA! GAME OVER");
                System.out.println("Pontuação final: " + pontos);
                break;
            }

            // Vitória
            if (turno > TURNO_MAX) {
                System.out.println("🏆 PARABÉNS! VOCÊ VENCEU!");
                System.out.println("Pontuação final: " + (pontos + 100));
                break;
            }

            // Mostrar equipamentos
            for (int i = 0; i < equipamentos.size(); i++) {
                Equipamento e = equipamentos.get(i);
                System.out.println(
                    (i + 1) + " - " + e.getNome() +
                    (e.isLigado() ? " (Ligado)" : " (Desligado)") +
                    " | Consumo: " + e.getConsumo()
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

            // Avançar turno
            if (op == 0) {

                // Falha aleatória (30%)
                if (random.nextInt(100) < 30) {
                    Equipamento falhou = equipamentos.get(
                        random.nextInt(equipamentos.size())
                    );
                    falhou.desligar();
                    System.out.println("⚠️ FALHA! " + falhou.getNome() + " desligou!");
                }

                pontos += 10;
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
                System.out.println(escolhido.getNome() + " LIGADO");
            } else {
                escolhido.desligar();
                System.out.println(escolhido.getNome() + " DESLIGADO");
            }
        }

        sc.close();
    }
}
