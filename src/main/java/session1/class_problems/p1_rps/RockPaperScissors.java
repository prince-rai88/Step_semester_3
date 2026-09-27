package session1.class_problems.p1_rps;

import java.util.Random;

public class RockPaperScissors {
    public String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        String p = playerMove.toLowerCase();
        String c = computerMove.toLowerCase();
        if ((p.equals("rock") && c.equals("scissors")) ||
            (p.equals("scissors") && c.equals("paper")) ||
            (p.equals("paper") && c.equals("rock"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }
    
    public void simulateGame() {
        String[] options = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        Random random = new Random();
        
        int wins = 0, losses = 0, draws = 0;
        
        for (int i = 0; i < 5; i++) {
            String cMove = options[random.nextInt(3)];
            String pMove = playerMoves[i];
            String result = playRound(pMove, cMove);
            System.out.printf("Round %d — Player: %s, Computer: %s | %s%n", (i+1), pMove, cMove, result);
            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;
        }
        
        double winPercent = (wins / 5.0) * 100;
        System.out.printf("Final Summary (after 5 rounds) Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercent);
    }
}
