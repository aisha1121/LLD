In-Memory Database



        Question:

        We should be able to manage databases
        Create/Delete/Modify
        We should be able to manage tables
        Create/Delete/Modify
        We should be able to manage indexes
        Create/Delete/Modify
        We should be able to perform following operations:
        Select
        Update
        Delete
        Truncate
        A table definition comprises columns which have types.
        We can also have constraints The supported column types are string and int.
        The string type can have a maximum length of 20 characters.
        The int type can have a minimum value of -1024 and a maximum value of 1024.
        Support for mandatory fields (tagging a column as required)
        We should be able to print all records in a table.
        We should be able to filter and display records whose column values match a given value.

    Entities:
        1. Database (name, Map<String, Table>) {tableName -> Table}
        2. Table (name, List<Coulmn>, List<Row>)
        3. Coulmn (name, columnType, required)
        4. Row (Map<String, Object>) {colname -> value}
    Enum:
        1. ColumnType (INT, STRING)