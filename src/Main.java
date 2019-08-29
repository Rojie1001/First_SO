import java.util.Calendar;
import java.util.Scanner;

public class Main {

	static int hora = 0;
	static int minuto = 0;
	static int segundo = 0;

	static int horaC;
	static int minutoC;
	static int segundoC;

	static int auxMinutos;
	static int auxHoras;
	static int auxSegundos;

	static int horaA;
	static int minutoA;
	static int segundoA;

	static boolean entrou = false;

	public static int i = 0;

	public static void main(String[] args) throws InterruptedException {

		Relogio re = new Relogio();
		Thread relogio = new Thread(re);
		relogio.start();

		Cronometro cr = new Cronometro();
		Thread cronometro = new Thread(cr);

		Alarme al = new Alarme();
		Thread alarme = new Thread(al);

		while (true) {

			System.out.println("(1)Ajustar horário");
			System.out.println("(2)Visualizar horário");
			System.out.println("(3)Iniciar cronômetro");
			System.out.println("(4)Parar cronômetro");
			System.out.println("(5)Zerar cronômetro");
			System.out.println("(6)Definir alarme");
			System.out.println("(7)Sair");

			Scanner teclado = new Scanner(System.in);
			i = teclado.nextInt();

			switch (i) {
			case 1:
				System.out.println("Digite a Hora");
				hora = teclado.nextInt();
				minuto = teclado.nextInt();
				segundo = teclado.nextInt();

				break;

			case 2:
				System.out.println("Hora Atual " + hora + ":" + minuto + ":" + segundo);

				break;

			case 3:

				minutoC = auxMinutos;
				horaC = auxHoras;
				segundoC = auxSegundos;

				if (!cronometro.isAlive()) {
					cronometro = new Thread(cr);
					cronometro.start();

				} else {
					cronometro.start();
				}

				break;
			case 4:

				auxMinutos = minutoC;
				auxHoras = horaC;
				auxSegundos = segundoC;

				System.out.println(auxHoras + ":" + auxMinutos + ":" + auxSegundos);
				cronometro.interrupt();

				break;
			case 5:

				horaC = 0;
				minutoC = 0;
				segundoC = 0;
				auxHoras = 0;
				auxMinutos = 0;
				auxSegundos = 0;

				cronometro.interrupt();

				break;

			case 6:

				System.out.println("Digite a Hora");
				horaA = teclado.nextInt();
				System.out.println("Digite os Minutos");
				minutoA = teclado.nextInt();

				if (!alarme.isAlive()) {
					alarme = new Thread(al);
					alarme.start();
				} else {
					alarme.start();
				}

				break;

			default:
				break;
			}

			if (i == 7)
				break;
		}
	}

	public static class Relogio implements Runnable {
		public void run() {

			while (true) {
				for (segundo = 0; segundo < 60; segundo++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				minuto++;
				if (minuto == 60) {
					minuto = 0;
					hora++;
				}
				if (hora == 24)
					hora = 0;
			}
		}
	}

	public static class Cronometro implements Runnable {
		public void run() {
			try {
				int cont = 0;
				while (true) {
					for (cont = 0; cont < 60; cont++) {

						Thread.sleep(1000);
						segundoC++;
					}

					segundoC = 0;
					minutoC++;
					if (minutoC == 60) {
						minutoC = 0;
						horaC++;
					}
					if (hora == 24) {
						hora = 0;
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static class Alarme implements Runnable {
		public void run() {
			int horas = 0;
			int minutos = 0;

			while (true) {

				Calendar calendar = Calendar.getInstance();
				horas = calendar.get(Calendar.HOUR);
				minutos = calendar.get(Calendar.MINUTE);

				if (horaA == horas && minutos == minutoA) {
					System.out.println(" Alarme ");

					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}
}