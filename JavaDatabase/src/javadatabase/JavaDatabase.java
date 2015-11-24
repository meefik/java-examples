package javadatabase;

/**
 * Для работы с базой Oracle XE необходимо указать параметры VM:
 * -Duser.language=en -Duser.region=us
 * 
 * @author Anton Skshidlevsky
 */
public class JavaDatabase {

    /**
     * @param args массив входных параметров, требуется указать три параметра:
     * строку подключения к базе данных, имя пользователя и пароль пользователя
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        DBAccess db = new DBAccess(args[0], args[1], args[2]);
        if (db.connect()) {
            System.out.println("Список сотрудников:");
            for (String s: db.getStaffList()) {
                System.out.println(" * " + s);
            }
            String department = db.getDepartment(3);
            System.out.println("Отдел №3: " + department);
            db.disconnect();
        }
    }
}
