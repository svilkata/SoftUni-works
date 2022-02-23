package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    //Part 1
    @Override
    //Persist Object in the Database - insert a new row or update an existing row
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
//        ensureTable(); - може да си го разпишем
        Field pk_idColumn = getIdColumn(entity.getClass());
        pk_idColumn.setAccessible(true);
        Object idValue = pk_idColumn.get(entity); //вземи стойността на полето id

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, (long) idValue); //cast-ни го на long
    }

    //Insert a new row in the database
    private boolean doInsert(E entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass()); //username, age, registration_date
        List<String> tableValues = getColumnsValuesWithoitId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName,
                String.join(",", tableFields),
                String.join(",", tableValues));

        return connection.prepareStatement(insertQuery).execute();
    }

    //Update a specific row in the database
    private boolean doUpdate(E entity, long idValue) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass()); //username, age, registration_date
        List<String> tableValues = getColumnsValuesWithoitId(entity);


        List<String> setStatements = new ArrayList<>();
        for (int i = 0; i < tableFields.size() - 1; i++) {
            setStatements.add(tableFields.get(i) + "=" + tableValues.get(i));
        }

        String updateQuery = String.format("UPDATE %s SET %s WHERE id=%d",
                tableName,
                String.join(",", setStatements),
                idValue);


        return connection.prepareStatement(updateQuery).execute();
    }

    private List<String> getColumnsWithoutId(Class<?> aClass) {
        List<String> collect = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))  //само ги филтрирай - тези полета, които са анотирани с Column анотация
                .map(f -> f.getAnnotationsByType(Column.class)) //след като са налични полетата, ги вземи
                .map(a -> a[0].name()) //Вземи името на полето - само веднъж имаме върху класа User анотация с Entity анотация, или анотация Entity се използва само на един клас за момента
                .collect(Collectors.toList());

        return collect;
    }

    private List<String> getColumnsValuesWithoitId(E entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(entity);

            if (o instanceof String || o instanceof LocalDate) {
                values.add("'" + o + "'");
            } else {
                values.add(o.toString());
            }
        }

        return values;
    }

    private Field getIdColumn(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            boolean annotationPresent = declaredField.isAnnotationPresent(Id.class);
            if (annotationPresent) {
                return declaredField;
            }
        }

        throw new UnsupportedOperationException("Entity does not have primary key");
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);
        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationsByType[0].name();
    }

    //Fetching Results - find
    @Override
    public Iterable<E> find(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return find(table, where, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where, String... colsToDisplay) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tableName = getTableName(table);

        String selectedCols;
        if (colsToDisplay == null) {
            selectedCols = "*";
        } else {
            selectedCols = Arrays.stream(colsToDisplay).collect(Collectors.joining(","));
        }

        String selectQuery = String.format("SELECT %s FROM %s %s",
                selectedCols,
                tableName,
                where != null ? "WHERE " + where : "");

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        List<E> output = new ArrayList<>();
        while (resultSet.next()) {
            //resultEntity - като го променим в метода fillEntity, той ще се промени и тук в метода findFirst
            E resultEntity = table.getDeclaredConstructor().newInstance(); //new instance of class the object/instance of which is table
            fillEntity(table, resultSet, resultEntity);
            output.add(resultEntity);
        }

        return output;
    }

    @Override
    public E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String tableName = getTableName(table);

        String selectQuery = String.format("SELECT * FROM %s %s LIMIT 1", tableName,
                where != null ? "WHERE " + where : "");

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        //resultEntity - като го променим в метода fillEntity, той ще се промени и тук в метода findFirst
        E resultEntity = table.getDeclaredConstructor().newInstance(); //new instance of class the object/instance of which is table
        fillEntity(table, resultSet, resultEntity);

        return resultEntity;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E resultEntity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = table.getDeclaredFields();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> sqlSelectColumns = new ArrayList<>();
        for (int i = 1; i <= columnCount ; i++) {
            String columnName = metaData.getColumnName(i);
            sqlSelectColumns.add(columnName);
        }

        for (Field declaredField : declaredFields) {
            String fieldNameClass = declaredField.getAnnotationsByType(Column.class)[0].name();
            if (sqlSelectColumns.contains(fieldNameClass)) {
                declaredField.setAccessible(true);
                fillField(declaredField, resultSet, resultEntity);
            }
        }
    }

    //from SQL type we convert to JAVA data type  - for each field - обратното на getSQLType метода
    private void fillField(Field declaredField, ResultSet resultSet, E resultEntity) throws SQLException,
            IllegalAccessException {
        Class<?> fieldType = declaredField.getType();
        //String fieldName = declaredField.getName(); //връща името на полета на изкуствената инстанция, която е взела имена на полетата от SQL базата/таблицата
        String fieldName = declaredField.getAnnotationsByType(Column.class)[0].name(); //чрез използване на анотация връща името на полето от изкуствената инстанция на класа

        if (fieldType == Integer.class || fieldType == int.class) {
            int value = resultSet.getInt(fieldName); //a java.sql command

            //resultEntity e инстанция на table
            //declared field е едно от полетата на table
            //fieldName e името на полето
            //value е резултат от SQL заявка (java.sql)
            declaredField.set(resultEntity, value);
        } else if (fieldType == long.class) {
            long value = resultSet.getLong(fieldName); //a java.sql command
            declaredField.set(resultEntity, value);
        } else if (fieldType == LocalDate.class) {
            LocalDate value = LocalDate.parse(resultSet.getString(fieldName));
            declaredField.set(resultEntity, value);
        } else {
            String value = resultSet.getString(fieldName);
            declaredField.set(resultEntity, value);
        }
    }


    //Part 2
    //Create Table
    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);

        String createQuery = String.format("CREATE TABLE %s (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, %s)", tableName, fieldsWithTypes);

        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.execute();
    }

    private String getSQLFieldsWithTypes(Class<E> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class)) //без id-то на класа
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(field -> {
                    String fieldName = field.getAnnotationsByType(Column.class)[0].name(); //тези полета на класа User, които са анотирани с Column анотация

                    //Определяме SQL типа на база JAVA типа
                    String sqlType = getSQLType(field.getType());

                    return fieldName + " " + sqlType;
                })
                .collect(Collectors.joining(","));
    }

    //Определяме SQL типа на база JAVA типа - от Java към SQL тип
    private String getSQLType(Class<?> type) {
        String sqlType = "";
        if (type == Integer.class || type == int.class) {
            sqlType = "INT";
        } else if (type == String.class) {
            sqlType = "VARCHAR(200)";
        } else if (type == LocalDate.class) {
            sqlType = "DATE";
        }
        return sqlType;
    }

    //Alter Table – add new columns
    public void doAlter(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String addColumnStatements = getAddColumnStatementsForNewFields(entityClass);

        String alterQuery = String.format("ALTER TABLE %s %s", tableName, addColumnStatements);

        PreparedStatement statement = connection.prepareStatement(alterQuery);
        statement.execute();
    }

    private String getAddColumnStatementsForNewFields(Class<E> entityClass) throws SQLException {
        Set<String> sqlColumns = getSQLColumnNames(entityClass);

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class)) //без id-то на класа
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> allAddStatements = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = field.getAnnotationsByType(Column.class)[0].name(); //тези полета на класа User, които са анотирани с Column анотация

            if (sqlColumns.contains(fieldName)) {
                continue;
            }

            //Определяме SQL типа на база JAVA типа - от Java към SQL тип
            String sqlType = getSQLType(field.getType());

            String addStatement = String.format("ADD COLUMN %s %s", fieldName, sqlType);
            allAddStatements.add(addStatement);
        }

        return allAddStatements.stream().collect(Collectors.joining(","));
    }

    private Set<String> getSQLColumnNames(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);

        //вземи всички имена на колони без id-то - SQL заявка
        String schemaQuery = String.format("SELECT `COLUMN_NAME` FROM `information_schema`.`columns`\n" +
                "WHERE `TABLE_SCHEMA` = 'custom-orm' AND `COLUMN_NAME` != 'id'\n" +
                "AND `TABLE_NAME` = %s;", tableName);

        /*String schemaQuery = "SELECT `COLUMN_NAME` FROM `information_schema`.`columns`\n" +
                "WHERE `TABLE_SCHEMA` = 'custom-orm' AND `COLUMN_NAME` != 'id'\n" +
                "AND `TABLE_NAME` = 'users';";   */

        PreparedStatement statement = connection.prepareStatement(schemaQuery);

        ResultSet resultSet = statement.executeQuery();

        Set<String> result = new HashSet<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            result.add(columnName);
        }

        return result;
    }

    // Delete row
    @Override
    public boolean delete(E userToDelete) throws IllegalAccessException, SQLException {
        String tableName = getTableName(userToDelete.getClass());
        Field idColumn = getIdColumn(userToDelete.getClass());

        String idColumnName = idColumn.getAnnotationsByType(Column.class)[0].name(); //чрез използване на анотация връща името на полето от изкуствената инстанция на класа
        idColumn.setAccessible(true);
        Object idColumnValue = idColumn.get(userToDelete);

        String queryDelete = String.format("DELETE FROM %s WHERE %s = %s",
                tableName,
                idColumnName,
                idColumnValue);

        return connection.prepareStatement(queryDelete).execute();
    }

}
