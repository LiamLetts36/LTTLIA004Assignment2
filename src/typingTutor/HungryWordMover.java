package typingTutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover {
    private FallingWord myWord;
    private AtomicBoolean done;
    private AtomicBoolean pause;
    private Score score;
    CountDownLatch startLatch;

    HungryWordMover( FallingWord word,WordDictionary dict, Score score,
               CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
        this(word);
        this.startLatch = startLatch;
        this.score=score;
        this.done=d;
        this.pause=p;
    }


    //This will be responsible for creating the hungry word that will come across the screen
    public HungryWordMover(FallingWord word) {

    }
}
