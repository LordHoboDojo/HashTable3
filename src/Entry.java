class Entry{
    boolean removed;
    public Object key;
    public Object val;
    public Entry()
    {
        removed = false;
        key = null;
        val = null;
    }
    public  void toggleRemoved()
    {
        removed = true;
    }
    public Entry(Object key, Object val)
    {
        this.key = key;
        this.val = val;
    }
    @Override
    public String toString()
    {
        return "<" + key + "," + val + ">";
    }
}