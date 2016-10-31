package com.threeti.danone.android.db.dao;

import android.database.Cursor;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseStatement;

import com.threeti.danone.common.bean.Crying;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Crying".
*/
public class CryingDao extends AbstractDao<Crying, String> {

    public static final String TABLENAME = "Crying";

    /**
     * Properties of entity Crying.<br/>
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
        public final static Property Crytype = new Property(7, String.class, "crytype", false, "CRYTYPE");
        public final static Property TimeOfDay = new Property(8, String.class, "timeOfDay", false, "time_of_day");
        public final static Property Cryinyn = new Property(9, String.class, "cryinyn", false, "CRYINYN");
        public final static Property Crysttim = new Property(10, long.class, "crysttim", false, "CRYSTTIM");
        public final static Property Cryentim = new Property(11, long.class, "cryentim", false, "CRYENTIM");
    };


    public CryingDao(DaoConfig config) {
        super(config);
    }
    
    public CryingDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Crying\" (" + //
                "\"APP_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: appId
                "\"server_Id\" TEXT," + // 1: serverId
                "\"modify_reason\" TEXT," + // 2: modifyReason
                "\"delete_reason\" TEXT," + // 3: deleteReason
                "\"infant_id\" TEXT NOT NULL ," + // 4: infantId
                "\"DDAT\" INTEGER NOT NULL ," + // 5: ddat
                "\"STATUS\" INTEGER," + // 6: status
                "\"CRYTYPE\" TEXT," + // 7: crytype
                "\"time_of_day\" TEXT," + // 8: timeOfDay
                "\"CRYINYN\" TEXT," + // 9: cryinyn
                "\"CRYSTTIM\" INTEGER NOT NULL ," + // 10: crysttim
                "\"CRYENTIM\" INTEGER NOT NULL );"); // 11: cryentim
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Crying\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(DatabaseStatement stmt, Crying entity) {
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
 
        String crytype = entity.getCrytype();
        if (crytype != null) {
            stmt.bindString(8, crytype);
        }
 
        String timeOfDay = entity.getTimeOfDay();
        if (timeOfDay != null) {
            stmt.bindString(9, timeOfDay);
        }
 
        String cryinyn = entity.getCryinyn();
        if (cryinyn != null) {
            stmt.bindString(10, cryinyn);
        }
        stmt.bindLong(11, entity.getCrysttim());
        stmt.bindLong(12, entity.getCryentim());
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Crying readEntity(Cursor cursor, int offset) {
        Crying entity = new Crying( //
            cursor.getString(offset + 0), // appId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // serverId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // modifyReason
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deleteReason
            cursor.getString(offset + 4), // infantId
            new java.util.Date(cursor.getLong(offset + 5)), // ddat
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // status
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // crytype
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // timeOfDay
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // cryinyn
            cursor.getLong(offset + 10), // crysttim
            cursor.getLong(offset + 11) // cryentim
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Crying entity, int offset) {
        entity.setAppId(cursor.getString(offset + 0));
        entity.setServerId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setModifyReason(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeleteReason(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInfantId(cursor.getString(offset + 4));
        entity.setDdat(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setStatus(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setCrytype(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTimeOfDay(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCryinyn(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCrysttim(cursor.getLong(offset + 10));
        entity.setCryentim(cursor.getLong(offset + 11));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Crying entity, long rowId) {
        return entity.getAppId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Crying entity) {
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
