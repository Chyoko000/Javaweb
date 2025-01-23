package Situ.Day9;

//public class JDBCDemo {
//    @Test
//    public void test1() {
//        try {
//            //通过当前类获得类加载器
//           // JDBCDemo classLoder=JDBCUtil.class.getClassLoader();
//
//
//
//            //1、加载驱动Class.forName("");
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //2、获得连接对象Connection
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3366/study?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8", "root", "123456");
//            //3、写sql语句
//            String sql = "SELECT id,name,age,gender FROM student";
//            //4、创建Statement(一艘船)
//            Statement statement = connection.createStatement();
//            //5、执行sql语句
//            // (1) 更新类（更改了表里面数据）：delete/update/insert          executeUpdate()
//            //返回值：int，表示你影响的行数
//            // (2)查询（没有改变表里面数据）:  select                                  executeQuery()
//            //返回值：结果集ResultSet
//            ResultSet resultSet = statement.executeQuery(sql);
//            List<Student> list = new ArrayList<>();
//            while (resultSet.next()) {//判断下一个有没有，没有返回false，如果有返回true，并且指向下一行
//                //当前resultSet执向第一行
//                //while每遍历一次，把这一行的每个字段的值拿出来，封装成Student对象
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                String gender = resultSet.getString("gender");
//                Student student = new Student(id, name, age, gender);
//                list.add(student);
//            }
//            for (Student student : list) {
//                System.out.println(student);
//            }
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        //6、关闭连接
//    }
//}
