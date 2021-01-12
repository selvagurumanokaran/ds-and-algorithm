package tictoctoe;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Player, Object> hashMap = new HashMap<>();
        hashMap.put(new Player(1), new Object());
        hashMap.put(new Player(17), new Object());
        hashMap.put(new Player(33), new Object());
        hashMap.put(new Player(33), new Object());
    }
}
