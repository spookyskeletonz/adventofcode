package twentytwentyone.day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day21 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var p1Score = 0;
    var p2Score = 0;
    var inputP1Start = scanner.nextLine();
    var p1Pos = Integer.parseInt(inputP1Start.split(": ")[1]);
    var inputP2Start = scanner.nextLine();
    var p2Pos = Integer.parseInt(inputP2Start.split(": ")[1]);
    var die = 1;
    var dieRollCount = 0;
    while (true) {
      var p1Roll1 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p1Roll2 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p1Roll3 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p1Move = p1Roll1 + p1Roll2 + p1Roll3;
      p1Pos = moveP(p1Pos, p1Move);
      p1Score += p1Pos;
      if (p1Score >= 1000) {
        break;
      }
      var p2Roll1 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p2Roll2 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p2Roll3 = die;
      dieRollCount++;
      die = incrementDie(die);
      var p2Move = p2Roll1 + p2Roll2 + p2Roll3;
      p2Pos = moveP(p2Pos, p2Move);
      p2Score += p2Pos;
      if (p2Score >= 1000) {
        break;
      }
    }
    return (Math.min(p1Score, p2Score)) * dieRollCount;
  }

  private int moveP(int origPos, int move) {
    var pos = origPos + move;
    while (pos > 10) {
      pos = pos - 10;
    }
    return pos;
  }

  private int incrementDie(int die) {
    die++;
    if (die > 100) {
      die = die - 100;
    }
    return die;
  }

  public class Player {
    int score = 0;
    int pos;

    public Player(int pos) {
      this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Player player = (Player) o;
      return score == player.score && pos == player.pos;
    }

    @Override
    public int hashCode() {
      return Objects.hash(score, pos);
    }
  }

  public class Game {
    Player p1;
    Player p2;
    boolean playerOneTurn;

    public Game(Player p1, Player p2, boolean playerOneTurn) {
      this.p1 = p1;
      this.p2 = p2;
      this.playerOneTurn = playerOneTurn;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Game game = (Game) o;
      return playerOneTurn == game.playerOneTurn && p1.equals(game.p1) && p2.equals(game.p2);
    }

    @Override
    public int hashCode() {
      return Objects.hash(p1, p2, playerOneTurn);
    }
  }

  public class Score {
    long p1Wins;
    long p2Wins;

    public Score(long p1Score, long p2Score) {
      this.p1Wins = p1Score;
      this.p2Wins = p2Score;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Score score = (Score) o;
      return p1Wins == score.p1Wins && p2Wins == score.p2Wins;
    }

    @Override
    public int hashCode() {
      return Objects.hash(p1Wins, p2Wins);
    }
  }

  // Used to cache results from recursive game calls
  Map<Game, Score> dp;

  public long solvePartTwo(File file) throws FileNotFoundException {
    dp = new HashMap<>();
    var scanner = new Scanner(file);
    var inputP1Start = scanner.nextLine();
    var p1Pos = Integer.parseInt(inputP1Start.split(": ")[1]);
    var p1 = new Player(p1Pos);
    var inputP2Start = scanner.nextLine();
    var p2Pos = Integer.parseInt(inputP2Start.split(": ")[1]);
    var p2 = new Player(p2Pos);
    var scores = playGame(new Game(p1, p2, true));
    return Math.max(scores.p1Wins, scores.p2Wins);
  }

  private Score playGame(Game game) {
    if (game.p1.score >= 21) {
      return new Score(1, 0);
    }
    if (game.p2.score >= 21) {
      return new Score(0, 1);
    }
    if (dp.containsKey(game)) {
      return dp.get(game);
    }
    var sumP1Wins = 0L;
    var sumP2Wins = 0L;
    for (var diceRollSum = 3; diceRollSum <= 9; diceRollSum++) {
      Score score;
      if (game.playerOneTurn) {
        // Roll and get new score for p1
        var p1Pos = moveP(game.p1.pos, diceRollSum);
        var p1Score = game.p1.score + p1Pos;
        var newP1 = new Player(p1Pos);
        newP1.score = p1Score;
        var newP2 = new Player(game.p2.pos);
        newP2.score = game.p2.score;
        // Play new game with new P1
        var newGame = new Game(newP1, newP2, false);
        score = playGame(newGame);
      } else {
        // Roll and get new score for p2
        var p2Pos = moveP(game.p2.pos, diceRollSum);
        var p2Score = game.p2.score + p2Pos;
        var newP2 = new Player(p2Pos);
        newP2.score = p2Score;
        var newP1 = new Player(game.p1.pos);
        newP1.score = game.p1.score;
        // Play new game with new p2
        var newGame = new Game(newP1, newP2, true);
        score = playGame(newGame);
      }
      // Since each possible dice roll combo has a predictable distribution,
      // we can multiply the number of wins for this distribution and construct our win scores for this game
      sumP1Wins += score.p1Wins * diceDistribution(diceRollSum);
      sumP2Wins += score.p2Wins * diceDistribution(diceRollSum);
    }
    var scoreForGame = new Score(sumP1Wins, sumP2Wins);
    dp.put(game, scoreForGame);
    return scoreForGame;
  }

  // 3 rolls of a 3 sided dice have this distribution, to use as a multiplier
  private long diceDistribution(int num) {
    switch (num) {
      case 3: return 1;   // 111
      case 4: return 3;   // 112, 121, 211
      case 5: return 6;   // 113, 131, 311, 122, 212, 221
      case 6: return 7;   // 123, 132, 213, 231, 312, 321, 222
      case 7: return 6;   // 223, 232, 322, 133, 313, 331
      case 8: return 3;   // 233, 323, 332
      case 9: return 1;   // 333
    }
    throw new RuntimeException();
  }
}
