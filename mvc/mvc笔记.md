#  MVC开发模式

一。MVC开发模式优点:

      1.提高JavaWeb开发的代码复用性，避免了重复性开发
    
      2.有效帮助开发人员实现业务开发中【事务管理】


二MVC开发角色组成:

      C：controller,控制层
    
      M: model,业务模型层，完成业务处理
    
          1 dao层： 直接操作数据库
    
      2.service层： 调用dao层来完成业务实现，负责管理所调用的
                     dao层的【事务管理】
    
      V: view  视图层:  将处理结果写入到响应包  JSP


三。Dao层作用：

              在实际业务处理过程，往往需要进行多次的数据库访问。
          这些访问性质往往是相同，采用Dao层可以将对数据库访问
          进行封装，避免进行重复性数据库访问开发操作。同时降低
          维护的成本。
    
              例子：【张三】给【李四】转账 4000
    
                 1.确认[张三]账户是否存在
    	     2.确认[李四]账户是否存在         select  count(*)  from t_account where account=?
    	     3.查询[张三]账户余额
    	     4.查询[李四]账户余额             select  balance   from t_account where account=?
    	     5.更新[张三]账户余额
    	     6.更新[李四]账户余额             update t_account  set balance=? where account=?

四。Dao层实现:

          1.Dao层角色:
          
              1）.Dao接口层：声明Dao接口
    
    	  2）.Dao实现层：声明Dao接口实现类


          2 Dao层命名规则:
            
    	 1) Dao接口层:     com.bjpowernode.dao
    
    	                    [接口]： 表Dao
    
                 2) Dao实现层：   com.bjowernode.daoImpl
    
    	                    [实现类]：表DaoImpl

五。独立使用Dao层处理业务存在问题:

        1.无法实现【业务的复用】
    
    2.无法将参与同一个业务中sql命令放入同一个事务管理
      con.setAutoCommit(false);
    
      PreparedStatement ps1 = con.preparedStatement(sql1)
       PreparedStatement ps2 = con.preparedStatement(sql2)
    
       ps1.execute()
       ps2.execute()
    
       sql_1  与  sql_2 是存储在同一个事务中


六 service层作用：

            1.封装的具体业务实现方案，来提高业务实现复用性
    
        2.负责将参与本次业务实现的Dao层中事务进行管理


七。service层实现:

            1.角色:
                 1)service接口层
    
    	     2)service实现层
    
            2.命名:
                 1) service接口层:  com.bjowernode.service
    
    	                        [接口]: 如果本次业务只跟一张表关联
    				         表名Service
    
    					 如果本次业务跟多张表关联
    					 业务名称Service
    
                    2)service实现层:   com.bjpowrneode.serviceImpl
    
    	                        [实现类]:如果本次业务只跟一张表关联
    				           表名ServiceImpl
                                                  如果本次业务跟多张表关联
    					  业务名称ServiceImpl




八。java.lang.ThreadLocal：

        1.ThreadLocal: 本地线程库


九。service层简化开发：

         service层需要负责本次业务中事务管理
         try{
     con.setAutoCommit(false)
             dao...
         dao...
    
         con.commit
    
           }catch(Exception ex){
          con.rollback()
       }  finally{
       
           con.close()
       }

十。代理设计模式:

        1.什么是设计模式: 一代代开发人员针对开发过程中常见难题给出解题思路。
                      共有23中设计模式
    
        2.代理模式作用:
    
                     帮助指定类来完成需要的实现功能，达到功能增强效果


        3.代理模式实现例子: [张三]是一个程序员内向不善于言辞。
                        [张三]向一个姑娘表白
    		    [李四]代理【张三】向姑娘表白


        4.代理模式实现步骤:
    
                         1.创建一个接口，声明需要被帮助方法名
    
    		     2.创建一个实现类，表示需要被帮助群体
    
    		     3.创建代理实现类
    		          1）需要与被帮助类实现同样接口
    			  2）将需要帮助对象作为属性声明
    			  3）需要通过构造方法得到要帮助的对象
    			  4) 在代理方法中，让被帮助的对象完成主要功能---送花

十一。要求DeptService中save方法执行，输出相关日志信息(代理)


十二。要求为EmpService接口和DeptService接口[所有方法]在运行时，都输出相关日志

            1.在代理设计模式中，一个代理实现类只针对一个接口
    
        2.在代理设计模式中，一个代理实现类只能为一个特定的方法进行【增强】
    
        ******动态代理模式


十三： 动态代理设计模式介绍:

          1.并不属于23中设计
      2.动态代理设计模式 = 代理模式 + 反射机制
      3.可以为项目中所有接口中所有方法进行增强


十四：  动态代理实现类开发步骤

         1.代理实现类 implements java.lang.reflect.InvocationHandler
    
     2.声明一个Object类型属性对象，表示帮助的实例对象（EmpService obj or DeptService obj2）
    
     3.声明一个有参数构造函数，从外部获得本次要帮助的具体对象
    
     4.重写InvocationHandler接口invoke方法，在这个方法对帮助对象方法在运行时进行【增强】
    
     5.创建一个方法来返回具体的代理实现类对象













