package com.threeti.danone.android.db.dao;

import android.database.Cursor;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseStatement;

import com.threeti.danone.common.bean.Stool;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "stool".
*/
public class StoolDao extends AbstractDao<Stool, String> {

    public static final String TABLENAME = "stool";

    /**
     * Properties of entity Stool.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AppId = new Property(0, String.class, "appId", true, "APP_ID");
        public final static Property ServerId = new Property(1, String.class, "serverId", false, "server_Id");
        public final static Property ModifyReason = new Property(2, String.class, "modifyReason", false, "modify_reason");
        public final static Property DeleteReason = new Property(3, String.class, "deleteReason", false, "delete_reason");
        public final static Property InfantId = new Property(4, String.class, "infantId", false, "infant_id");
        public final static Property Ddat = new Property(5, java.util.Date.class, "ddat", false, "DDAT");
        public final static Property Status = new Property(6, Integer.class, "status", false, "STATUS");
        public final static Property Stoolyn = new Property(7, String.class, "stoolyn", false, "STOOLYN");
        public final static Property Image = new Property(8, String.class, "image", false, "IMAGE");
        public final static Property Type = new Property(9, Integer.class, "type", false, "TYPE");
    };


    public StoolDao(DaoConfig config) {
        super(config);
    }
    
    public StoolDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"stool\" (" + //
                "\"APP_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: appId
                "\"server_Id\" TEXT," + // 1: serverId
                "\"modify_reason\" TEXT," + // 2: modifyReason
                "\"delete_reason\" TEXT," + // 3: deleteReason
                "\"infant_id\" TEXT NOT NULL ," + // 4: infantId
                "\"DDAT\" INTEGER NOT NULL ," + // 5: ddat
                "\"STATUS\" INTEGER," + // 6: status
                "\"STOOLYN\" TEXT," + // 7: stoolyn
                "\"IMAGE\" TEXT," + // 8: image
                "\"TYPE\" INTEGER);"); // 9: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"stool\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(DatabaseStatement stmt, Stool entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getAppId());
 
        String serverId = entity.getServerId();
        if (serverId != null) {
            stmt.bindString(2, serverId);
        }
 
        String modifyReason = entity.getModifyReason();
        if (modifyReason != null) {
            stmt.bindString(3, modifyReason);
        }
 
        String deleteReason = entity.getDeleteReason();
        if (deleteReason != null) {
            stmt.bindString(4, deleteReason);
        }
        stmt.bindString(5, entity.getInfantId());
        stmt.bindLong(6, entity.getDdat().getTime());
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(7, status);
        }
 
        String stoolyn = entity.getStoolyn();
        if (stoolyn != null) {
            stmt.bindString(8, stoolyn);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(9, image);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(10, type);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Stool readEntity(Cursor cursor, int offset) {
        Stool entity = new Stool( //
            cursor.getString(offset + 0), // appId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // serverId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // modifyReason
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deleteReason
            cursor.getString(offset + 4), // infantId
            new java.util.Date(cursor.getLong(offset + 5)), // ddat
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // status
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // stoolyn
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // image
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9) // type
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Stool entity, int offset) {
        entity.setAppId(cursor.getString(offset + 0));
        entity.setServerId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setModifyReason(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeleteReason(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInfantId(cursor.getString(offset + 4));
        entity.setDdat(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setStatus(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setStoolyn(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImage(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setType(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Stool entity, long rowId) {
        return entity.getAppId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Stool entity) {
        if(entity != null) {
            return entity.getAppId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
