package memory;

public class Memory {
    int a;
    byte b;
    String name;
}

//// HotSpot 64-bit, COOPS, CCPS
//    memory.Memory object internals:
//        OFF  SZ     TYPE DESCRIPTION               VALUE
//        0   8          (object header: mark)     N/A
//        8   4          (object header: class)    N/A
//        12   4      int memory.Memory.a                  N/A
//        16   1     byte memory.Memory.b                  N/A
//        17   3          (alignment/padding gap)
//        20   4   String memory.Memory.name               N/A
//        Instance size: 24 bytes
//        Space losses: 3 bytes internal + 0 bytes external = 3 bytes total

//// HotSpot JDK 15, 32-bit
//    memory.Memory object internals:
//        OFF  SZ     TYPE DESCRIPTION               VALUE
//        0   4          (object header: mark)     N/A
//        4   4          (object header: class)    N/A
//        8   4      int memory.Memory.a                  N/A
//        12   1     byte memory.Memory.b                  N/A
//        13   3          (alignment/padding gap)
//        16   4   String memory.Memory.name               N/A
//        20   4          (object alignment gap)
//        Instance size: 24 bytes
//        Space losses: 3 bytes internal + 4 bytes external = 7 bytes total

//// HotSpot JDK 15, 64-bit
//    memory.Memory object internals:
//        OFF  SZ     TYPE DESCRIPTION               VALUE
//        0   8          (object header: mark)     N/A
//        8   8          (object header: class)    N/A
//        16   4      int memory.Memory.a                  N/A
//        20   1     byte memory.Memory.b                  N/A
//        21   3          (alignment/padding gap)
//        24   8   String memory.Memory.name               N/A
//        Instance size: 32 bytes
//        Space losses: 3 bytes internal + 0 bytes external = 3 bytes total
