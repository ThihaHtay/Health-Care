package com.thiha.health;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DoctorDAO_Impl implements DoctorDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Doctor> __insertionAdapterOfDoctor;

  private final EntityDeletionOrUpdateAdapter<Doctor> __deletionAdapterOfDoctor;

  private final EntityDeletionOrUpdateAdapter<Doctor> __updateAdapterOfDoctor;

  public DoctorDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDoctor = new EntityInsertionAdapter<Doctor>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `doctor_table` (`id`,`name`,`address`,`exp`,`phoneno`,`fee`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Doctor value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress());
        }
        if (value.getExp() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExp());
        }
        if (value.getPhoneno() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhoneno());
        }
        if (value.getFee() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFee());
        }
      }
    };
    this.__deletionAdapterOfDoctor = new EntityDeletionOrUpdateAdapter<Doctor>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `doctor_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Doctor value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDoctor = new EntityDeletionOrUpdateAdapter<Doctor>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `doctor_table` SET `id` = ?,`name` = ?,`address` = ?,`exp` = ?,`phoneno` = ?,`fee` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Doctor value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress());
        }
        if (value.getExp() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExp());
        }
        if (value.getPhoneno() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhoneno());
        }
        if (value.getFee() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFee());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void insert(final Doctor doctor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDoctor.insert(doctor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Doctor doctor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDoctor.handle(doctor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Doctor doctor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDoctor.handle(doctor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Doctor>> getALLDoctor() {
    final String _sql = "SELECT * FROM doctor_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"doctor_table"}, false, new Callable<List<Doctor>>() {
      @Override
      public List<Doctor> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfExp = CursorUtil.getColumnIndexOrThrow(_cursor, "exp");
          final int _cursorIndexOfPhoneno = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneno");
          final int _cursorIndexOfFee = CursorUtil.getColumnIndexOrThrow(_cursor, "fee");
          final List<Doctor> _result = new ArrayList<Doctor>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Doctor _item;
            _item = new Doctor();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item.setName(_tmpName);
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            _item.setAddress(_tmpAddress);
            final String _tmpExp;
            if (_cursor.isNull(_cursorIndexOfExp)) {
              _tmpExp = null;
            } else {
              _tmpExp = _cursor.getString(_cursorIndexOfExp);
            }
            _item.setExp(_tmpExp);
            final String _tmpPhoneno;
            if (_cursor.isNull(_cursorIndexOfPhoneno)) {
              _tmpPhoneno = null;
            } else {
              _tmpPhoneno = _cursor.getString(_cursorIndexOfPhoneno);
            }
            _item.setPhoneno(_tmpPhoneno);
            final String _tmpFee;
            if (_cursor.isNull(_cursorIndexOfFee)) {
              _tmpFee = null;
            } else {
              _tmpFee = _cursor.getString(_cursorIndexOfFee);
            }
            _item.setFee(_tmpFee);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
