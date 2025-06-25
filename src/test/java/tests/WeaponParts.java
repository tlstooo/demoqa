package tests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeaponParts
{
    public String key;
    public int stack_size;
    public String sprite_index;
    public List<String> type;
    public Local local;
    public double weight;

    public static class Local {
        public String name_en;
        public String name_ru;
    }
}
