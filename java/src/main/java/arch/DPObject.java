package arch;

abstract class DPObject {
    byte[] bytes;
    int start;
    int length;

    abstract int getInt(String args);
    abstract String getString(String args);
    abstract double getDouble(String args);
    abstract DPObject getObject(String args);
//    abstract DBObject[] getArray(String args);
}
