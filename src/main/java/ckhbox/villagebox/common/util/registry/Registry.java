//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.util.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Registry<T extends IRegistrable> {
    private final HashMap<Integer, T> mapIntData = new HashMap<Integer, T>();

    private final List<T> dataList = new ArrayList<T>();

    public List<T> getAll() {
        return dataList;
    }

    public void register(int regID, T data) {
        if (mapIntData.containsKey(regID)) {
            System.out.println("Can not register the object, type/class is existed!");
        } else {
            mapIntData.put(regID, data);

            dataList.add(data);

            data.setRegID(regID);
        }
    }

    public T get(int regID) {
        if (mapIntData.containsKey(regID)) {
            return mapIntData.get(regID);
        } else {
            System.out.println("Can not fint registed item where id =" + regID);
            return null;
        }
    }
}
