package com.greendao.demo;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Generator {

	public static void main(String[] args) {
		final int version = 1;
		String defaultPackage = "com.threeti.danone.common.bean";
		// 创建模式对象，指定版本号和自动生成的bean对象的包名
		Schema schema = new Schema(version, defaultPackage);
		// 指定自动生成的dao对象的包名,不指定则都DAO类生成在"test.greenDAO.bean"包中
		schema.setDefaultJavaPackageDao("com.threeti.danone.android.db.dao");
		// schema.enableKeepSectionsByDefault();//通过次Schema对象添加的所有实体都不会覆盖自定义的代码

		// 添加实体
		addEntity(schema);
		addSpitting_up(schema);
		addStool(schema);
		addMvn(schema);
		addFeeding(schema);
		addCrying(schema);
		//AddOneToMany(schema);
		//		AddManyToMany(schema);

		String outDir = "E:/danone/Danone/src";
		// 调用DaoGenerator().generateAll方法自动生成代码到之前创建的java-gen目录下
		try {
			new DaoGenerator().generateAll(schema, outDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//crying数据表
	private  static void addCrying(Schema schema){
		Entity entity =schema.addEntity("Crying");
		entity.setTableName("Crying");
		entity.setSuperclass("Diary") ;
		entity.addStringProperty("appId").notNull().primaryKey();
		entity.addStringProperty("serverId").columnName("server_Id");
		entity.addStringProperty("modifyReason").columnName("modify_reason");
		entity.addStringProperty("deleteReason").columnName("delete_reason");
		entity.addStringProperty("infantId").columnName("infant_id");
		entity.addDateProperty("ddat").notNull();
		entity.addIntProperty("status");
		entity.addStringProperty("crytype");
		entity.addStringProperty("timeOfDay").columnName("time_of_day");
		entity.addStringProperty("cryinyn");
		entity.addLongProperty("crysttim").notNull();
		entity.addLongProperty("cryentim").notNull();
	}
//spitting_up数据表
	private  static void addSpitting_up(Schema schema){
		Entity entity =schema.addEntity("SpittingUp");
		entity.setTableName("spitting_up"); 
		entity.setSuperclass("Diary") ;
		entity.addStringProperty("appId").notNull().primaryKey();
		entity.addStringProperty("serverId").columnName("server_Id");
		entity.addStringProperty("modifyReason").columnName("modify_reason");
		entity.addStringProperty("deleteReason").columnName("delete_reason");
		entity.addStringProperty("infantId").columnName("infant_id");
		entity.addDateProperty("ddat").notNull();
		entity.addIntProperty("status");
		entity.addStringProperty("reguNumber");
		entity.addStringProperty("vomiNumber");
	}
	
	//stool数据表
		private  static void addStool(Schema schema){
			Entity entity =schema.addEntity("Stool") ;
			schema.enableKeepSectionsByDefault()     ;
			entity.setTableName("stool");
			entity.setSuperclass("Diary") ;
			entity.addStringProperty("appId").notNull().primaryKey();
			entity.addStringProperty("serverId").columnName("server_Id");
			entity.addStringProperty("modifyReason").columnName("modify_reason");
			entity.addStringProperty("deleteReason").columnName("delete_reason");
			entity.addStringProperty("infantId").columnName("infant_id");
			entity.addDateProperty("ddat").notNull();
			entity.addIntProperty("status");
			entity.addStringProperty("stoolyn");
			entity.addStringProperty("image");
			entity.addIntProperty("type");
			
		}
//feeding数据表
	private  static void addFeeding(Schema schema){
		Entity entity =schema.addEntity("Feed") ;
		schema.enableKeepSectionsByDefault()     ;
		entity.setTableName("feed");
		entity.setSuperclass("Diary") ;
		entity.addStringProperty("appId").notNull().primaryKey();
		entity.addStringProperty("serverId").columnName("server_Id");
		entity.addStringProperty("modifyReason").columnName("modify_reason");
		entity.addStringProperty("deleteReason").columnName("delete_reason");
		entity.addStringProperty("infantId").columnName("infant_id");
		entity.addIntProperty("status");
		entity.addDateProperty("ddat").notNull();
		entity.addIntProperty("studyNumber").columnName("study_number");
		entity.addIntProperty("formulaNumber").columnName("formula_number");
		entity.addIntProperty("beverNumber").columnName("bever_number");
		entity.addIntProperty("compNumber").columnName("comp_number");
		entity.addIntProperty("breastNumber").columnName("breast_number");
	
		
	}
//mvn数据表
	private  static void addMvn(Schema schema){
		Entity entity =schema.addEntity("Mvn") ;
		schema.enableKeepSectionsByDefault()     ;
		entity.setTableName("mvn");
		entity.setSuperclass("Diary") ;
		entity.addStringProperty("appId").notNull().primaryKey();
		entity.addStringProperty("serverId").columnName("server_Id");
		entity.addStringProperty("modifyReason").columnName("modify_reason");
		entity.addStringProperty("deleteReason").columnName("delete_reason");
		entity.addStringProperty("infantId").columnName("infant_id");
		entity.addDateProperty("ddat").notNull();
		entity.addIntProperty("status");
		entity.addIntProperty("type");
		entity.addStringProperty("cmtrt");
		entity.addStringProperty("cmroute");
		entity.addStringProperty("cmindsp");
	}

	private static void addTc(Schema schema){
		Entity entity = schema.addEntity("tc");
		// 指定表名，如不指定，表名则为 Entity（即实体类名）
		entity.setTableName("tc");
		// 给实体类中添加属性（即给test表中添加字段）
		entity.addIdProperty().autoincrement().primaryKey();// 添加Id,自增长
		entity.addStringProperty("name").notNull();// 添加String类型的name,不能为空
		entity.addIntProperty("age").notNull();
		entity.addDoubleProperty("score").notNull();
		entity.addStringProperty("fancy").notNull();
		entity.addLongProperty("time").customType("java.lang.String",
				"com.threeti.danone.android.db.converter.ItenPropertyConverter"); //增加转换器!
	}

	private static void addEntity(Schema schema) {
		// 添加一个实体，则会自动生成实体Entity类
		Entity entity = schema.addEntity("Student");
		entity.setSuperclass("BaseStudent") ;
		// 指定表名，如不指定，表名则为 Entity（即实体类名）
		entity.setTableName("student");
		// 给实体类中添加属性（即给test表中添加字段）
		entity.addIdProperty().autoincrement().primaryKey();// 添加Id,自增长
		entity.addStringProperty("name").notNull();// 添加String类型的name,不能为空
		entity.addIntProperty("age").notNull();
		entity.addDoubleProperty("score").notNull();
		entity.addStringProperty("fancy").notNull();
		entity.addLongProperty("time").customType("java.lang.String",
				"com.threeti.danone.android.db.converter.ItenPropertyConverter"); //增加转换器!
		//
		//		entity.setHasKeepSections(false);
		//		entity.setHasKeepSections(true);// 单独让某个实体不覆盖自定义的代码
	}

	// 建立表关系
	private static void AddOneToMany(Schema schema) {
		Entity customer = schema.addEntity("Customer");
		customer.addLongProperty("customerId").primaryKey();
		customer.addStringProperty("name").notNull();

		Entity order = schema.addEntity("Order");
		order.addLongProperty("orderId").primaryKey();
		order.addDoubleProperty("money").notNull();

		Property property = order.addLongProperty("customerId").getProperty();
		order.addToOne(customer, property);

		customer.addToMany(order, property).setName("orders");

	}

	private static void AddManyToMany(Schema schema) {
		Entity student = schema.addEntity("Student");
		student.addLongProperty("studentId").primaryKey();
		student.addStringProperty("name").notNull();

		// 课程
		Entity course = schema.addEntity("Course");
		course.addLongProperty("courseId").primaryKey();
		course.addStringProperty("courseName").notNull();

		// 建立多对多关联
		Entity studentCourse = schema.addEntity("StudentCourse");
		Property studentId = studentCourse.addLongProperty("studentId").getProperty();
		Property courseId = studentCourse.addLongProperty("courseId").getProperty();
		studentCourse.addToOne(student, studentId);
		studentCourse.addToOne(course, courseId);
		student.addToMany(studentCourse, studentId);
		course.addToMany(studentCourse, courseId);
	}

}
