package com.thiha.health;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.thiha.health.booking.BookingDAO;
import com.thiha.health.booking.BookingDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DoctorDatabase_Impl extends DoctorDatabase {
  private volatile DoctorDAO _doctorDAO;

  private volatile BookingDAO _bookingDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `doctor_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `address` TEXT, `exp` TEXT, `phoneno` TEXT, `fee` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `booking_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `address` TEXT, `exp` TEXT, `phoneno` TEXT, `fee` TEXT, `bdate` TEXT, `btime` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '31979c4690587dd6c315c9ee0b1d5b5b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `doctor_table`");
        _db.execSQL("DROP TABLE IF EXISTS `booking_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDoctorTable = new HashMap<String, TableInfo.Column>(6);
        _columnsDoctorTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("exp", new TableInfo.Column("exp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("phoneno", new TableInfo.Column("phoneno", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("fee", new TableInfo.Column("fee", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDoctorTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDoctorTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDoctorTable = new TableInfo("doctor_table", _columnsDoctorTable, _foreignKeysDoctorTable, _indicesDoctorTable);
        final TableInfo _existingDoctorTable = TableInfo.read(_db, "doctor_table");
        if (! _infoDoctorTable.equals(_existingDoctorTable)) {
          return new RoomOpenHelper.ValidationResult(false, "doctor_table(com.thiha.health.Doctor).\n"
                  + " Expected:\n" + _infoDoctorTable + "\n"
                  + " Found:\n" + _existingDoctorTable);
        }
        final HashMap<String, TableInfo.Column> _columnsBookingTable = new HashMap<String, TableInfo.Column>(8);
        _columnsBookingTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("exp", new TableInfo.Column("exp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("phoneno", new TableInfo.Column("phoneno", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("fee", new TableInfo.Column("fee", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("bdate", new TableInfo.Column("bdate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookingTable.put("btime", new TableInfo.Column("btime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookingTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookingTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookingTable = new TableInfo("booking_table", _columnsBookingTable, _foreignKeysBookingTable, _indicesBookingTable);
        final TableInfo _existingBookingTable = TableInfo.read(_db, "booking_table");
        if (! _infoBookingTable.equals(_existingBookingTable)) {
          return new RoomOpenHelper.ValidationResult(false, "booking_table(com.thiha.health.booking.Booking).\n"
                  + " Expected:\n" + _infoBookingTable + "\n"
                  + " Found:\n" + _existingBookingTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "31979c4690587dd6c315c9ee0b1d5b5b", "1218810ec38e763cd9d5fdad9d6a05b0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "doctor_table","booking_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `doctor_table`");
      _db.execSQL("DELETE FROM `booking_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DoctorDAO.class, DoctorDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookingDAO.class, BookingDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public DoctorDAO doctorDAO() {
    if (_doctorDAO != null) {
      return _doctorDAO;
    } else {
      synchronized(this) {
        if(_doctorDAO == null) {
          _doctorDAO = new DoctorDAO_Impl(this);
        }
        return _doctorDAO;
      }
    }
  }

  @Override
  public BookingDAO bookingDAO() {
    if (_bookingDAO != null) {
      return _bookingDAO;
    } else {
      synchronized(this) {
        if(_bookingDAO == null) {
          _bookingDAO = new BookingDAO_Impl(this);
        }
        return _bookingDAO;
      }
    }
  }
}
