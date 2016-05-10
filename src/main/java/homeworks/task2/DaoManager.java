package homeworks.task2;

public interface DaoManager {

	void selectAll(String tableName);

	void selectSpecificInformation();

	int insert();

	void update();

	int delete();

}
