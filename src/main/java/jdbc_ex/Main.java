package jdbc_ex;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "java_user";
        String password = "java_password";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Успешное подключение к PostgreSQL!");
            }

            Statement statement = connection.createStatement();

            //execute возвращает boolean в зависимости от того успешно выполнился запрос или нет
            Boolean isUpdated =  statement.execute("UPDATE public.cars\n" +
                    "\tSET year = 2025\n" +
                    "\tWHERE id = 2;");

            //запрос
            ResultSet result = statement.executeQuery("SELECT * FROM public.cars");

          while (result.next()){
              String record = result.getString("color");
              System.out.println(record);
          }

          result.close();
          statement.close();
          connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
