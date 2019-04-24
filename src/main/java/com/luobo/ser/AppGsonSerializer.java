/**
 *
 */
package com.luobo.ser;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import com.luobo.ser.serialization.Filter;
import com.luobo.ser.serialization.json.GsonSerializer;

public class AppGsonSerializer extends GsonSerializer {

    /**
     * @see pers.ksy.common.serialization.Serializer#serialize(Object)
     */
    @Override
    public String serialize(Object obj) {
        return getGson(null).toJson(obj);
    }

    /**
     * @see pers.ksy.common.serialization.Serializer#serialize(Object,
     *      pers.ksy.common.serialization.Filter[])
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

        //注册自定义序列化类
        gb.registerTypeAdapter(BaseEnum.class, new EnumSerializer());

        gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY) ;

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
         * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
         */
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            if (f.getDeclaringClass() == filterClass) {
                return exclusive == fields.contains(f.getName());
            }
            return false;
        }

        /**
         * @see com.google.gson.ExclusionStrategy#shouldSkipClass(Class)
         */
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            // TODO Auto-generated method stub
            return false;
        }

    }
}
