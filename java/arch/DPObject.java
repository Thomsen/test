
class DPObject {
    byte[] bytes;
    int start;
    int length;

    int getInt(String args);
    String getString(String args);
    double getDouble(String args);
    DPObject getObject(String args);
    DBObject[] getArray(String args);
}
