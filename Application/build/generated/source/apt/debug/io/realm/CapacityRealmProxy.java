package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CapacityRealmProxy extends com.example.android.bluetoothchat.Capacity
    implements RealmObjectProxy, CapacityRealmProxyInterface {

    static final class CapacityColumnInfo extends ColumnInfo
        implements Cloneable {

        public long phoneNameIndex;
        public long sendedMbIndex;

        CapacityColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.phoneNameIndex = getValidColumnIndex(path, table, "Capacity", "phoneName");
            indicesMap.put("phoneName", this.phoneNameIndex);
            this.sendedMbIndex = getValidColumnIndex(path, table, "Capacity", "sendedMb");
            indicesMap.put("sendedMb", this.sendedMbIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final CapacityColumnInfo otherInfo = (CapacityColumnInfo) other;
            this.phoneNameIndex = otherInfo.phoneNameIndex;
            this.sendedMbIndex = otherInfo.sendedMbIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final CapacityColumnInfo clone() {
            return (CapacityColumnInfo) super.clone();
        }

    }
    private CapacityColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("phoneName");
        fieldNames.add("sendedMb");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CapacityRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CapacityColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.example.android.bluetoothchat.Capacity.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$phoneName() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.phoneNameIndex);
    }

    public void realmSet$phoneName(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'phoneName' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public double realmGet$sendedMb() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.sendedMbIndex);
    }

    public void realmSet$sendedMb(double value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.sendedMbIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.sendedMbIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Capacity")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Capacity");
            realmObjectSchema.add(new Property("phoneName", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("sendedMb", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Capacity");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Capacity")) {
            Table table = sharedRealm.getTable("class_Capacity");
            table.addColumn(RealmFieldType.STRING, "phoneName", Table.NULLABLE);
            table.addColumn(RealmFieldType.DOUBLE, "sendedMb", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("phoneName"));
            table.setPrimaryKey("phoneName");
            return table;
        }
        return sharedRealm.getTable("class_Capacity");
    }

    public static CapacityColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Capacity")) {
            Table table = sharedRealm.getTable("class_Capacity");
            final long columnCount = table.getColumnCount();
            if (columnCount != 2) {
                if (columnCount < 2) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final CapacityColumnInfo columnInfo = new CapacityColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("phoneName")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'phoneName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("phoneName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'phoneName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.phoneNameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'phoneName' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("phoneName")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'phoneName' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("phoneName"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'phoneName' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("sendedMb")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'sendedMb' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("sendedMb") != RealmFieldType.DOUBLE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'double' for field 'sendedMb' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.sendedMbIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'sendedMb' does support null values in the existing Realm file. Use corresponding boxed type for field 'sendedMb' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Capacity' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Capacity";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.android.bluetoothchat.Capacity createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.android.bluetoothchat.Capacity obj = null;
        if (update) {
            Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("phoneName")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("phoneName"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CapacityRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("phoneName")) {
                if (json.isNull("phoneName")) {
                    obj = (io.realm.CapacityRealmProxy) realm.createObjectInternal(com.example.android.bluetoothchat.Capacity.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CapacityRealmProxy) realm.createObjectInternal(com.example.android.bluetoothchat.Capacity.class, json.getString("phoneName"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'phoneName'.");
            }
        }
        if (json.has("sendedMb")) {
            if (json.isNull("sendedMb")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sendedMb' to null.");
            } else {
                ((CapacityRealmProxyInterface) obj).realmSet$sendedMb((double) json.getDouble("sendedMb"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.android.bluetoothchat.Capacity createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.example.android.bluetoothchat.Capacity obj = new com.example.android.bluetoothchat.Capacity();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("phoneName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CapacityRealmProxyInterface) obj).realmSet$phoneName(null);
                } else {
                    ((CapacityRealmProxyInterface) obj).realmSet$phoneName((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("sendedMb")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'sendedMb' to null.");
                } else {
                    ((CapacityRealmProxyInterface) obj).realmSet$sendedMb((double) reader.nextDouble());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'phoneName'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.android.bluetoothchat.Capacity copyOrUpdate(Realm realm, com.example.android.bluetoothchat.Capacity object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.android.bluetoothchat.Capacity) cachedRealmObject;
        } else {
            com.example.android.bluetoothchat.Capacity realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((CapacityRealmProxyInterface) object).realmGet$phoneName();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.CapacityRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.example.android.bluetoothchat.Capacity copy(Realm realm, com.example.android.bluetoothchat.Capacity newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.android.bluetoothchat.Capacity) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.android.bluetoothchat.Capacity realmObject = realm.createObjectInternal(com.example.android.bluetoothchat.Capacity.class, ((CapacityRealmProxyInterface) newObject).realmGet$phoneName(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((CapacityRealmProxyInterface) realmObject).realmSet$sendedMb(((CapacityRealmProxyInterface) newObject).realmGet$sendedMb());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.android.bluetoothchat.Capacity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
        long tableNativePtr = table.getNativeTablePointer();
        CapacityColumnInfo columnInfo = (CapacityColumnInfo) realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CapacityRealmProxyInterface) object).realmGet$phoneName();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetDouble(tableNativePtr, columnInfo.sendedMbIndex, rowIndex, ((CapacityRealmProxyInterface)object).realmGet$sendedMb(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
        long tableNativePtr = table.getNativeTablePointer();
        CapacityColumnInfo columnInfo = (CapacityColumnInfo) realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.android.bluetoothchat.Capacity object = null;
        while (objects.hasNext()) {
            object = (com.example.android.bluetoothchat.Capacity) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CapacityRealmProxyInterface) object).realmGet$phoneName();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                Table.nativeSetDouble(tableNativePtr, columnInfo.sendedMbIndex, rowIndex, ((CapacityRealmProxyInterface)object).realmGet$sendedMb(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.android.bluetoothchat.Capacity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
        long tableNativePtr = table.getNativeTablePointer();
        CapacityColumnInfo columnInfo = (CapacityColumnInfo) realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CapacityRealmProxyInterface) object).realmGet$phoneName();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        Table.nativeSetDouble(tableNativePtr, columnInfo.sendedMbIndex, rowIndex, ((CapacityRealmProxyInterface)object).realmGet$sendedMb(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.android.bluetoothchat.Capacity.class);
        long tableNativePtr = table.getNativeTablePointer();
        CapacityColumnInfo columnInfo = (CapacityColumnInfo) realm.schema.getColumnInfo(com.example.android.bluetoothchat.Capacity.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.android.bluetoothchat.Capacity object = null;
        while (objects.hasNext()) {
            object = (com.example.android.bluetoothchat.Capacity) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CapacityRealmProxyInterface) object).realmGet$phoneName();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                Table.nativeSetDouble(tableNativePtr, columnInfo.sendedMbIndex, rowIndex, ((CapacityRealmProxyInterface)object).realmGet$sendedMb(), false);
            }
        }
    }

    public static com.example.android.bluetoothchat.Capacity createDetachedCopy(com.example.android.bluetoothchat.Capacity realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.android.bluetoothchat.Capacity unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.android.bluetoothchat.Capacity)cachedObject.object;
            } else {
                unmanagedObject = (com.example.android.bluetoothchat.Capacity)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.android.bluetoothchat.Capacity();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((CapacityRealmProxyInterface) unmanagedObject).realmSet$phoneName(((CapacityRealmProxyInterface) realmObject).realmGet$phoneName());
        ((CapacityRealmProxyInterface) unmanagedObject).realmSet$sendedMb(((CapacityRealmProxyInterface) realmObject).realmGet$sendedMb());
        return unmanagedObject;
    }

    static com.example.android.bluetoothchat.Capacity update(Realm realm, com.example.android.bluetoothchat.Capacity realmObject, com.example.android.bluetoothchat.Capacity newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((CapacityRealmProxyInterface) realmObject).realmSet$sendedMb(((CapacityRealmProxyInterface) newObject).realmGet$sendedMb());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Capacity = [");
        stringBuilder.append("{phoneName:");
        stringBuilder.append(realmGet$phoneName() != null ? realmGet$phoneName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sendedMb:");
        stringBuilder.append(realmGet$sendedMb());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapacityRealmProxy aCapacity = (CapacityRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCapacity.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCapacity.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCapacity.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
