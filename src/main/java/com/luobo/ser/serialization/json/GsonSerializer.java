/**
 * 
 */
package com.luobo.ser.serialization.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luobo.ser.serialization.Filter;
import com.luobo.ser.serialization.Serializer;
import java.util.Arrays;
import java.util.List;

/**
 * simple introduction
 *
 * <p>
 * detailed comment
 * 
 * @author boil_000 2015年4月3日
 * @see
 * @since 1.0
 */
public class GsonSerializer extends BaseJsonSerializer {

    /**
     * @see Serializer#serialize(Object)
     */
    @Override
    public String serialize(Object obj) {
        return getGson(null).toJson(obj);
    }

    /**
     * @see Serializer#serialize(Object,
     *      Filter[])
     */
    @Override
    public String serialize(Object obj, Filter[] filters) {
        return getGson(filters).toJson(obj);
    }

    /**
     * @return the gson
     */
    private Gson getGson(Filter[] filters) {
        GsonBuilder gb = new GsonBuilder().setDateFormat(datePattern);
        if (null != filters) {
            ExclusionStrategy[] strategies = new ExclusionStrategy[filters.length];
            for (int i = 0; i < filters.length; i++) {
                strategies[i] = new SerializeFilterStrategy(
                        filters[i].getTarget(), Arrays.asList(filters[i]
                                .getFields()), filters[i].isExclusive());
            }
            gb.setExclusionStrategies(strategies);
            //允许返回字段为空
            gb.serializeNulls();
        }
        Gson gson = gb.create();
        return gson;
    }

    /**
     *
     * Gson序列化过滤策略
     *
     * @author 孔思宇 2015年4月3日
     * @see
     * @since 1.1
     */
    private static class SerializeFilterStrategy implements ExclusionStrategy {
        private Class<?> filterClass;
        private List<String> fields;
        private boolean exclusive = true;

        /**
         * @param fields
         * @param excludes
         */
        private SerializeFilterStrategy(Class<?> filterClass,
                List<String> fields, boolean exclusive) {
            super();
            this.filterClass = filterClass;
            this.fields = fields;
            this.exclusive = exclusive;
        }

        /**
         * @see ExclusionStrategy#shouldSkipField(FieldAttributes)
         */
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            if (f.getDeclaringClass() == filterClass) {
                return exclusive == fields.contains(f.getName());
            }
            return false;
        }

        /**
         * @see ExclusionStrategy#shouldSkipClass(Class)
         */
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            // TODO Auto-generated method stub
            return false;
        }

    }
}
