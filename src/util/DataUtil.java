package util;
 
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
 
public class DataUtil {
	private Connection conn = null;
 
	private ResultSet rs = null;
 
	private PreparedStatement prestmt = null;
 
	public DataUtil() {
		String[] str = readConfigFile();
		try {
			Class.forName(str[0]);
			conn = DriverManager.getConnection(str[1], str[2], str[3]);//连接数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		//似乎仅作为实验
		DataUtil dbc = new DataUtil();
		String[] str = dbc.readConfigFile();
		for(String info:str){
			System.out.println(info);
		}
		//System.out.println(Fn.time());
	}
 
	/**
	 * 读取配置文件SystemConfig.properties
	 * @return
	 */
	private String[] readConfigFile() {
		String[] str = new String[4];
		try {
			Properties props = new Properties();
			InputStream infile = this.getClass().getResourceAsStream("/ConfigFile/SystemConfig.properties");
			props.load(infile);
 
			str[0] = props.getProperty("driver");
			str[1] = props.getProperty("url");
			str[2] = props.getProperty("username");
			str[3] = props.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
 
	/**
	 * 函数功能：删除数据
	 * 
	 * @param tableName
	 *            表名
	 * @param condition
	 *            条件 例：delete from 表 where condition
	 */
	public boolean deleteData(String tableName, String condition) {
		String sql = "";
		boolean flag = false;
		//sql语句组装
		if (condition == null || condition == "") {
			sql = "delete from " + tableName;
		} else {
			sql = "delete from " + tableName + " where " + condition;
		}
		try {
			prestmt = conn.prepareStatement(sql);
			int rscount = prestmt.executeUpdate();//被影响的记录条数
			if (rscount > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
 
	/**
	 * 函数功能：从表中取出符合条件的数据
	 * 
	 * @param tableName
	 *            表名
	 * @param field
	 *            列名
	 * @param condition
	 *            查询条件
	 * @return 一个向量集合，每个向量含String[] field
	 */
	public Vector<String[]> getData(String tableName, String[] field, String condition) {
		Vector<String[]> vec = new Vector<String[]>();
 
		String strField = "", sql = "";
		for (int i = 0; i < field.length; i++) {
			strField += field[i] + ",";
		}
		strField = strField.substring(0, strField.lastIndexOf(","));//去除strField中最后的逗号
		if (condition == null || condition == "") {
			sql = "select " + strField + " from " + tableName;
		} else {
			sql = "select " + strField + " from " + tableName + " where " + condition;
		}
		try {
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();//获取结果集
			while (rs.next()) {
				String[] temp = new String[field.length];//缓存数组的长度与属性名长度个数相同
				for (int i = 0; i < field.length; i++) {
					temp[i] = rs.getString(field[i]);//获取对应的属性值
					// Debug(temp[i]);
				}
				vec.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		return vec;
 
	}
	
	/**实现两表连接
	 * 
	 * @param tableName1 母表
	 * @param tableName2 子表
	 * @param field 列名
	 * @param joinConditioin 链接条件
	 * @param condition 筛选条件
	 * @return
	 */
	public Vector<String[]> getData(String tableName1,String tableName2,String[] field, String joinConditioin,String condition) {
		Vector<String[]> vec=new Vector<String[]>();
		
		String strField = "", sql = "";
		for (int i = 0; i < field.length; i++) {
			strField += field[i] + ",";
		}
		strField = strField.substring(0, strField.lastIndexOf(","));//去除strField中最后的逗号
		if (condition == null || condition == "") {
			sql = "select " + strField + " from " + tableName1+ " join "+tableName2+ " on " +joinConditioin;
		} else {
			sql = "select " + strField + " from " + tableName1+ " join "+tableName2+ " on " +joinConditioin + " where " + condition;
		}
		try {
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();
			while (rs.next()) {
				String[] temp = new String[field.length];
				for (int i = 0; i < field.length; i++) {
					temp[i] = rs.getString(field[i]);
					// Debug(temp[i]);
				}
				vec.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	/**
	 * 函数功能：用户登录检查
	 * 
	 * @param table
	 * @param condition
	 * @return
	 */
	public boolean CheckLogin(String table, String condition) {
		boolean flag = false;
		try {
			String sql = "select * from " + table + " where " + condition;
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
 
	/**
	 * 函数功能说明：插入数据
	 * 
	 * @param tableName
	 * @param field
	 * @param value
	 * @throws SQLException
	 *             第二个参数和第三个参数个数要相等，若为空或""，则表示该表全部字段
	 */
	public boolean addData(String tableName, String[] field, String[] value) {
		boolean flag = false;
		if (field == null || value == null || field.length == 0 || value.length == 0 || field.length != value.length)
			return flag;
		String strField = "", strValue = "";
		for (int i = 0; i < field.length; i++) {
			strField += field[i] + ",";
			strValue += "'" + value[i] + "',";
		}
		strField = strField.substring(0, strField.lastIndexOf(","));
		strValue = strValue.substring(0, strValue.lastIndexOf(","));
		try {
			String sql = "insert into " + tableName;
			sql += " (" + strField + ") values(";
			sql += strValue + ")";
			prestmt = conn.prepareStatement(sql);
			int rscount = prestmt.executeUpdate();
			if (rscount > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
 
	/**
	 * 函数功能：修改数据
	 * 
	 * @param tableName
	 *            表名
	 * @param field
	 *            字段列表
	 * @param value
	 *            值列表
	 * @param condition
	 *            条件 例：update 表 set 字段1=值1 where 条件
	 */
	public boolean updateData(String tableName, String[] field, String[] value, String condition) {
		boolean flag = false;
		if (field == null || value == null || field.length == 0 || value.length == 0 || field.length != value.length)
			return flag;
		String str = "";
		for (int i = 0; i < field.length; i++) {
			str += field[i] + "='" + value[i] + "',";
		}
		str = str.substring(0, str.lastIndexOf(","));
		// Debug(str);
		String sql = "";
		if (condition == null || condition == "") {
			sql = "update " + tableName + " set " + str;
		} else {
			sql = "update " + tableName + " set " + str + "  where " + condition;
		}
		// Debug(sql);
 
		try {
			prestmt = conn.prepareStatement(sql);
			int rscount = prestmt.executeUpdate();
			if (rscount > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
 
	/**
	 * 函数功能：执行单条语句返回一个哈希表
	 * 
	 * @param table
	 * @param field
	 * @param condition
	 * @return
	 */
	public Hashtable<String, String> execSQL(String table, String field, String condition) {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		try {
			String sql = "select " + field + " from " + table + " where " + condition;
			System.out.println(sql);
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();
			while (rs.next()) {
				ht.put(field, rs.getString(field));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ht;
 
	}
	/**
	 * 分页
	 * @param tableName
	 * @param field
	 * @param condition
	 * @param pageNo 当前页码
	 * @param pageSize 每页数量
	 * @return
	 */
	public PageUtil getPage(String tableName,String[] field,String condition,int pageNo,int pageSize){
		Vector<String[]> data = new Vector<String[]>();
		PageUtil pu = null;
		
		String strField = "", sql = "";
		for (int i = 0; i < field.length; i++) {
			strField += field[i] + ",";
		}
		strField = strField.substring(0, strField.lastIndexOf(","));//去除strField中最后的逗号
		if (condition == null || condition == "") {
			sql = "select " + strField + " from " + tableName + " limit " + (pageNo-1)*pageSize + "," + pageSize;
		} else {
			sql = "select " + strField + " from " + tableName + " limit " + (pageNo-1)*pageSize + "," + pageSize +" where " + condition;
		}
		
		try{
			//获取总数据条数
			int totalCount=0;
			String queryCount = "select count(bno) from book";
			prestmt = conn.prepareStatement(queryCount);
			rs = prestmt.executeQuery();
			while(rs.next()){
				totalCount = rs.getInt(1);
			}
			
			prestmt = conn.prepareStatement(sql);
			rs = prestmt.executeQuery();
			while(rs.next()){
				String[] tmp = new String[field.length];
				for(int i=0;i<field.length;i++){
					tmp[i] = rs.getString(field[i]);
				}
				data.add(tmp);
			}
			pu = new PageUtil(pageSize, totalCount);
			pu.setData(data);
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return pu;
	}
	
}