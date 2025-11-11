package com.hr.api.dbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.tools.TriggerAdapter;

public class TriggerNotifier extends TriggerAdapter {
	
	private String tableName;
	private String type;
	
	@Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
		this.tableName = tableName;
		switch(type) {
			case 1:
				this.type = "INSERT";
				break;
			case 2:
				this.type = "UPDATE";
				break;
			default: 
				this.type = "DELETE";
				break;
		}
	}
	
	@Override
	public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
	            "INSERT INTO db_operations (operation, table_name, datetime) " +
	            "VALUES (?, ?, CURRENT_TIMESTAMP())")
	        ) {
	            stmt.setObject(1, type);
	            stmt.setObject(2, tableName);
	            stmt.executeUpdate();
	        }
	}

}
