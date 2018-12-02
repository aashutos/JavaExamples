/**
 * This class is generated by jOOQ
 */
package com.interview.yoti.robot.dao.jooq.tables;


import com.interview.yoti.robot.dao.jooq.Yoti;
import com.interview.yoti.robot.dao.jooq.tables.records.HooverRequestsRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HooverRequests extends TableImpl<HooverRequestsRecord> {

	private static final long serialVersionUID = 285317388;

	/**
	 * The reference instance of <code>YOTI.HOOVER_REQUESTS</code>
	 */
	public static final HooverRequests HOOVER_REQUESTS = new HooverRequests();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<HooverRequestsRecord> getRecordType() {
		return HooverRequestsRecord.class;
	}

	/**
	 * The column <code>YOTI.HOOVER_REQUESTS.REQUEST_TIMESTAMP</code>.
	 */
	public final TableField<HooverRequestsRecord, Long> REQUEST_TIMESTAMP = createField("REQUEST_TIMESTAMP", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>YOTI.HOOVER_REQUESTS.DIMENSION_ROOM</code>.
	 */
	public final TableField<HooverRequestsRecord, String> DIMENSION_ROOM = createField("DIMENSION_ROOM", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>YOTI.HOOVER_REQUESTS.POSITION_HOOVER</code>.
	 */
	public final TableField<HooverRequestsRecord, String> POSITION_HOOVER = createField("POSITION_HOOVER", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>YOTI.HOOVER_REQUESTS.DIRT_PATCHES</code>.
	 */
	public final TableField<HooverRequestsRecord, String> DIRT_PATCHES = createField("DIRT_PATCHES", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

	/**
	 * The column <code>YOTI.HOOVER_REQUESTS.INSTRUCTIONS</code>.
	 */
	public final TableField<HooverRequestsRecord, String> INSTRUCTIONS = createField("INSTRUCTIONS", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

	/**
	 * Create a <code>YOTI.HOOVER_REQUESTS</code> table reference
	 */
	public HooverRequests() {
		this("HOOVER_REQUESTS", null);
	}

	/**
	 * Create an aliased <code>YOTI.HOOVER_REQUESTS</code> table reference
	 */
	public HooverRequests(String alias) {
		this(alias, HOOVER_REQUESTS);
	}

	private HooverRequests(String alias, Table<HooverRequestsRecord> aliased) {
		this(alias, aliased, null);
	}

	private HooverRequests(String alias, Table<HooverRequestsRecord> aliased, Field<?>[] parameters) {
		super(alias, Yoti.YOTI, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HooverRequests as(String alias) {
		return new HooverRequests(alias, this);
	}

	/**
	 * Rename this table
	 */
	public HooverRequests rename(String name) {
		return new HooverRequests(name, null);
	}
}
