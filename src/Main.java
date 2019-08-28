import java.util.Scanner;

public class Main {

	static int hora = 0;
	static int minuto = 0;
	static int segundos = 0;

	static int horaC;
	static int minutoC;
	static int segundoC;

	static int horaA;
	static int minutoA;
	static int segundoA;

	public static int i = 0;

	public static void main(String[] args) throws InterruptedException {

		Relogio re = new Relogio();
		Thread relogio = new Thread(re);
		relogio.start();

		Cronometro cr = new Cronometro();
		Thread cronometro = new Thread(cr);

		while (true) {

			System.out.println("(1)Ajustar hor�rio");
			System.out.println("(2)Visualizar hor�rio");
			System.out.println("(3)Iniciar cron�metro");
			System.out.println("(4)Parar cron�metro");
			System.out.println("(5)Zerar cron�metro");
			System.out.println("(6)Definir alarme");
			System.out.println("(7)Sair");

			Scanner teclado = new Scanner(System.in);
			i = teclado.nextInt();

			switch (i) {
			case 1:
				System.out.println("Digite a Hora");
				hora = teclado.nextInt();
				minuto = teclado.nextInt();
				segundos = teclado.nextInt();

				break;
			case 2:
				System.out.println("Hora Atual " + hora + ":" + minuto + ":" + segundos);

				break;

			case 3:
				cronometro.start();

			case 4:
//				System.out.println("hh");
//				cronometro.wait();

				break;
			case 5:
				horaC = 0;
				minutoC = 0;
				segundoC = 0;

				break;
			case 6:
				System.out.println(horaC + ":" + minutoC + ":" + segundoC);
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

			int cont = 0;
			while (true) {
				for (segundos = 0; segundos <= 60; segundos++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				minuto++;
				if (minuto == 60)
					minuto = 0;
				if (minuto == 60)
					hora++;

			}

		}
	}

	public static class Cronometro implements Runnable {
		public void run() {

			int cont = 0;
			while (true) {
				for (segundoC = 0; segundoC <= 60; segundoC++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				minutoC++;
				if (minutoC == 60)
					minutoC = 0;
				if (minutoC == 60)
					horaC++;

			}

		}
	}
//	public static class Alarme implements Runnable {
//		public void run() {
//		while
//
//		}
//	}

}
