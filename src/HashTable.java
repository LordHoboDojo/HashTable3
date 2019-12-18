

public class HashTable {
    public Entry[] data;
    int size;
    int collisions;

    public HashTable() {
        size = 0;
        data = new Entry[101];
        collisions =0;
    }

    public HashTable(int initCap) {
        size = 0;
        collisions = 0;
        data = new Entry[initCap];
    }

    Object put(Object key, Object value) throws IllegalStateException {
        int tmp = Math.abs(key.hashCode() % data.length);
        int i = tmp;
        int index = getIndex(key);
        if (index >0 && (data[index] !=null ||!data[index].removed))
        {
            Entry ret = data[index];
            data[index] = new Entry(key,value);
            return ret;
        }
        do {
            collisions++;
            if (data[i] == null||data[i].removed) {
                data[i] = new Entry(key, value);
                size++;
                return null;
            }
            i = (i + 1) % data.length;
        } while (i != tmp);
        return null;

    }
    Entry remove(Object key)
    {
        int index = key.hashCode() %data.length;
        int t = index;
        while (data[t] != null)
        {
            if (data[t].key.equals(key))
            {
                size--;
                data[t].toggleRemoved();
                return data[t];
            }

           t = (t+1) %data.length;
        }
        return null;
    }
    Object get(Object key)
    {
        int index = Math.abs(key.hashCode() %data.length);
        int t = index;
        while (data[t] !=null)
        {
            if (data[t].key.equals(key)&&!data[t].removed) {
                return data[t];
            }
            t = (t+1) % data.length;
        }
        return null;
    }
    int getProbes(Object key)
    {
        int t = Math.abs(key.hashCode() %data.length);
        int start = t;
        int probes =0;
       do {
            if (data[t] !=null&& data[t].key.equals(key)&& !data[t].removed){
                return probes;
            }
            t = (t+1) %data.length;
            probes++;
        }
        while (data[t] !=null&& start!=t);
        return probes;
    }
    int getIndex(Object key)
    {
        int index = Math.abs(key.hashCode() %data.length);
        int t = index;
        while (data[t] !=null)
        {
            if (data[t].key.equals(key)&&!data[t].removed) {
                return t;
            }
            t = (t+1) % data.length;
        }
        return -1;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i=0;i<data.length;i++)
        {
           if (data[i] != null&& !data[i].removed) res.append(data[i]).append(" ").append(i).append(" \n");
        }
        return res.toString();
    }
}

