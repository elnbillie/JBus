package MuhammadBillieElianJBusRS;

import java.util.HashMap;

public class Serializable
{
    public final int id;
    private static HashMap<Class<?>,Integer> mapCounter = new HashMap<>();
    
    protected Serializable(){
        if(mapCounter.containsKey(this.getClass())){
            mapCounter.put(this.getClass(), mapCounter.get(this.getClass()) + 1);
        }else{
            mapCounter.put(this.getClass(),1);
        }
        this.id = mapCounter.get(this.getClass());
    }

    public static Integer getLastAssignedId(Class<?>key){
        return mapCounter.getOrDefault(key,0);
    }

    public static Integer setLastAssignedId(Class<?>key, int value){
        mapCounter.put(key,value);
        return value;
    }


    public int compareTo(Serializable o){
        return Integer.compare(this.id, o.id);
    }


    public boolean equals(Object obj) {
        if (obj instanceof Serializable) {
            Serializable serialObj = (Serializable) obj;
            return this.id == serialObj.id;
        }
        return false;
    }

    public boolean equals(Serializable o) {
        return this.id == o.id;
    }
}
