package stockx;

import java.util.*;

class Data {
    int frequency;
    int key;
    Object value;

    Data(int key, Object value) {
        this.key = key;
        this.value = value;
        this.frequency = 0;
    }

    Data(int key) {
        this.key = key;
        this.frequency = 0;
    }

    public int getFrequency() {
        return frequency;
    }
}

class LFU {

    Map<Integer, Data> map;
    Queue<Data> queue;
    int capacity;

    LFU(int capacity) {
        this.map = new HashMap<>();
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Data::getFrequency));
        this.capacity = capacity;
    }

    // "a" --> new Integer(1)
    // "b" --> new Integer(2)
    // read a
    // "a" --> new Integer(7)
    public void write(int key, int value) {

        if (map.containsKey(key)) {
            Data data = map.get(key);
            data.frequency++;
            data.value = value;
            queue.offer(queue.poll());
        } else {
            Data data = new Data(key, value);
            data.frequency++;
            if (queue.size() >= capacity) {
                Data evictedData = queue.poll();
                map.remove(evictedData.key);
            }
            queue.offer(data);
            map.put(key, data);
        }



    /*Data data = map.getOrDefault(key, new Data(key, value));
    data.frequency++;
    data.value = value;
    if(queue.size() < capacity) {
    	queue.offer(data);
    	map.put(map, data);
    }else {
      Data evictedData = queue.poll();
      map.remove(evictedData.key);
      queue.offer(data);
    	map.put(map, data);
    }*/

    }

    public Optional<Object> read(int key) {
        Data data = map.get(key);
        if (data == null) return Optional.empty();
        data.frequency++;
        queue.offer(queue.poll());
        return Optional.of(data.value);

    }

    public static void main(String[] args) {
        LFU lfu = new LFU(2);
        lfu.write(1, 2);
        lfu.write(1, 9);
        lfu.write(2, 12);
        lfu.read(2);
        System.out.println(lfu.read(1));
        lfu.write(3, 88);
    }
}
