package com.sir.app.utils;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;

/**
 * 数据库相关辅助类
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public class DBUtils {

    public static String DB_NAME;

    public static LiteOrm liteOrm;

    public static void createDb(Context activity, String userId) {
        if (liteOrm == null) {
            DB_NAME = userId + ".db";
            liteOrm = LiteOrm.newSingleInstance(activity, DB_NAME);
        }
        liteOrm.setDebugged(true);
    }

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }

    /**
     * 插入一条记录
     *
     * @param t
     */
    public static <T> void insert(T t) {
        liteOrm.save(t);
    }

    /**
     * 插入所有记录
     *
     * @param list
     */
    public static <T> void insertAll(List<T> list) {
        liteOrm.save(list);
    }

    /**
     * 查询所有
     *
     * @param cla
     * @return
     */
    public static <T> List<T> QueryAll(Class<T> cla) {
        return liteOrm.query(cla);
    }

    /**
     * 查询某字段等于Value的值
     *
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public static <T> List<T> queryByWhere(Class<T> cla, String field, Object[] value) {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T queryById(String id, Class<T> cla) {
        return liteOrm.queryById(id, cla);
    }

    /**
     * 查询某字段等于Value的值,可以指定从1-20,就是分页
     *
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public static <T> List<T> queryByWhereLength(Class<T> cla, String field, Object[] value, int start, int length) {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
    }

    /**
     * 删除所有某字段等于Value的值
     *
     * @param cla
     * @param field
     * @param value
     */
    public static <T> void deleteWhere(Class<T> cla, String field, Object[] value) {
        liteOrm.delete(new WhereBuilder(cla).where(field + "=?", value));
    }

    /**
     * 删除所有
     *
     * @param cla
     */
    public static <T> void deleteAll(Class<T> cla) {
        liteOrm.deleteAll(cla);
    }

    /**
     * 仅在以存在时更新
     *
     * @param t
     */
    public static <T> void update(T t) {
        liteOrm.update(t, ConflictAlgorithm.Replace);
    }

    /**
     * 更新全部
     *
     * @param list
     */
    public static <T> void updateALL(List<T> list) {
        liteOrm.update(list);
    }
}
