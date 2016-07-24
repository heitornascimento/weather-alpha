package lm.com.br.weatherdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by heitornascimento on 7/22/16.
 */
public class City extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;

    public City( ){
    }

    public City(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
