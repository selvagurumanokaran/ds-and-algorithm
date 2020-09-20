import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefenderArcade {
    public int countArcades(List<String> times) {

        class Game {
            int startTime;
            int endTime;

            public Game(int startTime, int endTime) {
                this.startTime = startTime;
                this.endTime = endTime;
            }

            public int getStartTime() {
                return startTime;
            }

            public int getEndTime() {
                return endTime;
            }
        }

        List<Game> games = times.stream()
                .map(time -> time.split(" "))
                .map(timeArr -> new int[]{Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1])})
                .map(timeArr -> new Game(timeArr[0], timeArr[1]))
                .sorted(Comparator.comparingInt(Game::getEndTime)).collect(Collectors.toList());
        Game currGame = games.stream().min(Comparator.comparingInt(Game::getStartTime)).get();
        games.remove(currGame);
        int currCnt = 1;
        List<Integer> arcades = new ArrayList<>();
        for (Game game : games) {
            if (game.startTime <= currGame.endTime) {
                currCnt++;
            } else {
                currGame = game;
                arcades.add(currCnt);
                currCnt = 1;
            }
        }
        return arcades.stream().max(Comparator.comparingInt(arcade -> arcade)).get();
    }
}
