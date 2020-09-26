import java.time.ZonedDateTime;

/**
 * @author Hedon Wang
 * @create 2020-05-16 15:04
 */
public class T2 {

    //获取标准格式的时间
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();   //默认市区
        System.out.println(zbj);
    }
}
