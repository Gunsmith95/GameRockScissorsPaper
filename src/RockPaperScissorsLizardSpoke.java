import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsLizardSpoke {
	private RockPaperScissorsLizardSpoke.User user = new RockPaperScissorsLizardSpoke.User();
	private RockPaperScissorsLizardSpoke.Computer computer = new RockPaperScissorsLizardSpoke.Computer();
	private int userScore = 0;
	private int computerScore = 0;
	private int numberOfGames = 0;

	public void starGames() {
		System.out.println("КАМЕНЬ, НОЖНИЦЫ, БУМАГА, ЯЩЕРИЦА, СПОК!");
		RockPaperScissorsLizardSpoke.Move userMove = this.user.getMove();
		RockPaperScissorsLizardSpoke.Move computerMove = this.computer.getMove();
		System.out.println("\nВаш ход " + userMove + ".");
		System.out.println("Ход компьютера " + computerMove + ".\n");
		int compareMove = userMove.compareMove(computerMove);
		switch (compareMove) {
			case - 1:
				System.out.println(computerMove + " beats " + userMove + ". Вы проиграли.");
				++ this.computerScore;
				break;
			case 0:
				System.out.println("Ничья!");
				break;
			case 1:
				System.out.println(userMove + " beats " + computerMove + ". Вы победили!");
				++ this.userScore;
		}

		++ this.numberOfGames;
		if (this.user.playAgain()) {
			System.out.println();
			this.starGames();
		} else {
			this.printGameStats();
		}

	}

	private void printGameStats() {
		int wins = this.userScore;
		int losses = this.computerScore;
		int ties = this.numberOfGames - this.userScore - this.computerScore;
		double percentageWin = ((double) wins + (double) ties / 2.0D) / (double) this.numberOfGames;
		System.out.print("+");
		this.printDashes(71);
		System.out.println("+");
		System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n", "ПОБЕДА", "ПОРАЖЕНИЕ", "НИЧЬЯ", "ВСЕГО ИГР", "ПРОЦЕНТ ПОБЕД");
		System.out.print("|");
		this.printDashes(10);
		System.out.print("+");
		this.printDashes(10);
		System.out.print("+");
		this.printDashes(10);
		System.out.print("+");
		this.printDashes(16);
		System.out.print("+");
		this.printDashes(21);
		System.out.println("|");
		System.out.printf("|  %6d  |  %6d     |  %6d  |  %12d  |  %13.2f%%  |\n", wins, losses, ties, this.numberOfGames, percentageWin * 100.0D);
		System.out.print("+");
		this.printDashes(71);
		System.out.println("+");
	}

	private void printDashes(int numberOfDashes) {
		for (int i = 0; i < numberOfDashes; ++ i) {
			System.out.print("-");
		}

	}

	public RockPaperScissorsLizardSpoke() {
	}

	public static void main(String[] args) {
		RockPaperScissorsLizardSpoke game = new RockPaperScissorsLizardSpoke();
		game.starGames();
	}

	private class Computer {
		private Computer() {
		}

		public RockPaperScissorsLizardSpoke.Move getMove() {
			RockPaperScissorsLizardSpoke.Move[] moves = RockPaperScissorsLizardSpoke.Move.values();
			Random random = new Random();
			int index = random.nextInt(moves.length);
			return moves[index];
		}
	}

	private class User {
		private Scanner inputScaner;

		public User() {
			this.inputScaner = new Scanner(System.in);
		}

		public RockPaperScissorsLizardSpoke.Move getMove() {
			System.out.println("Выберите: 1 - камень, 2 - ножницы, 3 - бумага, 4 - ящерица,  5 - спок");
			String userInput = this.inputScaner.nextLine();
			char firstLetter = userInput.charAt(0);
			if (firstLetter == '1' || firstLetter == '2' || firstLetter == '3' || firstLetter == '4' || firstLetter == '5') {
				switch (firstLetter) {
					case '1':
						System.out.println("Вы выбрали камень");
						return RockPaperScissorsLizardSpoke.Move.ROCK;
					case '2':
						System.out.println("Вы выбрали ножницы");
						return RockPaperScissorsLizardSpoke.Move.SCISSORS;
					case '3':
						System.out.println("Вы выбрали бумага");
						return RockPaperScissorsLizardSpoke.Move.PAPER;
					case '4':
						System.out.println("Вы выбрали ящерица");
						return RockPaperScissorsLizardSpoke.Move.LIZARD;
					case '5':
						System.out.println("Вы выбрали спок");
						return RockPaperScissorsLizardSpoke.Move.SPOKE;
				}
			}

			System.out.println("Ввод неверный, повторите попытку ");
			return this.getMove();
		}

		public boolean playAgain() {
			System.out.println("Ходите сыграть ещё раз?");
			String userInput = this.inputScaner.nextLine();
			userInput = userInput.toUpperCase();
			return userInput.charAt(0) == 1044;
		}
	}

	private static enum Move {
		ROCK,
		PAPER,
		SCISSORS,
		LIZARD,
		SPOKE;

		private Move() {
		}

		private int compareMove(RockPaperScissorsLizardSpoke.Move otherMove) {
			if (this == otherMove) {
				return 0;
			} else {
				switch (this) {
					case ROCK:
						if (otherMove != SCISSORS && otherMove != LIZARD) {
							return - 1;
						}

						return 1;
					case PAPER:
						if (otherMove != ROCK && otherMove != SPOKE) {
							return - 1;
						}

						return 1;
					case SCISSORS:
						if (otherMove != PAPER && otherMove != LIZARD) {
							return - 1;
						}

						return 1;
					case LIZARD:
						if (otherMove != SPOKE && otherMove != PAPER) {
							return - 1;
						}

						return 1;
					case SPOKE:
						if (otherMove != ROCK && otherMove != SCISSORS) {
							return - 1;
						}

						return 1;
					default:
						return 0;
				}
			}
		}
	}
}