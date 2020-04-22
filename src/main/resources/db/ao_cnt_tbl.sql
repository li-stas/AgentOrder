select count(*) as cnt from user_tables where table_name like 'AO%';
org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'JDBCAccordController': 
Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: 
Could not autowire field: 
ru.javastudy.mvcHtml5Angular.mvc.jdbc.JDBCAccordDAO ru.javastudy.mvcHtml5Angular.mvc.jdbc.JDBCAccordController.jdbcAccordDAO; 
nested exception is org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'JDBCAccordDAO': 
Invocation of init method failed; 
nested exception is org.springframework.jdbc.datasource.init.ScriptStatementFailedException: 
Failed to execute SQL script statement #4 of class path resource [ao_dbschema.sql]: 
end if; nested exception is java.sql.SQLSyntaxErrorException: ORA-00900: invalid SQL statement

