package com.thiha.health.hospital;

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
public final class HospitalDAO_Impl implements HospitalDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Hospital> __insertionAdapterOfHospital;

  private final EntityDeletionOrUpdateAdapter<Hospital> __deletionAdapterOfHospital;

  private final EntityDeletionOrUpdateAdapter<Hospital> __updateAdapterOfHospital;

  public HospitalDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHospital = new EntityInsertionAdapter<Hospital>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `hospital_table` (`id`,`hName`,`hAddress`,`hPhoneno`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Hospital value) {
        stmt.bindLong(1, value.getId());
        if (value.hName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.hName);
        }
        if (value.hAddress == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.hAddress);
        }
        if (value.hPhoneno == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.hPhoneno);
        }
      }
    };
    this.__deletionAdapterOfHospital = new EntityDeletionOrUpdateAdapter<Hospital>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `hospital_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Hospital value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfHospital = new EntityDeletionOrUpdateAdapter<Hospital>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `hospital_table` SET `id` = ?,`hName` = ?,`hAddress` = ?,`hPhoneno` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Hospital value) {
        stmt.bindLong(1, value.getId());
        if (value.hName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.hName);
        }
        if (value.hAddress == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.hAddress);
        }
        if (value.hPhoneno == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.hPhoneno);
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void insert(final Hospital hospital) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHospital.insert(hospital);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Hospital hospital) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHospital.handle(hospital);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Hospital hospital) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHospital.handle(hospital);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Hospital>> getAllHospital() {
    final String _sql = "SELECT * FROM hospital_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hospital_table"}, false, new Callable<List<Hospital>>() {
      @Override
      public List<Hospital> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHName = CursorUtil.getColumnIndexOrThrow(_cursor, "hName");
          final int _cursorIndexOfHAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "hAddress");
          final int _cursorIndexOfHPhoneno = CursorUtil.getColumnIndexOrThrow(_cursor, "hPhoneno");
          final List<Hospital> _result = new ArrayList<Hospital>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Hospital _item;
            _item = new Hospital();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            if (_cursor.isNull(_cursorIndexOfHName)) {
              _item.hName = null;
            } else {
              _item.hName = _cursor.getString(_cursorIndexOfHName);
            }
            if (_cursor.isNull(_cursorIndexOfHAddress)) {
              _item.hAddress = null;
            } else {
              _item.hAddress = _cursor.getString(_cursorIndexOfHAddress);
            }
            if (_cursor.isNull(_cursorIndexOfHPhoneno)) {
              _item.hPhoneno = null;
            } else {
              _item.hPhoneno = _cursor.getString(_cursorIndexOfHPhoneno);
            }
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
