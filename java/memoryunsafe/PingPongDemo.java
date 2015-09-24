import java.nio.channels.FileChannel.MapMode;
import java.nio.MappedByteBuffer;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.io.File;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

public class PingPongDemo {

    public static final void main(final String[] args) {

        boolean odd;

        switch (args.length < 1? "usage" : args[0].toLowerCase()) {
        case "odd": {
            odd = true;
            break;
        }
        case "even": {
            odd = false;
            break;
        }
        default: {
            System.err.println("Usage: java PingPongDemo [odd|even]");
            return;
        }
        }

        int runs = 10000000;
        long start = 0;
        System.out.println("Waiting for the odd/even");

        System.out.println("java io tmpdir = " + System.getProperty("java.io.tmpdir"));
        File counters = new File(System.getProperty("java.io.tmpdir"), "counters.deleteme");
        counters.deleteOnExit();

        try (FileChannel fc = new RandomAccessFile(counters, "rw").getChannel()) {
            System.out.println("try start");
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
            long address = ((DirectBuffer) mbb).address();  // 能获取非堆内存或是DirectMemory，但不是线程安全
            for (int i=-1; i<runs; i++) {
                for (;;) {
                    long value = UNSAFE.getLongVolatile(null, address);
                    boolean isOdd = (value & 1) != 0;
                    if (isOdd != odd) {
                        //System.out.println("odd continue");
                        continue;
                    }
                    if (UNSAFE.compareAndSwapLong(null, address, value, value + 1)) {
                        //System.out.println("unsafe compare and swap long");
                        break;
                    }
                }
                if (i == 0) {
                    System.out.println("started");
                    start = System.nanoTime();
                }
            }

            System.out.printf("... Finished, average ping/pong took %,d ns%n", (System.nanoTime() - start) / runs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Unsafe UNSAFE = null;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
