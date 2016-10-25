package com.threeti.danone.android.db.dao;

import android.database.Cursor;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseStatement;

import com.threeti.danone.common.bean.SpittingUp;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "spitting_up".
*/
public class SpittingUpDao extends AbstractDao<SpittingUp, String> {

    public static final String TABLENAME = "spitting_up";

    /**
     * Properties of entity SpittingUp.<br/>
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
        public final static Property ReguNumber = new Property(7, String.class, "reguNumber", false, "REGU_NUMBER");
        public final static Property VomiNumber = new Property(8, String.class, "vomiNumber", false, "VOMI_NUMBER");
    };


    public SpittingUpDao(DaoConfig config) {
        super(config);
    }
    
    public SpittingUpDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"spitting_up\" (" + //
                "\"APP_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: appId
                "\"server_Id\" TEXT," + // 1: serverId
                "\"modify_reason\" TEXT," + // 2: modifyReason
                "\"delete_reason\" TEXT," + // 3: deleteReason
                "\"infant_id\" TEXT," + // 4: infantId
                "\"DDAT\" INTEGER NOT NULL ," + // 5: ddat
                "\"STATUS\" INTEGER," + // 6: status
                "\"REGU_NUMBER\" TEXT," + // 7: reguNumber
                "\"VOMI_NUMBER\" TEXT);"); // 8: vomiNumber
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"spitting_up\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(DatabaseStatement stmt, SpittingUp entity) {
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
 
        String infantId = entity.getInfantId();
        if (infantId != null) {
            stmt.bindString(5, infantId);
        }
        stmt.bindLong(6, entity.getDdat().getTime());
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(7, status);
        }
 
        String reguNumber = entity.getReguNumber();
        if (reguNumber != null) {
            stmt.bindString(8, reguNumber);
        }
 
        String vomiNumber = entity.getVomiNumber();
        if (vomiNumber != null) {
            stmt.bindString(9, vomiNumber);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SpittingUp readEntity(Cursor cursor, int offset) {
        SpittingUp entity = new SpittingUp( //
            cursor.getString(offset + 0), // appId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // serverId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // modifyReason
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deleteReason
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // infantId
            new java.util.Date(cursor.getLong(offset + 5)), // ddat
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // status
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // reguNumber
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // vomiNumber
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SpittingUp entity, int offset) {
        entity.setAppId(cursor.getString(offset + 0));
        entity.setServerId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setModifyReason(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeleteReason(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInfantId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDdat(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setStatus(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setReguNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setVomiNumber(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(SpittingUp entity, long rowId) {
        return entity.getAppId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(SpittingUp entity) {
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
